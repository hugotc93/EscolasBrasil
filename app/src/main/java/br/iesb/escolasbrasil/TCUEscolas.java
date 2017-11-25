package br.iesb.escolasbrasil;

import java.util.List;

import br.iesb.escolasbrasil.model.Escola;
import retrofit2.Call;
import retrofit2.http.GET;



public interface TCUEscolas {
    public final static String BASE_URL = "http://mobile-aceite.tcu.gov.br/nossaEscolaRS/";

    @GET("rest/escolas")
    Call<List<Escola>> listarEscola();
}