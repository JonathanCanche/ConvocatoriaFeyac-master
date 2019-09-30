package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebProductos;

public class imagenContactoProductos {

    private String imagen;
    private String imagendos;
    private String subtitulo;
    private String titulo;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getImagendos() {
        return imagendos;
    }

    public void setImagendos(String imagendos) {
        this.imagendos = imagendos;
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

    public imagenContactoProductos() {
    }

    public imagenContactoProductos(String imagen, String imagendos, String subtitulo, String titulo) {
        this.imagen = imagen;
        this.imagendos = imagendos;
        this.subtitulo = subtitulo;
        this.titulo = titulo;
    }
}
