package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebComida;

public class bannersHome {

    private String img;
    private String texto;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public bannersHome() {
    }

    public bannersHome(String img, String texto) {
        this.img = img;
        this.texto = texto;
    }
}
