package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebModa;

public class socialModa {
    private String facebook;
    private String instagram;
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

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public socialModa() {
    }

    public socialModa(String facebook, String instagram, String twitter) {
        this.facebook = facebook;
        this.instagram = instagram;
        this.twitter = twitter;
    }
}
