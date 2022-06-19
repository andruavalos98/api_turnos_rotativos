package com.example.turnosrotativos.Repositorios;

import com.example.turnosrotativos.Entidades.TipoDeJornadaLaboral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoJornadaLaboralRepositorio  extends JpaRepository<TipoDeJornadaLaboral, Integer> {
    TipoDeJornadaLaboral findByNombre(String nombre);
}
