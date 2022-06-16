package com.example.turnosrotativos.Servicios;

import com.example.turnosrotativos.Entidades.Empleado;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Service
public class EmpleadoServicio {

    public ArrayList<Empleado> listaEmpleados = new ArrayList<>();

    Empleado empleado = new Empleado();

    @PostMapping()
    public void altaEmpleado(int id, String nombre){
        empleado.setId(id);
        empleado.setNombre(nombre);


        listaEmpleados.add(empleado);

    }

    public void bajaEmpleado(){
        empleado.setAlta(false);
    }

    public void modificarEmpleado(){

    }

    public void eliminarEmpleado(){
        empleado.setAlta(false);
    }
}
