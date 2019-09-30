package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebComida;

public class seccionesComida {

    private aboutComida about;
    private contactoComida contacto;
    private homeComida home;
    private menuComida menu;
    private specialsComida specials;
    private yellowComida yellow;

    public aboutComida getAbout() {
        return about;
    }

    public void setAbout(aboutComida about) {
        this.about = about;
    }

    public contactoComida getContacto() {
        return contacto;
    }

    public void setContacto(contactoComida contacto) {
        this.contacto = contacto;
    }

    public homeComida getHome() {
        return home;
    }

    public void setHome(homeComida home) {
        this.home = home;
    }

    public menuComida getMenu() {
        return menu;
    }

    public void setMenu(menuComida menu) {
        this.menu = menu;
    }

    public specialsComida getSpecials() {
        return specials;
    }

    public void setSpecials(specialsComida specials) {
        this.specials = specials;
    }

    public yellowComida getYellow() {
        return yellow;
    }

    public void setYellow(yellowComida yellow) {
        this.yellow = yellow;
    }

    public seccionesComida() {
    }

    public seccionesComida(aboutComida about, contactoComida contacto, homeComida home, menuComida menu, specialsComida specials, yellowComida yellow) {
        this.about = about;
        this.contacto = contacto;
        this.home = home;
        this.menu = menu;
        this.specials = specials;
        this.yellow = yellow;
    }
}
