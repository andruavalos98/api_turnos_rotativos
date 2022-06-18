package com.example.turnosrotativos.Servicios;

import com.example.turnosrotativos.Entidades.JornadaLaboral;
import com.example.turnosrotativos.Modelos.RequestModificarJornada;
import com.example.turnosrotativos.Repositorios.JornadaLaboralRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Objects.isNull;

@Service
public class JornadaLaboralServicio {
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
}
