package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebComida;

import java.util.List;

public class menuComida {

    private String header;
    private String navbar;
    private List<itemsMenu> items;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getNavbar() {
        return navbar;
    }

    public void setNavbar(String navbar) {
        this.navbar = navbar;
    }

    public List<itemsMenu> getItems() {
        return items;
    }

    public void setItems(List<itemsMenu> items) {
        this.items = items;
    }

    public menuComida() {
    }

    public menuComida(String header, String navbar, List<itemsMenu> items) {
        this.header = header;
        this.navbar = navbar;
        this.items = items;
    }
}
