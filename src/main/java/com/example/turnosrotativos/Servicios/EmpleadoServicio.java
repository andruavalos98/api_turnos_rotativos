package com.example.turnosrotativos.Servicios;

import com.example.turnosrotativos.Entidades.Empleado;
import com.example.turnosrotativos.Repositorios.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServicio {
    @Autowired
    private EmpleadoRepositorio repositorio;

    @Autowired
    private Empleado entidad;

    public List<Empleado> getAll(){
        return this.repositorio.findAll();
    }

    public Optional<Empleado> getById(Integer id) {
        return this.repositorio.findById(id);
    }

    // Creo una nueva instancia de un empleado y seteo un nombre
    public void crearEmpleado(Empleado empleado) {
        this.entidad.setNombre(empleado.getNombre());

        this.repositorio.save(empleado);
    }

    public void modificarEmpleado() {

    }

    public void bajaEmpleado() {
        this.entidad.setAlta(false);
    }

}
