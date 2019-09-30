package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebServicios;

public class imagenesPortafolio {

    private String descripcion;
    private String imagen;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public imagenesPortafolio() {
    }

    public imagenesPortafolio(String descripcion, String imagen, String titulo) {
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.titulo = titulo;
    }
}
