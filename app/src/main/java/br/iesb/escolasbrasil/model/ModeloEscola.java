package br.iesb.escolasbrasil.model;


public class ModeloEscola {
    private final String nomeEscola;
    private final String enderecoEscola;
    private final String telefoneEscola;

    public ModeloEscola(String nomeEscola, String enderecoEscola, String telefoneEscola) {
        this.nomeEscola = nomeEscola;
        this.enderecoEscola = enderecoEscola;
        this.telefoneEscola = telefoneEscola;
    }

    public String getNomeEscola() { return nomeEscola; }

    public String getEnderecoEscola() {
        return enderecoEscola;
    }

    public String getTelefoneEscola() {
        return telefoneEscola;
    }
}
