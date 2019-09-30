package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebComida;

import java.util.List;

public class homeComida {

    private String navbar;
    private List<bannersHome> banners;

    public String getNavbar() {
        return navbar;
    }

    public void setNavbar(String navbar) {
        this.navbar = navbar;
    }

    public List<bannersHome> getBanners() {
        return banners;
    }

    public void setBanners(List<bannersHome> banners) {
        this.banners = banners;
    }

    public homeComida() {
    }

    public homeComida(String navbar, List<bannersHome> banners) {
        this.navbar = navbar;
        this.banners = banners;
    }
}
