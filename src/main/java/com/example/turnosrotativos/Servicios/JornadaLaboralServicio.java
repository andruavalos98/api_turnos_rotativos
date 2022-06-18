package com.example.turnosrotativos.Servicios;

import com.example.turnosrotativos.Entidades.JornadaLaboral;
import com.example.turnosrotativos.Modelos.RequestModificarJornada;
import com.example.turnosrotativos.Repositorios.JornadaLaboralRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class JornadaLaboralServicio {

    @Autowired
    private JornadaLaboralRepositorio JornadaLaboralRepositorio;

    public List<JornadaLaboral> getAllJornadaLaboral(){
        List<JornadaLaboral> JornadaLaboral = new ArrayList<>();
        JornadaLaboralRepositorio.findAll().forEach(JornadaLaboral::add);
        return JornadaLaboral;
    }

    public Optional<JornadaLaboral> getJornadaLaboralById(Integer id){
        return JornadaLaboralRepositorio.findById(id);
    }

    public JornadaLaboral addJornadaLaboral(JornadaLaboral jornada){
        return this.JornadaLaboralRepositorio.save(jornada);
    }

    public JornadaLaboral updateJornadaLaboral(RequestModificarJornada jornadaLaboral){
        // Si el id no existe en el repositorio, devuelvo null
        if(!this.JornadaLaboralRepositorio.existsById(jornadaLaboral.getId())) {
            return null;
        }

        // Busco y actualizo la jornada
        JornadaLaboral JornadaLaboralToUpdate = this.JornadaLaboralRepositorio.findById(jornadaLaboral.getId()).get();

        // Cambio hora de ingreso y de salida
        JornadaLaboralToUpdate.setHoraEntrada(jornadaLaboral.getHoraEntrada());
        JornadaLaboralToUpdate.setHoraSalida(jornadaLaboral.getHoraSalida());

        // Guardo los cambios y retorno la jornada actualizada
        return this.JornadaLaboralRepositorio.save(JornadaLaboralToUpdate);
    }

    public boolean removeJornadaLaboral(Integer id) {
        // Checkeo si el  de jornada laboral existe
        if (this.JornadaLaboralRepositorio.existsById(id)) {
            this.JornadaLaboralRepositorio.deleteById(id);
            return true;
        }

        return false;
    }


}
