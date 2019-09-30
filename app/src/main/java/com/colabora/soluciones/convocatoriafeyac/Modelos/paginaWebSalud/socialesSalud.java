package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebSalud;

public class socialesSalud {

    private String facebook;
    private String instagram;
    private String titulo;
    private String twitter;

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

    public socialesSalud() {
    }

    public socialesSalud(String facebook, String instagram, String titulo, String twitter) {
        this.facebook = facebook;
        this.instagram = instagram;
        this.titulo = titulo;
        this.twitter = twitter;
    }
}
