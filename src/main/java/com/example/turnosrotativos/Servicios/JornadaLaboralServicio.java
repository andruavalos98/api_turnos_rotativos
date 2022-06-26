package com.example.turnosrotativos.Servicios;

import com.example.turnosrotativos.Entidades.Empleado;
import com.example.turnosrotativos.Entidades.JornadaLaboral;
import com.example.turnosrotativos.Entidades.TipoDeJornadaLaboral;
import com.example.turnosrotativos.Modelos.EmpleadoPorHorasCargadasPorJornada;
import com.example.turnosrotativos.Modelos.HorasPorJornada;
import com.example.turnosrotativos.Modelos.RequestModificarJornada;
import com.example.turnosrotativos.Repositorios.EmpleadoRepositorio;
import com.example.turnosrotativos.Repositorios.JornadaLaboralRepositorio;
import com.example.turnosrotativos.Repositorios.TipoJornadaLaboralRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.util.Objects.isNull;

@Service
public class JornadaLaboralServicio {
    @Autowired
    private JornadaLaboralRepositorio jornadaLaboralRepositorio;
    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;
    @Autowired
    private TipoJornadaLaboralRepositorio tipoJornadaLaboralRepositorio;

    public List<JornadaLaboral> getAllJornadaLaboral(){
        List<JornadaLaboral> JornadaLaboral = new ArrayList<>();
        jornadaLaboralRepositorio.findAll().forEach(JornadaLaboral::add);
        return JornadaLaboral;
    }

    public Optional<JornadaLaboral> getJornadaLaboralById(Integer id){
        return jornadaLaboralRepositorio.findById(id);
    }

    public List<EmpleadoPorHorasCargadasPorJornada> getHorasCargadasPorCadaTipoDeJornadaParaTodosLosEmpleados() {
        // Obtengo todos los empleados
        List<Empleado> empleados = this.empleadoRepositorio.findAll();
        // Obtengo todos los tipos de jornada
        List<TipoDeJornadaLaboral> tiposDeJornada = this.tipoJornadaLaboralRepositorio.findAll();
        // Creo una lista para devolver la respuesta
        List<EmpleadoPorHorasCargadasPorJornada> response = new ArrayList<>();

        // Este algoritmo es muy costoso pero funciona :(
        for (int i = 0; i < empleados.size(); i++) {
            Empleado empleado = empleados.get(i);
            ArrayList<HorasPorJornada> horasPorJornada = new ArrayList<>();

            // Encontrar las horas cargadas por cada tipo de jornada
            for (int j = 0; j < tiposDeJornada.size(); j++) {
                TipoDeJornadaLaboral tipo = tiposDeJornada.get(j);

                // Obtengo todas las jornadas del empleado de cada tipo
                List<JornadaLaboral> jornadas = this.jornadaLaboralRepositorio.findJornadaLaboralByEmpleadoIdAndTipoDeJornadaLaboralId(empleado.getId(), tipo.getId());

                long totalDeHorasTrabajadas = 0;

                // Sumo todas las horas de trabajo en el dia
                for (int k = 0; k < jornadas.size(); k++) {
                    JornadaLaboral jornada = jornadas.get(k);
                    totalDeHorasTrabajadas += ChronoUnit.HOURS.between(jornada.getHoraEntrada(), jornada.getHoraSalida());
                }

                // Agrego las horas por el tipo
                horasPorJornada.add(new HorasPorJornada(tipo.getNombre(), totalDeHorasTrabajadas));
            }

            // Agrego las horas por tipo de jornada a la respuesta
            response.add(new EmpleadoPorHorasCargadasPorJornada(empleado, horasPorJornada));
        }

        return response;
    }

    public JornadaLaboral addJornadaLaboral(JornadaLaboral jornada){
        return this.jornadaLaboralRepositorio.save(jornada);
    }

    public JornadaLaboral updateJornadaLaboral(RequestModificarJornada jornadaLaboral) {
        // Si el id no existe en el repositorio,
        if (
                !this.jornadaLaboralRepositorio.existsById(jornadaLaboral.getId()) ||
                isNull(jornadaLaboral.getHoraEntrada()) ||
                isNull(jornadaLaboral.getHoraSalida())
        ) {
            return null;
        }

        // Busco y actualizo la jornada
        JornadaLaboral JornadaLaboralToUpdate = this.jornadaLaboralRepositorio.findById(jornadaLaboral.getId()).get();

        // Cambio hora de ingreso y de salida
        JornadaLaboralToUpdate.setHoraEntrada(jornadaLaboral.getHoraEntrada());
        JornadaLaboralToUpdate.setHoraSalida(jornadaLaboral.getHoraSalida());

        // Guardo los cambios y retorno la jornada actualizada
        return this.jornadaLaboralRepositorio.save(JornadaLaboralToUpdate);
    }

    public boolean removeJornadaLaboral(Integer id) {
        // Checkeo si el  de jornada laboral existe
        if (this.jornadaLaboralRepositorio.existsById(id)) {
            this.jornadaLaboralRepositorio.deleteById(id);
            return true;
        }

        return false;
    }
}
