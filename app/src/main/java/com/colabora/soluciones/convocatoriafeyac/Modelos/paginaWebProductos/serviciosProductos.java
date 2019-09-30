package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebProductos;

import java.util.List;

public class serviciosProductos {

    private String navbar;
    private String titulo;
    private List<caracteristicaServicio> servicio;

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

    public List<caracteristicaServicio> getServicio() {
        return servicio;
    }

    public void setServicio(List<caracteristicaServicio> servicio) {
        this.servicio = servicio;
    }

    public serviciosProductos() {
    }

    public serviciosProductos(String navbar, String titulo, List<caracteristicaServicio> servicio) {
        this.navbar = navbar;
        this.titulo = titulo;
        this.servicio = servicio;
    }
}
