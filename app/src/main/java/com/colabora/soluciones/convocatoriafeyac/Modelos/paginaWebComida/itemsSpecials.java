package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebComida;

public class itemsSpecials {

    private String descripcion;
    private String img;
    private int precio;
    private String titulo;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public itemsSpecials() {
    }

    public itemsSpecials(String descripcion, String img, int precio, String titulo) {
        this.descripcion = descripcion;
        this.img = img;
        this.precio = precio;
        this.titulo = titulo;
    }
}
