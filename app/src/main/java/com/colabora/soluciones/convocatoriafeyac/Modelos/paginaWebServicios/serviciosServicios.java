package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebServicios;

import java.util.List;

public class serviciosServicios {

    private String descripcion;
    private String navbar;
    private String titulo;
    private List<caracteristicasServicio> servicio;

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

    public List<caracteristicasServicio> getServicio() {
        return servicio;
    }

    public void setServicio(List<caracteristicasServicio> servicio) {
        this.servicio = servicio;
    }

    public serviciosServicios() {
    }

    public serviciosServicios(String descripcion, String navbar, String titulo, List<caracteristicasServicio> servicio) {
        this.descripcion = descripcion;
        this.navbar = navbar;
        this.titulo = titulo;
        this.servicio = servicio;
    }
}
