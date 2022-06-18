package com.example.turnosrotativos.Controladores;

import com.example.turnosrotativos.Entidades.TipoDeJornadaLaboral;
import com.example.turnosrotativos.Servicios.TipoJornadaLaboralServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("tipo-de-jornada-laboral")
public class TipoJornadaLaboralControlador {
    private final TipoJornadaLaboralServicio tipoJornadaLaboralServicio;

    @Autowired
    public TipoJornadaLaboralControlador(TipoJornadaLaboralServicio tipoJornadaLaboralServicio){
        this.tipoJornadaLaboralServicio = tipoJornadaLaboralServicio;
    }

    @GetMapping("/listar")
    public List<TipoDeJornadaLaboral> getAllTiposJornadaLaboral(){
        return tipoJornadaLaboralServicio.getAllTipoJornadaLaboral();
    }

    @GetMapping("/listar/{id}")
    public TipoDeJornadaLaboral getTipoJornadaLaboral(@PathVariable Integer id){
        return tipoJornadaLaboralServicio.getTipoJornadaLaboralById(id).get();
    }

    @GetMapping("/buscar-nombre/{nombre}")
    public ResponseEntity getTipoJornadaLaboral(@PathVariable String nombre){
        // Busca
        TipoDeJornadaLaboral tipoBuscado = tipoJornadaLaboralServicio.getTipoJornadaLaboralByName(nombre);

        // Si no encuentra nada
        if (isNull(tipoBuscado)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        // Caso afirmativo
        return ResponseEntity.ok(tipoBuscado);
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addTipoJornadaLaboral(@RequestBody TipoDeJornadaLaboral tipoDeJornadaLaboral){
        return ResponseEntity.ok(this.tipoJornadaLaboralServicio.addTipoJornadaLaboral(tipoDeJornadaLaboral));
    }

    @PatchMapping("/modificar")
    public ResponseEntity updateTipoJornadaLaboral(@RequestBody TipoDeJornadaLaboral tipoDeJornadaLaboral){
        // Try to make the update and save the value in a variable
        TipoDeJornadaLaboral tipoModificado = this.tipoJornadaLaboralServicio.updateTipoJornadaLaboral(tipoDeJornadaLaboral);

        // If the update failed, return a bad response
        if (isNull(tipoModificado)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        // Otherwise returns a Successful request
        return ResponseEntity.ok(tipoModificado);
    }

    @DeleteMapping("/borrar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity removeTipoJornadaLaboral(@PathVariable Integer id){
        return ResponseEntity.ok(this.tipoJornadaLaboralServicio.removeTipoDeJornadaLaboral(id));
    }
}
