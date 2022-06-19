package com.example.turnosrotativos.Modelos;

/*
* Modelo creado para enviar mensajes de error como response
* */
public class ErrorResponse {
    private String error = "Algo salió mal.";

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
