package com.example.turnosrotativos.Modelos;

public class ErrorResponse {
    private String error = "Algo salió mal.";

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
