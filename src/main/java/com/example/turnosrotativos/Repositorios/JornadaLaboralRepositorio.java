package com.example.turnosrotativos.Repositorios;

import com.example.turnosrotativos.Entidades.JornadaLaboral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JornadaLaboralRepositorio  extends JpaRepository<JornadaLaboral, Integer> {
    List<JornadaLaboral> findJornadaLaboralByEmpleadoId(Integer id);
    List<JornadaLaboral> findJornadaLaboralByFecha(LocalDate fecha);
    List<JornadaLaboral> findJornadaLaboralByEmpleadoIdAndFecha(Integer id, LocalDate fecha);
    List<JornadaLaboral> findJornadaLaboralByEmpleadoIdAndFechaBetween(Integer id, LocalDate fecha1, LocalDate fecha2);
    List<JornadaLaboral> findJornadaLaboralByFechaBetween(LocalDate fecha1, LocalDate fecha2);
    JornadaLaboral findJornadaLaboralByTipoDeJornadaLaboralIdAndFecha(Integer id, LocalDate fecha);
}
