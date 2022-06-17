package com.example.turnosrotativos.Entidades;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "user_generator", initialValue = 100)
    private Integer id;

    private String nombre;

    private boolean alta = true;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
