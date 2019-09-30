package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebAplicaciones;

public class contactoAplicacion {

    private String facebook;
    private String google;
    private String titulo;
    private String twitter;

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
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

    public contactoAplicacion() {
    }

    public contactoAplicacion(String facebook, String google, String titulo, String twitter) {
        this.facebook = facebook;
        this.google = google;
        this.titulo = titulo;
        this.twitter = twitter;
    }
}
