package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebAplicaciones;

public class descargasAplicacion {

    private String botonaps;
    private String botonplay;
    private String subtitulo;
    private String titulo;

    public String getBotonaps() {
        return botonaps;
    }

    public void setBotonaps(String botonaps) {
        this.botonaps = botonaps;
    }

    public String getBotonplay() {
        return botonplay;
    }

    public void setBotonplay(String botonplay) {
        this.botonplay = botonplay;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public descargasAplicacion() {
    }

    public descargasAplicacion(String botonaps, String botonplay, String subtitulo, String titulo) {
        this.botonaps = botonaps;
        this.botonplay = botonplay;
        this.subtitulo = subtitulo;
        this.titulo = titulo;
    }
}
