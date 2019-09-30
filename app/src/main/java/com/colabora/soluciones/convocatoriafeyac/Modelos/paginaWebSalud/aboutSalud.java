package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebSalud;

import java.util.List;

public class aboutSalud {

    private String navbar;
    private String titulo;
    private String descripcion;
    private List<caracteristicasAbout> caracteristicas;

    public String getNavbar() {
        return navbar;
    }

    public void setNavbar(String navbar) {
        this.navbar = navbar;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<caracteristicasAbout> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<caracteristicasAbout> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public aboutSalud() {
    }

    public aboutSalud(String navbar, String titulo, String descripcion, List<caracteristicasAbout> caracteristicas) {
        this.navbar = navbar;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.caracteristicas = caracteristicas;
    }
}
