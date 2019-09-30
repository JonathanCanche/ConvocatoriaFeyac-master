package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebProductos;

public class aboutProductos {

    private String descripcion;
    private String imagen;
    private String navbar;
    private String subtitulo;
    private String titulo;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

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

    public aboutProductos() {
    }

    public aboutProductos(String descripcion, String imagen, String navbar, String subtitulo, String titulo) {
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.navbar = navbar;
        this.subtitulo = subtitulo;
        this.titulo = titulo;
    }
}
