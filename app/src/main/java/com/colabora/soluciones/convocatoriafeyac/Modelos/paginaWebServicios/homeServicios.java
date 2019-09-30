package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebServicios;

public class homeServicios {

    private String imagen;
    private String logo;
    private String navbar;
    private String titulo;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public homeServicios() {
    }

    public homeServicios(String imagen, String logo, String navbar, String titulo) {
        this.imagen = imagen;
        this.logo = logo;
        this.navbar = navbar;
        this.titulo = titulo;
    }
}
