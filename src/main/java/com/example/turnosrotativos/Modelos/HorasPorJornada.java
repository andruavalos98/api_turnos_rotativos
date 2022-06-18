package com.example.turnosrotativos.Modelos;

import com.example.turnosrotativos.Entidades.TipoDeJornadaLaboral;

public class HorasPorJornada {
    private String tipoDeJornada;
    private float horas;

    public HorasPorJornada(String tipoDeJornada, float horas) {
        this.tipoDeJornada = tipoDeJornada;
        this.horas = horas;
    }

    public String getTipoDeJornada() {
        return tipoDeJornada;
    }

    public float getHoras() {
        return horas;
    }
}
