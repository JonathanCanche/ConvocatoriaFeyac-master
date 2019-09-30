package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebServicios;

import java.util.List;

public class bannerServicios {

    private String descripcion;
    private String titulo;
    private List<cuadroInfoBanner> cuadroInfo;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<cuadroInfoBanner> getCuadroInfo() {
        return cuadroInfo;
    }

    public void setCuadroInfo(List<cuadroInfoBanner> cuadroInfo) {
        this.cuadroInfo = cuadroInfo;
    }

    public bannerServicios() {
    }

    public bannerServicios(String descripcion, String titulo, List<cuadroInfoBanner> cuadroInfo) {
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.cuadroInfo = cuadroInfo;
    }
}
