package com.example.turnosrotativos.Repositorios;

import com.example.turnosrotativos.Entidades.TipoDeJornadaLaboral;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoJornadaLaboralRepositorio  extends JpaRepository<TipoDeJornadaLaboral, Integer> {

}
