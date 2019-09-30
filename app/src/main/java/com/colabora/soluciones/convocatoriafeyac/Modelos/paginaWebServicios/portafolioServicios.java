package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebServicios;

import java.util.List;

public class portafolioServicios {

    private String navbar;
    private String titulo;
    private List<imagenesPortafolio> imagenes;

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

    public List<imagenesPortafolio> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<imagenesPortafolio> imagenes) {
        this.imagenes = imagenes;
    }

    public portafolioServicios() {
    }

    public portafolioServicios(String navbar, String titulo, List<imagenesPortafolio> imagenes) {
        this.navbar = navbar;
        this.titulo = titulo;
        this.imagenes = imagenes;
    }
}
