package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebSalud;

import java.util.List;

public class horarioSalud {

    private String titulo;
    private List<horaHorario> hora;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<horaHorario> getHora() {
        return hora;
    }

    public void setHora(List<horaHorario> hora) {
        this.hora = hora;
    }

    public horarioSalud() {
    }

    public horarioSalud(String titulo, List<horaHorario> hora) {
        this.titulo = titulo;
        this.hora = hora;
    }
}
