package com.example.turnosrotativos.Controladores;

import com.example.turnosrotativos.Entidades.Empleado;
import com.example.turnosrotativos.Servicios.EmpleadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Action;
import java.util.List;

@RestController
public class EmpleadoControlador {

    @Autowired
    private EmpleadoServicio empleadoServicio;

    @PostMapping()
    public String crearEmpleado(@RequestBody Empleado empleado){
        this.empleadoServicio.crearEmpleado(empleado);

        return "Empleado guardado";
    }

    @GetMapping(value = "/getAllEmpleados")
    public List<Empleado> getAll(){
        return this.empleadoServicio.getAll();
    }
}
