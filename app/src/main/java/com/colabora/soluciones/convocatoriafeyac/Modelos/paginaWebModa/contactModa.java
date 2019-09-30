package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebModa;

public class contactModa {

    private String address;
    private String email;
    private String img;
    private String navbar;
    private String phone;
    private String title;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public contactModa() {
    }

    public contactModa(String address, String email, String img, String navbar, String phone, String title) {
        this.address = address;
        this.email = email;
        this.img = img;
        this.navbar = navbar;
        this.phone = phone;
        this.title = title;
    }
}
