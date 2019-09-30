package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebProductos;

public class contactoProductos {

    private String email;
    private String facebook;
    private String instagram;
    private String lugar;
    private String navbar;
    private String telefono;
    private String titulo;
    private String twitter;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getNavbar() {
        return navbar;
    }

    public void setNavbar(String navbar) {
        this.navbar = navbar;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public contactoProductos() {
    }

    public contactoProductos(String email, String facebook, String instagram, String lugar, String navbar, String telefono, String titulo, String twitter) {
        this.email = email;
        this.facebook = facebook;
        this.instagram = instagram;
        this.lugar = lugar;
        this.navbar = navbar;
        this.telefono = telefono;
        this.titulo = titulo;
        this.twitter = twitter;
    }
}
