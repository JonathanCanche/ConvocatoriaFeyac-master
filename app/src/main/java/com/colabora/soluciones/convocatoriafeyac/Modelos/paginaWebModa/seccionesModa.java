package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebModa;

import java.util.List;

public class seccionesModa {
    private aboutModa about;
    private contactModa contact;
    private galleryModa gallery;
    private homeModa home;
    private servicesModa services;
    private socialModa social;
    private List<workModa> work;

    public aboutModa getAbout() {
        return about;
    }

    public void setAbout(aboutModa about) {
        this.about = about;
    }

    public contactModa getContact() {
        return contact;
    }

    public void setContact(contactModa contact) {
        this.contact = contact;
    }

    public galleryModa getGallery() {
        return gallery;
    }

    public servicesModa getServices() {
        return services;
    }

    public void setServices(servicesModa services) {
        this.services = services;
    }

    public void setGallery(galleryModa gallery) {
        this.gallery = gallery;
    }

    public homeModa getHome() {
        return home;
    }

    public void setHome(homeModa home) {
        this.home = home;
    }

    public socialModa getSocial() {
        return social;
    }

    public void setSocial(socialModa social) {
        this.social = social;
    }

    public List<workModa> getWork() {
        return work;
    }

    public void setWork(List<workModa> work) {
        this.work = work;
    }

    public seccionesModa() {
    }

    public seccionesModa(aboutModa about, contactModa contact, galleryModa gallery, homeModa home, servicesModa services, socialModa social, List<workModa> work) {
        this.about = about;
        this.contact = contact;
        this.gallery = gallery;
        this.home = home;
        this.services = services;
        this.social = social;
        this.work = work;
    }
}
