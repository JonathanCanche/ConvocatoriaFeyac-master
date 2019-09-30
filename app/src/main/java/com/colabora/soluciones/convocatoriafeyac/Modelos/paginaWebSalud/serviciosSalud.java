package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebSalud;

import java.util.List;

public class serviciosSalud {

    private String descripcion;
    private String navbar;
    private String titulo;
    private List<caracteristicaServicios> servicio;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

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

    public List<caracteristicaServicios> getServicio() {
        return servicio;
    }

    public void setServicio(List<caracteristicaServicios> servicio) {
        this.servicio = servicio;
    }

    public serviciosSalud() {
    }

    public serviciosSalud(String descripcion, String navbar, String titulo, List<caracteristicaServicios> servicio) {
        this.descripcion = descripcion;
        this.navbar = navbar;
        this.titulo = titulo;
        this.servicio = servicio;
    }
}
