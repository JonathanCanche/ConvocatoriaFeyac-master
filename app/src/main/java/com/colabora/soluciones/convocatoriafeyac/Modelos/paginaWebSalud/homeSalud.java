package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebSalud;

public class homeSalud {

    private String imagen;
    private String logo;
    private String navbar;
    private String subtitulo;
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

    public homeSalud() {
    }

    public homeSalud(String imagen, String logo, String navbar, String subtitulo, String titulo) {
        this.imagen = imagen;
        this.logo = logo;
        this.navbar = navbar;
        this.subtitulo = subtitulo;
        this.titulo = titulo;
    }
}
