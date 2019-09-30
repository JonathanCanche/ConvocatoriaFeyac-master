package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebModa;

import java.util.List;

public class servicesModa {

    private String img;
    private String navbar;
    private List<listServicesModa>  list;

    public List<listServicesModa> getList() {
        return list;
    }

    public void setList(List<listServicesModa> list) {
        this.list = list;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNavbar() {
        return navbar;
    }

    public void setNavbar(String navbar) {
        this.navbar = navbar;
    }

    public servicesModa() {
    }

    public servicesModa(String img, String navbar, List<listServicesModa> list) {
        this.img = img;
        this.navbar = navbar;
        this.list = list;
    }
}
