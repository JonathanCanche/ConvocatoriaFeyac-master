package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebProductos;

import java.util.List;

public class galeriaProductos {

    private String navbar;
    private String titulo;
    private List<imagenesGaleria> imagenes;

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

    public List<imagenesGaleria> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<imagenesGaleria> imagenes) {
        this.imagenes = imagenes;
    }

    public galeriaProductos() {
    }

    public galeriaProductos(String navbar, String titulo, List<imagenesGaleria> imagenes) {
        this.navbar = navbar;
        this.titulo = titulo;
        this.imagenes = imagenes;
    }
}
