package br.iesb.escolasbrasil.model;

/**
 * Created by Gabriel on 25/11/2017.
 */

public class Pessoa {

    String id;
    String nome;
    String sobrenome;
    String email;

    public Pessoa() {

    }

    public Pessoa(String id, String nome, String sobrenome, String email) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }
}
