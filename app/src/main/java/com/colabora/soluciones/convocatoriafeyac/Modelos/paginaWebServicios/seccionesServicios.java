package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebServicios;

public class seccionesServicios {

    private aboutServicios about;
    private bannerServicios banner;
    private contactoServicios contacto;
    private homeServicios home;
    private portafolioServicios portafolio;
    private serviciosServicios servicios;

    public serviciosServicios getServicios() {
        return servicios;
    }

    public void setServicios(serviciosServicios servicios) {
        this.servicios = servicios;
    }

    public aboutServicios getAbout() {
        return about;
    }

    public void setAbout(aboutServicios about) {
        this.about = about;
    }

    public bannerServicios getBanner() {
        return banner;
    }

    public void setBanner(bannerServicios banner) {
        this.banner = banner;
    }

    public contactoServicios getContacto() {
        return contacto;
    }

    public void setContacto(contactoServicios contacto) {
        this.contacto = contacto;
    }

    public homeServicios getHome() {
        return home;
    }

    public void setHome(homeServicios home) {
        this.home = home;
    }

    public portafolioServicios getPortafolio() {
        return portafolio;
    }

    public void setPortafolio(portafolioServicios portafolio) {
        this.portafolio = portafolio;
    }


    public seccionesServicios() {
    }

    public seccionesServicios(aboutServicios about, bannerServicios banner, contactoServicios contacto, homeServicios home, portafolioServicios portafolio, serviciosServicios servicios) {
        this.about = about;
        this.banner = banner;
        this.contacto = contacto;
        this.home = home;
        this.portafolio = portafolio;
        this.servicios = servicios;
    }
}
