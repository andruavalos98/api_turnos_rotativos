package com.example.turnosrotativos.Servicios;

import com.example.turnosrotativos.Entidades.Empleado;
import com.example.turnosrotativos.Repositorios.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleadoServicio {

    public ArrayList<Empleado> listaEmpleados = new ArrayList<>();

    @Autowired
    private EmpleadoRepositorio repositorio;

    private Empleado entidad;


 // Creo una nueva instancia de un empleado y seteo un nombre
    public void crearEmpleado(Empleado empleado) {
        this.entidad.setNombre(empleado.getNombre());

        this.repositorio.save(this.entidad);
    }

    public void bajaEmpleado() {
        this.entidad.setAlta(false);
    }

    public void modificarEmpleado() {

    }

    public List<Empleado> getAll(){
        return this.repositorio.findAll();
    }



}
