package br.iesb.escolasbrasil;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.iesb.escolasbrasil.model.Escola;


public class EscolaRecycleViewAdapter extends RecyclerView.Adapter {
    private List<Escola> escolaLista;
    private Context context;


    public EscolaRecycleViewAdapter(List<Escola> escolaLista, Context context) {
        this.escolaLista = escolaLista;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lista_escola, parent, false);
        EscolaViewHolder holder = new EscolaViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        EscolaViewHolder viewHolder = (EscolaViewHolder) holder;
        Escola escola = escolaLista.get(position);

        viewHolder.nome.setText(escola.getNome());
        viewHolder.municipio.setText(escola.getMunicipio());
        viewHolder.uf.setText(escola.getUf());

    }

    @Override
    public int getItemCount() {
        return escolaLista.size();
    }

}