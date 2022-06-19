package com.example.turnosrotativos.Servicios;

import com.example.turnosrotativos.Entidades.TipoDeJornadaLaboral;
import com.example.turnosrotativos.Repositorios.TipoJornadaLaboralRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class TipoJornadaLaboralServicio {

    @Autowired
    private TipoJornadaLaboralRepositorio tipoJornadaLaboralRepositorio;

    public List<TipoDeJornadaLaboral> getAllTipoJornadaLaboral(){
        List<TipoDeJornadaLaboral> tiposDeJornadaLaboral = new ArrayList<>();
        tipoJornadaLaboralRepositorio.findAll().forEach(tiposDeJornadaLaboral::add);
        return tiposDeJornadaLaboral;
    }

    public Optional<TipoDeJornadaLaboral> getTipoJornadaLaboralById(Integer id){
        return tipoJornadaLaboralRepositorio.findById(id);
    }

    public TipoDeJornadaLaboral getTipoJornadaLaboralByName(String nombre) {
        return tipoJornadaLaboralRepositorio.findByNombre(nombre);
    }

    public TipoDeJornadaLaboral addTipoJornadaLaboral(TipoDeJornadaLaboral tipo){
        return this.tipoJornadaLaboralRepositorio.save(tipo);
    }

    public TipoDeJornadaLaboral updateTipoJornadaLaboral(TipoDeJornadaLaboral tipoDeJornadaLaboral){
        // Si no existe el tipo en el repositorio, devuelvo null
        if(!this.tipoJornadaLaboralRepositorio.existsById(tipoDeJornadaLaboral.getId())) {
            return null;
        }

        // Busco y actualizo el tipo de actividad
        TipoDeJornadaLaboral tipoDeJornadaLaboralToUpdate = this.tipoJornadaLaboralRepositorio.findById(tipoDeJornadaLaboral.getId()).orElse(null);
        TipoDeJornadaLaboral tipoDeJornadaLaboralUpdated = buildTipodeJornadaLaboral(tipoDeJornadaLaboralToUpdate, tipoDeJornadaLaboral);
        this.tipoJornadaLaboralRepositorio.save(tipoDeJornadaLaboralUpdated);
        return tipoDeJornadaLaboralUpdated;
    }

    private TipoDeJornadaLaboral buildTipodeJornadaLaboral(TipoDeJornadaLaboral tipoDeJornadaLaboralToUpdeate, TipoDeJornadaLaboral tipoDeJornadaLaboralWithChanges){
        return new TipoDeJornadaLaboral(
                nonNull(tipoDeJornadaLaboralWithChanges.getId())
                        ? tipoDeJornadaLaboralWithChanges.getId()
                        : tipoDeJornadaLaboralToUpdeate.getId(),
                nonNull(tipoDeJornadaLaboralWithChanges.getNombre())
                        ? tipoDeJornadaLaboralWithChanges.getNombre()
                        : tipoDeJornadaLaboralToUpdeate.getNombre(),
                nonNull(tipoDeJornadaLaboralWithChanges.isAlta())
                        ? tipoDeJornadaLaboralWithChanges.isAlta()
                        : tipoDeJornadaLaboralToUpdeate.isAlta()
                );
    }

    public boolean removeTipoDeJornadaLaboral(Integer id){
        // Checkeo si el tipo de jornada laboral existe
        if(this.tipoJornadaLaboralRepositorio.existsById(id)) {
            this.tipoJornadaLaboralRepositorio.deleteById(id);
            return true;
        }

        return false;
    }

}
