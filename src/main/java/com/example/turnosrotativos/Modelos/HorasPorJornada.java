package com.example.turnosrotativos.Modelos;
/*
* Modelo creado para ser parte de la response al endpoint listar/horas-cargadas-por-jornada
* */
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
