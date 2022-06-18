package com.example.turnosrotativos.Repositorios;

import com.example.turnosrotativos.Entidades.JornadaLaboral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JornadaLaboralRepositorio  extends JpaRepository<JornadaLaboral, Integer> {
}
