package com.colabora.soluciones.convocatoriafeyac.Modelos.paginaWebSalud;

public class horaHorario {

    private String dehora;
    private String dia;
    private String hastahora;

    public String getDehora() {
        return dehora;
    }

    public void setDehora(String dehora) {
        this.dehora = dehora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHastahora() {
        return hastahora;
    }

    public void setHastahora(String hastahora) {
        this.hastahora = hastahora;
    }

    public horaHorario() {
    }

    public horaHorario(String dehora, String dia, String hastahora) {
        this.dehora = dehora;
        this.dia = dia;
        this.hastahora = hastahora;
    }
}
