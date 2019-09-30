package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebComida;

import java.util.List;

public class specialsComida {

    private String header;
    private String navbar;
    private List<itemsSpecials> items;

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

    public List<itemsSpecials> getItems() {
        return items;
    }

    public void setItems(List<itemsSpecials> items) {
        this.items = items;
    }

    public specialsComida() {
    }

    public specialsComida(String header, String navbar, List<itemsSpecials> items) {
        this.header = header;
        this.navbar = navbar;
        this.items = items;
    }
}
