package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebComida;

public class contactoComida {

    private String adress;
    private String email;
    private String facebook_url;
    private String header;
    private String instagram_url;
    private String navbar;
    private String nombre_reservaciones;
    private String nombre_restaurante;
    private String phone;

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook_url() {
        return facebook_url;
    }

    public void setFacebook_url(String facebook_url) {
        this.facebook_url = facebook_url;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getInstagram_url() {
        return instagram_url;
    }

    public void setInstagram_url(String instagram_url) {
        this.instagram_url = instagram_url;
    }

    public String getNavbar() {
        return navbar;
    }

    public void setNavbar(String navbar) {
        this.navbar = navbar;
    }

    public String getNombre_reservaciones() {
        return nombre_reservaciones;
    }

    public void setNombre_reservaciones(String nombre_reservaciones) {
        this.nombre_reservaciones = nombre_reservaciones;
    }

    public String getNombre_restaurante() {
        return nombre_restaurante;
    }

    public void setNombre_restaurante(String nombre_restaurante) {
        this.nombre_restaurante = nombre_restaurante;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public contactoComida() {
    }

    public contactoComida(String adress, String email, String facebook_url, String header, String instagram_url, String navbar, String nombre_reservaciones, String nombre_restaurante, String phone) {
        this.adress = adress;
        this.email = email;
        this.facebook_url = facebook_url;
        this.header = header;
        this.instagram_url = instagram_url;
        this.navbar = navbar;
        this.nombre_reservaciones = nombre_reservaciones;
        this.nombre_restaurante = nombre_restaurante;
        this.phone = phone;
    }
}
