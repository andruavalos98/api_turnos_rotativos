package com.example.turnosrotativos.Servicios;

import com.example.turnosrotativos.Entidades.TipoDeJornadaLaboral;

public class TipoJornadaLaboralServicio {

    TipoDeJornadaLaboral tipoDeJornadaLaboral = new TipoDeJornadaLaboral();

    public void altaTipoJornadaLaboral(int id, String nombre, boolean alta){
        tipoDeJornadaLaboral.setId(id);
        tipoDeJornadaLaboral.setNombre(nombre);

    }
}
