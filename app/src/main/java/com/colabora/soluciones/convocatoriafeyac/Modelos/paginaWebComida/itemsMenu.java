package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebComida;

public class itemsMenu {

    private String descripcion;
    private int precio;
    private String titulo;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public itemsMenu() {
    }

    public itemsMenu(String descripcion, int precio, String titulo) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.titulo = titulo;
    }
}
