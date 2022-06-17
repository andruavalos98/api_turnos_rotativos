package com.example.turnosrotativos.Entidades;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
public class TipoDeJornadaLaboral {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "user_generator", initialValue = 100)
    private Integer id;
    private String nombre;

    private boolean alta = true;

    public TipoDeJornadaLaboral(Integer id, String nombre, boolean alta) {
        this.id = id;
        this.nombre = nombre;
        this.alta = alta;
    }

    public TipoDeJornadaLaboral() {

    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
