package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebSalud;

public class seccionesSalud {

    private aboutSalud about;
    private bannerSalud banner;
    private contactoSalud contacto;
    private homeSalud home;
    private horarioSalud horario;
    private serviciosSalud servicios;
    private socialesSalud sociales;

    public aboutSalud getAbout() {
        return about;
    }

    public void setAbout(aboutSalud about) {
        this.about = about;
    }

    public bannerSalud getBanner() {
        return banner;
    }

    public void setBanner(bannerSalud banner) {
        this.banner = banner;
    }

    public contactoSalud getContacto() {
        return contacto;
    }

    public void setContacto(contactoSalud contacto) {
        this.contacto = contacto;
    }

    public homeSalud getHome() {
        return home;
    }

    public void setHome(homeSalud home) {
        this.home = home;
    }

    public horarioSalud getHorario() {
        return horario;
    }

    public void setHorario(horarioSalud horario) {
        this.horario = horario;
    }

    public serviciosSalud getServicios() {
        return servicios;
    }

    public void setServicios(serviciosSalud servicios) {
        this.servicios = servicios;
    }

    public socialesSalud getSociales() {
        return sociales;
    }

    public void setSociales(socialesSalud sociales) {
        this.sociales = sociales;
    }

    public seccionesSalud() {
    }

    public seccionesSalud(aboutSalud about, bannerSalud banner, contactoSalud contacto, homeSalud home, horarioSalud horario, serviciosSalud servicios, socialesSalud sociales) {
        this.about = about;
        this.banner = banner;
        this.contacto = contacto;
        this.home = home;
        this.horario = horario;
        this.servicios = servicios;
        this.sociales = sociales;
    }
}
