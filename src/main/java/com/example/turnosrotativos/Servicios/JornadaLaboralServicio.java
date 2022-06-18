package com.example.turnosrotativos.Servicios;

import com.example.turnosrotativos.Entidades.JornadaLaboral;
import com.example.turnosrotativos.Modelos.RequestModificarJornada;
import com.example.turnosrotativos.Repositorios.JornadaLaboralRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class JornadaLaboralServicio {

    private final int ID_JORNADA_NORMAL = 4;
    private final int ID_HORAS_EXTRA = 2;
    private final int ID_VACACIONES = 5;
    private final int ID_DIA_LIBRE = 3;

    @Autowired
    private JornadaLaboralRepositorio jornadaLaboralRepositorio;
    @Autowired
    private TipoJornadaLaboralServicio tipoJornadaServicio;

    public List<JornadaLaboral> getAllJornadaLaboral(){
        List<JornadaLaboral> JornadaLaboral = new ArrayList<>();
        jornadaLaboralRepositorio.findAll().forEach(JornadaLaboral::add);
        return JornadaLaboral;
    }

    public Optional<JornadaLaboral> getJornadaLaboralById(Integer id){
        return jornadaLaboralRepositorio.findById(id);
    }

    public JornadaLaboral addJornadaLaboral(JornadaLaboral jornada){
        if (!this.esDuracionDeJornadaValida(jornada) || !this.esCantidadDeHorasTrabajadasValidasPorDia(jornada) || !this.esCantidadDeHorasTrabajadasValidasPorSemana(jornada) || !this.esCantidadDeEmpleadosValidas(jornada) || !this.quedanDiasLibresDisponibles(jornada)) {
            return null;
        }

        return this.jornadaLaboralRepositorio.save(jornada);
    }

    public JornadaLaboral updateJornadaLaboral(RequestModificarJornada jornadaLaboral) {
        // Si el id no existe en el repositorio,
        if (
                !this.jornadaLaboralRepositorio.existsById(jornadaLaboral.getId()) ||
                isNull(jornadaLaboral.getHoraEntrada()) ||
                isNull(jornadaLaboral.getHoraEntrada())
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

    private boolean esDuracionDeJornadaValida(JornadaLaboral jornada) {
        long duracionJornada = this.obtenerDuracionDeJornada(jornada);

        switch (jornada.getTipoDeJornadaLaboral().getId()) {
            case ID_JORNADA_NORMAL:
                return duracionJornada >= 6 && duracionJornada <= 8;
            case ID_HORAS_EXTRA:
                return duracionJornada >= 2 && duracionJornada <= 6;
            default:
                return true;
        }
    }

    private boolean esCantidadDeHorasTrabajadasValidasPorDia(JornadaLaboral jornada) {
//        List<JornadaLaboral> jornadasDelEmpleadoEnElDia = this.jornadaLaboralRepositorio.

        return true;
    }

    private boolean esCantidadDeHorasTrabajadasValidasPorSemana(JornadaLaboral jornada) {
//         long duracionNuevaJornada = ChronoUnit.HOURS.between(jornada.getHoraEntrada(), jornada.getHoraSalida());

//         List<JornadaLaboral> jornadasDelEmpleado = this.jornadaLaboralRepositorio.findJornadaLaboralByEmpleadoId(jornada.getEmpleado().getId());

//         long horasTrabajadasEnLaSemana = 0;

//         jornadasDelEmpleado.forEach(elemento -> {
//
//         });

         return true;
    }

    private boolean esCantidadDeEmpleadosValidas(JornadaLaboral jornada) {
        return true;
    }

    private boolean empleadoNoTieneDiaLibre(JornadaLaboral jornada) {
        return true;
    }

    private boolean quedanDiasLibresDisponibles(JornadaLaboral jornada) {
        return true;
    }

    private long obtenerDuracionDeJornada(JornadaLaboral jornada) {
        return ChronoUnit.HOURS.between(jornada.getHoraEntrada(), jornada.getHoraSalida());
    }

}
