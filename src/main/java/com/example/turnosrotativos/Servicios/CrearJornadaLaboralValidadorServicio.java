package com.example.turnosrotativos.Servicios;

import com.example.turnosrotativos.Entidades.JornadaLaboral;
import com.example.turnosrotativos.Modelos.ErrorResponse;
import com.example.turnosrotativos.Repositorios.JornadaLaboralRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class CrearJornadaLaboralValidadorServicio {
    private final int ID_JORNADA_NORMAL = 4;
    private final int ID_HORAS_EXTRA = 2;
    private final int ID_VACACIONES = 5;
    private final int ID_DIA_LIBRE = 3;

    @Autowired
    private JornadaLaboralRepositorio jornadaLaboralRepositorio;
    @Autowired
    private TipoJornadaLaboralServicio tipoJornadaServicio;

    public ErrorResponse validarJornada(JornadaLaboral nuevaJornada) {
        if(!this.esDuracionDeJornadaValida(nuevaJornada)) {
            return new ErrorResponse("La duración de la nuevaJornada debe ser de entre 6 y 8 horas para una nuevaJornada Normal y de entre 2 y 6 horas para horas extras.");
        }

        if(!this.esCantidadDeHorasTrabajadasValidasPorDia(nuevaJornada)) {
            return new ErrorResponse("El empleado no puede trabajar mas de 12 horas por dia.");
        }

        if(!this.esCantidadDeHorasTrabajadasValidasPorSemana(nuevaJornada)) {
            return new ErrorResponse("El empleado no puede trabajar mas de 48 horas por semana.");
        }

        if(!this.esCantidadDeEmpleadosValidaParaElTurno(nuevaJornada)) {
            return new ErrorResponse("Un turno no puede tener mas de 2 empleados a la vez.");
        }

        if (nuevaJornada.getTipoDeJornadaLaboral().getId().equals(ID_DIA_LIBRE) && !this.quedanDiasLibresDisponibles(nuevaJornada)) {
            return new ErrorResponse("El empleado no puede tener mas dias libres esta semana.");
        }

        if (!this.empleadoNoTieneDiaLibre(nuevaJornada)) {
            return new ErrorResponse("El empleado tiene el día libre en la fecha " + nuevaJornada.getFecha().toString());
        }

        return null;
    }

    private boolean esDuracionDeJornadaValida(JornadaLaboral nuevaJornada) {
        long duracionJornada = this.obtenerDuracionDeJornada(nuevaJornada);

        switch (nuevaJornada.getTipoDeJornadaLaboral().getId()) {
            case ID_JORNADA_NORMAL:
                return duracionJornada >= 6 && duracionJornada <= 8;
            case ID_HORAS_EXTRA:
                return duracionJornada >= 2 && duracionJornada <= 6;
            default:
                return true;
        }
    }

    private boolean esCantidadDeHorasTrabajadasValidasPorDia(JornadaLaboral nuevaJornada) {
        // Si el empleado esta de vacaciones, no importa las horas que se le sumen al dia
        if (!this.empleadoNoEstaDeVacacionesNiDiaLibre(nuevaJornada)) {
            return true;
        }

        // Todos las jornadas del empleado en el dia
        List<JornadaLaboral> jornadasDelEmpleadoEnElDia = this.jornadaLaboralRepositorio.findJornadaLaboralByEmpleadoIdAndFecha(nuevaJornada.getEmpleado().getId(), nuevaJornada.getFecha());

        // Jornadas del empleado en el dia sin tener en cuenta dia libre ni vacaciones
        List<JornadaLaboral> jornadasLaboralesEnElDia = this.filtrarPorDiasTrabajados(jornadasDelEmpleadoEnElDia);

        long horasASumar = this.obtenerDuracionDeJornada(nuevaJornada);
        long horasTrabajadasEnElDia = this.obtenerHorasTrabajadasDeUnaLista(jornadasLaboralesEnElDia);

        // Sumo las horas que ya tiene planeadas mas las nuevas y verifico que no superen las 12
        return horasTrabajadasEnElDia + horasASumar <= 12;
    }

    private boolean esCantidadDeHorasTrabajadasValidasPorSemana(JornadaLaboral nuevaJornada) {
        // Pbtengo los dias trabjados del empleado
        List<JornadaLaboral> jornadasLaboralesDeLaSemana = this.obtenerJornadasLaboralesDeLaSemana(nuevaJornada);

        long horasTrabajadasEnLaSemana = this.obtenerHorasTrabajadasDeUnaLista(jornadasLaboralesDeLaSemana);

        long duracionNuevaJornada = ChronoUnit.HOURS.between(nuevaJornada.getHoraEntrada(), nuevaJornada.getHoraSalida());

        long horasATrabajarEnLaSemana = horasTrabajadasEnLaSemana + duracionNuevaJornada;

        // Controlo que las horas a trabajar no sean mayores que 48
        return horasATrabajarEnLaSemana <= 48;
    }

    private boolean esCantidadDeEmpleadosValidaParaElTurno(JornadaLaboral nuevaJornada) {
//        List<JornadaLaboral> jornadasLaboralesDeLaSemana = this.obtenerJornadasLaboralesDeLaSemana(nuevaJornada);
//
//        jornadasLaboralesDeLaSemana.stream()
//                .filter(jornada -> {
//                    jornada.getHoraEntrada().i
//                })
//
//        int cantidadDeEmpleadosEnElTurno =
//
//        for (int i = 0; i < ; i++) {
//
//        }

        return true;
    }

    private boolean empleadoNoTieneDiaLibre(JornadaLaboral nuevaJornada) {
        JornadaLaboral diaLibre = this.jornadaLaboralRepositorio.findJornadaLaboralByTipoDeJornadaLaboralIdAndFecha(ID_DIA_LIBRE, nuevaJornada.getFecha());
        JornadaLaboral diaDeLicencia = this.jornadaLaboralRepositorio.findJornadaLaboralByTipoDeJornadaLaboralIdAndFecha(ID_VACACIONES, nuevaJornada.getFecha());

        return isNull(diaLibre) && isNull(diaDeLicencia);
    }

    private boolean quedanDiasLibresDisponibles(JornadaLaboral nuevaJornada) {
        // Obtengo el primer y el ultimo dia de la semana de la jornada a ingresar
        final DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
        final DayOfWeek lastDayOfWeek = DayOfWeek.of(((firstDayOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);

        // Busco el primer dia de la semana
        LocalDate primerDiaDeLaSemana = nuevaJornada.getFecha().with(TemporalAdjusters.previousOrSame(firstDayOfWeek));

        // Busco el ultimo dia de la semana
        LocalDate ultimoDiaDeLaSemana = nuevaJornada.getFecha().with(TemporalAdjusters.nextOrSame(lastDayOfWeek));

        List<JornadaLaboral> jornadasLibres = this.jornadaLaboralRepositorio.findJornadaLaboralByTipoDeJornadaLaboralIdAndFechaBetween(ID_DIA_LIBRE, primerDiaDeLaSemana, ultimoDiaDeLaSemana);

        return jornadasLibres.size() < 2;
    }

    private long obtenerDuracionDeJornada(JornadaLaboral jornada) {
        return ChronoUnit.HOURS.between(jornada.getHoraEntrada(), jornada.getHoraSalida());
    }

    private boolean empleadoNoEstaDeVacacionesNiDiaLibre(JornadaLaboral jornada) {
        return !jornada.getTipoDeJornadaLaboral().getId().equals(ID_DIA_LIBRE) && !jornada.getTipoDeJornadaLaboral().getId().equals(ID_VACACIONES);
    }

    private List<JornadaLaboral> filtrarPorDiasTrabajados(List<JornadaLaboral> listaDeJornadas) {
        return listaDeJornadas.stream()
                .filter(elemento -> this.empleadoNoEstaDeVacacionesNiDiaLibre(elemento))
                .collect(Collectors.toList());
    }

    private long obtenerHorasTrabajadasDeUnaLista(List<JornadaLaboral> listaDeJornadas) {
        long totalDeHorasTrabajadas = 0;

        // Sumo todas las horas de trabajo en el dia
        for (int i = 0; i < listaDeJornadas.size(); i++) {
            totalDeHorasTrabajadas += this.obtenerDuracionDeJornada(listaDeJornadas.get(i));
        }

        return totalDeHorasTrabajadas;
    }

    private List<JornadaLaboral> obtenerJornadasLaboralesDeLaSemana(JornadaLaboral nuevaJornada) {
        // Obtengo el primer y el ultimo dia de la semana de la jornada a ingresar
        final DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
        final DayOfWeek lastDayOfWeek = DayOfWeek.of(((firstDayOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);

        // Busco el primer dia de la semana
        LocalDate primerDiaDeLaSemana = nuevaJornada.getFecha().with(TemporalAdjusters.previousOrSame(firstDayOfWeek));

        // Busco el ultimo dia de la semana
        LocalDate ultimoDiaDeLaSemana = nuevaJornada.getFecha().with(TemporalAdjusters.nextOrSame(lastDayOfWeek));

        // Busco las jornadas que tuvo el empleado durante la semana
        List<JornadaLaboral> jornadasDeLaSemana = this.jornadaLaboralRepositorio.findJornadaLaboralByEmpleadoIdAndFechaBetween(nuevaJornada.getEmpleado().getId(), primerDiaDeLaSemana, ultimoDiaDeLaSemana);

        // Pbtengo los dias trabjados del empleado
        return this.filtrarPorDiasTrabajados(jornadasDeLaSemana);
    }

}
