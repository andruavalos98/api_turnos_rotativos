package com.example.turnosrotativos.Controladores;

import com.example.turnosrotativos.Entidades.JornadaLaboral;
import com.example.turnosrotativos.Modelos.ErrorResponse;
import com.example.turnosrotativos.Modelos.RequestModificarJornada;
import com.example.turnosrotativos.Servicios.CrearJornadaLaboralValidadorServicio;
import com.example.turnosrotativos.Servicios.JornadaLaboralServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/jornada")
public class JornadaLaboralControlador {

    @Autowired
    private JornadaLaboralServicio jornadaLaboralServicio;
    @Autowired
    private CrearJornadaLaboralValidadorServicio crearJornadaLaboralValidadorServicio;

    @GetMapping("/listar")
    public List<JornadaLaboral> getAllJornadaLaboral(){
        return jornadaLaboralServicio.getAllJornadaLaboral();
    }

    @GetMapping("/listar/{id}")
    public JornadaLaboral getJornadaLaboral(@PathVariable Integer id){
        return jornadaLaboralServicio.getJornadaLaboralById(id).get();
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addJornadaLaboral(@RequestBody JornadaLaboral nuevaJornada){
//        return ResponseEntity.ok(this.crearJornadaLaboralValidadorServicio.encontrarEmpleadoConDiaLibre(nuevaJornada));

        // Valido la nuevaJornada
        ErrorResponse errorDeValidacion = this.crearJornadaLaboralValidadorServicio.validarJornada(nuevaJornada);

        // Si hay un error, lo agrego a la response y la retorno
        if(nonNull(errorDeValidacion)) {
            return new ResponseEntity(errorDeValidacion, HttpStatus.BAD_REQUEST);
        }

//        return ResponseEntity.ok(this.jornadaLaboralServicio.horasTrabajadasEnLaSemana(DeJornadaLaboral));
        return ResponseEntity.ok(this.jornadaLaboralServicio.addJornadaLaboral(nuevaJornada));
    }

    @PatchMapping("/modificar")
    public ResponseEntity updateJornadaLaboral(@RequestBody RequestModificarJornada jornadaModificada){
        // Try to make the update and save the value in a variable
        JornadaLaboral jornadaUpdated = this.jornadaLaboralServicio.updateJornadaLaboral(jornadaModificada);

        // If the update failed, return a bad response
        if (isNull(jornadaUpdated)) {
            return new ResponseEntity("Valores de la petición invalidos.", HttpStatus.NOT_FOUND);
        }

        // Otherwise returns a Successful request
        return ResponseEntity.ok(jornadaUpdated);
    }

    @DeleteMapping("/borrar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity removeJornadaLaboral(@PathVariable Integer id){
        return ResponseEntity.ok(this.jornadaLaboralServicio.removeJornadaLaboral(id));
    }

}
