package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebProductos;

public class paginaWebProductos {

    private String icon;
    private String idUsuario;
    private int tipo;
    private String url;
    private seccionesProductos secciones;

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

    public seccionesProductos getSecciones() {
        return secciones;
    }

    public void setSecciones(seccionesProductos secciones) {
        this.secciones = secciones;
    }

    public paginaWebProductos() {
    }

    public paginaWebProductos(String icon, String idUsuario, int tipo, String url, seccionesProductos secciones) {
        this.icon = icon;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
        this.url = url;
        this.secciones = secciones;
    }
}
