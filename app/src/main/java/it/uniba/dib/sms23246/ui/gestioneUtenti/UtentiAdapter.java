package it.uniba.dib.sms23246.ui.gestioneUtenti;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

import it.uniba.dib.sms23246.R;

public class UtentiAdapter extends RecyclerView.Adapter<UtentiAdapter.UtenteViewHolder> {

    private List<Utente> utenti = new ArrayList<>();
    private Context context;

    public interface OnAccettaRichiestaListener {
        void onAccettaRichiesta(String messaggio);
    }

    private OnAccettaRichiestaListener accettaRichiestaListener;

    public UtentiAdapter(Context context, OnAccettaRichiestaListener listener) {
        this.context = context;
        this.accettaRichiestaListener = listener;
    }


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

        // Aggiungi un listener per il clic del pulsante
        holder.btnAddPatologia.setOnClickListener(view -> {
            // Chiamare il listener e passare l'ID dell'utente
            if (accettaRichiestaListener != null) {
                accettaRichiestaListener.onAccettaRichiesta(utente.getUserId());
            }
        });

        /* Aggiungi un listener per il clic del pulsante
        holder.btnAddPatologia.setOnClickListener(view -> {
            // Ottieni il NavController dal FragmentActivity
            NavController navController = Navigation.findNavController((Activity) context, R.id.nav_host_fragment_activity_operator);

            // Naviga verso il fragmentWithoutNavBar
            navController.navigate(R.id.action_gestioneUtenti_to_aggiungiPatologia);
        });*/
    }

    @Override
    public int getItemCount() {
        return utenti.size();
    }

    static class UtenteViewHolder extends RecyclerView.ViewHolder {
        private TextView nomeCognomeTextView;
        private Button btnAddPatologia;

        public UtenteViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeCognomeTextView = itemView.findViewById(R.id.user);
            btnAddPatologia = itemView.findViewById(R.id.btnAddPatologia);
        }

        public void bind(Utente user) {
                String nome = user.getNome();
                String cognome = user.getCognome();

                if (nome != null && cognome != null) {
                    String nomeCognome = nome + " " + cognome;
                    nomeCognomeTextView.setText(nomeCognome);
                }
        }
    }
}
