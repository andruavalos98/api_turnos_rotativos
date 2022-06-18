package com.example.turnosrotativos.Modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalTime;

public class RequestModificarJornada {
    private Integer id;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime horaEntrada;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime horaSalida;

    public Integer getId() {
        return id;
    }

    @JsonFormat(pattern = "HH:mm:ss")
    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    @JsonFormat(pattern = "HH:mm:ss")
    public LocalTime getHoraSalida() {
        return horaSalida;
    }
}
