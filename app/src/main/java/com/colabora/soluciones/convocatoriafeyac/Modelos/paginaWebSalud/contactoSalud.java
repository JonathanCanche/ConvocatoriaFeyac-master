package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebSalud;

public class contactoSalud {

    private String email;
    private String lugar;
    private String navbar;
    private String telefono;
    private String titulo;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public contactoSalud() {
    }

    public contactoSalud(String email, String lugar, String navbar, String telefono, String titulo) {
        this.email = email;
        this.lugar = lugar;
        this.navbar = navbar;
        this.telefono = telefono;
        this.titulo = titulo;
    }
}
