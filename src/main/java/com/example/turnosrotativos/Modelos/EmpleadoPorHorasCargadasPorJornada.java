package com.example.turnosrotativos.Modelos;

import com.example.turnosrotativos.Entidades.Empleado;

import java.util.List;

/*
* Clase hecha para la estructura de la response para el endpoint listar/horas-cargadas-por-jornada
* */
public class EmpleadoPorHorasCargadasPorJornada {
    private Empleado empleado;
    private List<HorasPorJornada> horasPorCadaTipoJornada;

    public EmpleadoPorHorasCargadasPorJornada(Empleado empleado, List<HorasPorJornada> horasPorCadaTipoJornada) {
        this.empleado = empleado;
        this.horasPorCadaTipoJornada = horasPorCadaTipoJornada;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public List<HorasPorJornada> getHorasPorCadaTipoJornada() {
        return horasPorCadaTipoJornada;
    }
}
