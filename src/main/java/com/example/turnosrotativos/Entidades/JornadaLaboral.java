package com.example.turnosrotativos.Entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Component
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
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime horaEntrada;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime horaSalida;

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

    @JsonFormat(pattern = "dd/MM/yyyy")
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @JsonFormat(pattern = "HH:mm:ss")
    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    @JsonFormat(pattern = "HH:mm:ss")
    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
