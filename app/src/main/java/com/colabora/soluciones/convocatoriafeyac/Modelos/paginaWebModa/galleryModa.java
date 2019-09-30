package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebModa;

import java.util.List;

public class galleryModa {

    private String categories;
    private String navbar;
    private String subtitle;
    private String title;
    private List<imagenModa> imagen;

    public List<imagenModa> getImagen() {
        return imagen;
    }

    public void setImagen(List<imagenModa> imagen) {
        this.imagen = imagen;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getNavbar() {
        return navbar;
    }

    public void setNavbar(String navbar) {
        this.navbar = navbar;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public galleryModa() {
    }

    public galleryModa(String categories, String navbar, String subtitle, String title, List<imagenModa> imagen) {
        this.categories = categories;
        this.navbar = navbar;
        this.subtitle = subtitle;
        this.title = title;
        this.imagen = imagen;
    }
}
