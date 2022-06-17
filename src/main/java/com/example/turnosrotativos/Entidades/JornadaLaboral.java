package com.example.turnosrotativos.Entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class JornadaLaboral {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "user_generator", initialValue = 100)
    private Integer id;
    @ManyToOne
    private Empleado empleado;
    @ManyToOne
    private TipoDeJornadaLaboral tipoDeJornadaLaboral;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fecha;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime horaEntrada;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime horaSalida;

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public TipoDeJornadaLaboral getTipoDeJornadaLaboral() {
        return tipoDeJornadaLaboral;
    }

    public void setTipoDeJornadaLaboral(TipoDeJornadaLaboral tipoDeJornadaLaboral) {
        this.tipoDeJornadaLaboral = tipoDeJornadaLaboral;
    }

    @JsonFormat(pattern = "dd/MM/yyyy")
    public LocalDate getFecha() {
        return fecha;
    }

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
