package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebAplicaciones;

public class homeAplicacion {

    private String imagen;
    private String navbar;
    private String titulo;

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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public homeAplicacion() {
    }

    public homeAplicacion(String imagen, String navbar, String titulo) {
        this.imagen = imagen;
        this.navbar = navbar;
        this.titulo = titulo;
    }
}
