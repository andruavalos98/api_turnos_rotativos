package com.example.turnosrotativos.Controladores;

import com.example.turnosrotativos.Entidades.Empleado;
import com.example.turnosrotativos.Servicios.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleado")
public class EmpleadoControlador {

    @Autowired
    private EmpleadoServicio empleadoServicio;

    @PostMapping("/crear")
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado){
        this.empleadoServicio.crearEmpleado(empleado);

        return ResponseEntity.ok(empleado);
    }

    @GetMapping("/all")
    public List<Empleado> getAll(){
        return this.empleadoServicio.getAll();
    }

    @GetMapping("")
    public ResponseEntity getById(@RequestParam(name = "id", required = false, defaultValue = "0") Integer id) {
        Optional<Empleado> empleado = this.empleadoServicio.getById(id);

        if(!empleado.isPresent()) {
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.ok(empleado.get());
    }

//    @PatchMapping("")
//    public ResponseEntity getById(@RequestBody Empleado empleado) {
//        return ResponseEntity.ok(this.empleadoServicio.modificarEmpleado(empleado));
//    }

}
