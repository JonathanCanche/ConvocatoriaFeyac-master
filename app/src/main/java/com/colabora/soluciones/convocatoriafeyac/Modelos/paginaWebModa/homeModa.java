package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebModa;

public class homeModa {

    private String background;
    private String navbar;
    private String subtitle;
    private String text;
    private String title;

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getNavbar() {
        return navbar;
    }

    public void setNavbar(String navbar) {
        this.navbar = navbar;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public homeModa() {
    }

    public homeModa(String background, String navbar, String subtitle, String text, String title) {
        this.background = background;
        this.navbar = navbar;
        this.subtitle = subtitle;
        this.text = text;
        this.title = title;
    }
}
