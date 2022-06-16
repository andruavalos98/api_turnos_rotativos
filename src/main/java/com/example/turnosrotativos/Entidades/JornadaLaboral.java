package com.example.turnosrotativos.Entidades;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
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
     private Date fecha;
     private Date horaEntrada;
     private Date horaSalida;

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
