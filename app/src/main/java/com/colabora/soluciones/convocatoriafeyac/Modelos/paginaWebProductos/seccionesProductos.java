package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebProductos;

public class seccionesProductos {

    private aboutProductos about;
    private contactoProductos contacto;
    private galeriaProductos galeria;
    private homeProductos home;
    private imagenContactoProductos imagencontacto;
    private serviciosProductos servicios;

    public aboutProductos getAbout() {
        return about;
    }

    public void setAbout(aboutProductos about) {
        this.about = about;
    }

    public contactoProductos getContacto() {
        return contacto;
    }

    public void setContacto(contactoProductos contacto) {
        this.contacto = contacto;
    }

    public galeriaProductos getGaleria() {
        return galeria;
    }

    public void setGaleria(galeriaProductos galeria) {
        this.galeria = galeria;
    }

    public homeProductos getHome() {
        return home;
    }

    public void setHome(homeProductos home) {
        this.home = home;
    }

    public imagenContactoProductos getImagencontacto() {
        return imagencontacto;
    }

    public void setImagencontacto(imagenContactoProductos imagencontacto) {
        this.imagencontacto = imagencontacto;
    }

    public serviciosProductos getServicios() {
        return servicios;
    }

    public void setServicios(serviciosProductos servicios) {
        this.servicios = servicios;
    }

    public seccionesProductos() {
    }

    public seccionesProductos(aboutProductos about, contactoProductos contacto, galeriaProductos galeria, homeProductos home, imagenContactoProductos imagencontacto, serviciosProductos servicios) {
        this.about = about;
        this.contacto = contacto;
        this.galeria = galeria;
        this.home = home;
        this.imagencontacto = imagencontacto;
        this.servicios = servicios;
    }
}
