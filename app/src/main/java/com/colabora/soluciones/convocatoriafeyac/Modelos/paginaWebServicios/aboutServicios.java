package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebServicios;

import java.util.List;

public class aboutServicios {

    private String Sdescripcion;
    private String descripcion;
    private String imagen;
    private String navbar;
    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    private List<caracteristicasAbout> caracteristicas;
    private List<nosotrosAbout> nosotros;

    public String getSdescripcion() {
        return Sdescripcion;
    }

    public void setSdescripcion(String sdescripcion) {
        Sdescripcion = sdescripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNavbar() {
        return navbar;
    }

    public void setNavbar(String navbar) {
        this.navbar = navbar;
    }

    public List<caracteristicasAbout> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<caracteristicasAbout> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public List<nosotrosAbout> getNosotros() {
        return nosotros;
    }

    public void setNosotros(List<nosotrosAbout> nosotros) {
        this.nosotros = nosotros;
    }

    public aboutServicios() {
    }

    public aboutServicios(String sdescripcion, String descripcion, String imagen, String navbar, String titulo, List<caracteristicasAbout> caracteristicas, List<nosotrosAbout> nosotros) {
        Sdescripcion = sdescripcion;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.navbar = navbar;
        this.titulo = titulo;
        this.caracteristicas = caracteristicas;
        this.nosotros = nosotros;
    }
}
