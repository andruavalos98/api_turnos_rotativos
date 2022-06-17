package com.example.turnosrotativos.Repositorios;

import com.example.turnosrotativos.Entidades.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepositorio  extends JpaRepository<Empleado, Integer> {
}
