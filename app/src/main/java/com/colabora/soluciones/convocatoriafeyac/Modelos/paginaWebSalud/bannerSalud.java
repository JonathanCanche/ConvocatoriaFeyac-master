package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebSalud;

public class bannerSalud {

    private String autor;
    private String descripcion;
    private String titulo;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public bannerSalud() {
    }

    public bannerSalud(String autor, String descripcion, String titulo) {
        this.autor = autor;
        this.descripcion = descripcion;
        this.titulo = titulo;
    }
}
