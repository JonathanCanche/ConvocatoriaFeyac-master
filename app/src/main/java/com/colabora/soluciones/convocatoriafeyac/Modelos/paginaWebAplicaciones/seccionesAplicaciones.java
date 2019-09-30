package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebAplicaciones;

public class seccionesAplicaciones {

    private bannerAplicacion banner;
    private contactoAplicacion contacto;
    private descargasAplicacion descargas;
    private homeAplicacion home;
    private serviciosAplicacion servicios;

    public bannerAplicacion getBanner() {
        return banner;
    }

    public void setBanner(bannerAplicacion banner) {
        this.banner = banner;
    }

    public contactoAplicacion getContacto() {
        return contacto;
    }

    public void setContacto(contactoAplicacion contacto) {
        this.contacto = contacto;
    }

    public descargasAplicacion getDescargas() {
        return descargas;
    }

    public void setDescargas(descargasAplicacion descargas) {
        this.descargas = descargas;
    }

    public homeAplicacion getHome() {
        return home;
    }

    public void setHome(homeAplicacion home) {
        this.home = home;
    }

    public serviciosAplicacion getServicios() {
        return servicios;
    }

    public void setServicios(serviciosAplicacion servicios) {
        this.servicios = servicios;
    }

    public seccionesAplicaciones() {
    }

    public seccionesAplicaciones(bannerAplicacion banner, contactoAplicacion contacto, descargasAplicacion descargas, homeAplicacion home, serviciosAplicacion servicios) {
        this.banner = banner;
        this.contacto = contacto;
        this.descargas = descargas;
        this.home = home;
        this.servicios = servicios;
    }
}
