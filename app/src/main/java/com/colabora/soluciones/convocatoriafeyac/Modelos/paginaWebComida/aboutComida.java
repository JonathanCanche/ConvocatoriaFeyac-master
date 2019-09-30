package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebComida;

public class aboutComida {

    private String background;
    private String header;
    private String img;
    private String navbar;
    private String text;

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public aboutComida() {
    }

    public aboutComida(String background, String header, String img, String navbar, String text) {
        this.background = background;
        this.header = header;
        this.img = img;
        this.navbar = navbar;
        this.text = text;
    }
}
