package it.uniba.dib.sms23246.ui.gestioneUtenti;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

import it.uniba.dib.sms23246.R;

public class UtentiAdapter extends RecyclerView.Adapter<UtentiAdapter.UtenteViewHolder> {

    private List<Utente> utenti = new ArrayList<>();

    public void setUtenti(List<Utente> utenti) {
        this.utenti = utenti;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UtenteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UtenteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UtenteViewHolder holder, int position) {
        Utente utente = utenti.get(position);
        holder.bind(utente);
    }

    @Override
    public int getItemCount() {
        return utenti.size();
    }

    static class UtenteViewHolder extends RecyclerView.ViewHolder {
        private TextView nomeCognomeTextView;

        public UtenteViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeCognomeTextView = itemView.findViewById(R.id.userId);
        }

        public void bind(Utente user) {
                String nome = user.getNome();
                String cognome = user.getCognome();
                int eta = user.getEta();

                if (nome != null && cognome != null) {
                    String nomeCognome = nome + " " + cognome;
                    nomeCognomeTextView.setText(nomeCognome);
                }
        }
    }
}
