package it.uniba.dib.sms23246.ui.shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

import it.uniba.dib.sms23246.R;

public class ProdottoAdapter extends RecyclerView.Adapter<ProdottoAdapter.ProdottoViewHolder> {

    private List<Prodotto> productList;

    public ProdottoAdapter(List<Prodotto> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProdottoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prodotto_item, parent, false);
        return new ProdottoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdottoViewHolder holder, int position) {
        Prodotto prodotto = productList.get(position);

        // Imposta i dati del prodotto negli elementi del layout
        holder.textViewNome.setText(prodotto.getNomeProdotto());
        holder.textViewCategoria.setText(prodotto.getCategoriaProdotto());
        holder.textViewCosto.setText(String.valueOf((int) prodotto.getCosto()));
        if (prodotto.getData() != null) {
            holder.textViewData.setText(prodotto.getData().toString());
        } else {
            holder.textViewData.setText("Data non disponibile");
        }

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProdottoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNome;
        TextView textViewCategoria;
        TextView textViewCosto;
        TextView textViewData;

        // Aggiungi qui altri TextView per gli altri campi

        public ProdottoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.textViewNome);
            textViewCategoria = itemView.findViewById(R.id.textViewCategoria);
            textViewCosto = itemView.findViewById(R.id.textViewCosto);
            textViewData = itemView.findViewById(R.id.textViewData);
        }
    }
}

