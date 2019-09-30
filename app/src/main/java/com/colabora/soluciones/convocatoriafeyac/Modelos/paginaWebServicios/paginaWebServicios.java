package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebServicios;

public class paginaWebServicios {

    private String icon;
    private String idUsuario;
    private int tipo;
    private String url;
    private seccionesServicios secciones;

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

    public seccionesServicios getSecciones() {
        return secciones;
    }

    public void setSecciones(seccionesServicios secciones) {
        this.secciones = secciones;
    }

    public paginaWebServicios() {
    }

    public paginaWebServicios(String icon, String idUsuario, int tipo, String url, seccionesServicios secciones) {
        this.icon = icon;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.url = url;
        this.secciones = secciones;
    }
}
