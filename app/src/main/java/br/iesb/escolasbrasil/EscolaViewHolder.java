package br.iesb.escolasbrasil;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class EscolaViewHolder extends RecyclerView.ViewHolder{
    public final TextView nome;
    public final TextView municipio;
    public final TextView uf;
    public final Button iraomapa;

    public EscolaViewHolder(View view) {
        super(view);

        nome = view.findViewById(R.id.nome);
        municipio = view.findViewById(R.id.municipio);
        uf = view.findViewById(R.id.uf);
        iraomapa = view.findViewById(R.id.iraomapabutton);
    }
}