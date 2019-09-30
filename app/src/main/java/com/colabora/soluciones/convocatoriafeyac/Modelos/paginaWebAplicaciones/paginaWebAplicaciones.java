package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebAplicaciones;

public class paginaWebAplicaciones {

    private String icon;
    private String idUsuario;
    private int tipo;
    private String url;
    private seccionesAplicaciones secciones;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public seccionesAplicaciones getSecciones() {
        return secciones;
    }

    public void setSecciones(seccionesAplicaciones secciones) {
        this.secciones = secciones;
    }

    public paginaWebAplicaciones() {
    }

    public paginaWebAplicaciones(String icon, String idUsuario, int tipo, String url, seccionesAplicaciones secciones) {
        this.icon = icon;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.url = url;
        this.secciones = secciones;
    }
}
