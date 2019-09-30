package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebModa;

public class aboutModa {
    private String button;
    private String img;
    private String navbar;
    private String subtitle;
    private String text;
    private String title;

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public aboutModa() {
    }

    public aboutModa(String button, String img, String navbar, String subtitle, String text, String title) {
        this.button = button;
        this.img = img;
        this.navbar = navbar;
        this.subtitle = subtitle;
        this.text = text;
        this.title = title;
    }
}
