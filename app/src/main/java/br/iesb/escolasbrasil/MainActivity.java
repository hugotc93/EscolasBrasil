package br.iesb.escolasbrasil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.iesb.escolasbrasil.model.Escola;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<Escola> data = new ArrayList<>();
    private EscolaRecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    public Double lat;
    public Double lon;
    public String nomedaescola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadJson();
        initiView();
        //telaMapa();
    }

    private void LoadJson() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TCUEscolas.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TCUEscolas service = retrofit.create(TCUEscolas.class);

        Call<List<Escola>> call = service.listarEscola();

        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Aguarde");
        progressDialog.setTitle("BUSCANDO ESCOLAS");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        call.enqueue(new Callback<List<Escola>>() {
            @Override
            public void onResponse(Call<List<Escola>> call, Response<List<Escola>> response) {

                List<Escola> lista = response.body();

                for (Escola e : lista){
                    Log.d("RETROFIT", String.format("NOME:%s - MUNICIPIO:%s - UF:%s LAT:%f LON:%f", e.getNome(), e.getMunicipio(), e.getUf(), e.getLatitude(), e.getLongitude()));
                    data.add(new Escola(e.getNome(), e.getMunicipio(), e.getUf(), e.getLongitude(), e.getLatitude()));
                    lat = e.getLatitude();
                    lon = e.getLongitude();
                    nomedaescola = e.getNome();
                    recyclerView.setAdapter(new EscolaRecycleViewAdapter(data, MainActivity.this));
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layoutManager);

                    //telaMapa();

                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<Escola>> call, Throwable t) {
                Log.d("ERRO", t.getMessage());
                Toast.makeText(MainActivity.this, "Falha na busca!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initiView(){
        recyclerView = (RecyclerView)findViewById(R.id.recycler_item_lista_escola);
        //telaMapa();
    }

    public void telaMapa(View view){
        Intent it = new Intent(MainActivity.this, MapaActivity.class);
        it.putExtra("latitude", lat);
        it.putExtra("longitude", lon);
        it.putExtra("escolanome", nomedaescola);
        startActivity(it);
    }

}