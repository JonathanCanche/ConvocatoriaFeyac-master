package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebProductos;

import java.util.List;

public class homeProductos {

    private String navbar;
    private String subtitulo;
    private String titulo;
    private List<imagenHome> imagen;

    public String getNavbar() {
        return navbar;
    }

    public void setNavbar(String navbar) {
        this.navbar = navbar;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<imagenHome> getImagen() {
        return imagen;
    }

    public void setImagen(List<imagenHome> imagen) {
        this.imagen = imagen;
    }

    public homeProductos() {
    }

    public homeProductos(String navbar, String subtitulo, String titulo, List<imagenHome> imagen) {
        this.navbar = navbar;
        this.subtitulo = subtitulo;
        this.titulo = titulo;
        this.imagen = imagen;
    }
}
