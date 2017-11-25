package br.iesb.escolasbrasil.model;

/**
 * Created by hugot on 23/11/2017.
 */

public class Escola {

    private final String nome;
    private final String municipio;
    private final String uf;
    private final Double longitude;
    private final Double latitude;

    public Escola(String nome, String municipio, String uf, Double longitude, Double latitude) {
        this.nome = nome;
        this.municipio = municipio;
        this.uf = uf;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getNome() {
        return nome;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getUf() {
        return uf;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }
}