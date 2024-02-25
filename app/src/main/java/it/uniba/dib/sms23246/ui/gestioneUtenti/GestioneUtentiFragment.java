package it.uniba.dib.sms23246.ui.gestioneUtenti;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.sms23246.R;
import it.uniba.dib.sms23246.databinding.FragmentGestioneutentiBinding;
import it.uniba.dib.sms23246.ui.cassettaAttrezzi.CassettaAttrezzi;

public class GestioneUtentiFragment extends Fragment {

    private FragmentGestioneutentiBinding binding;
    private GestioneUtentiViewModel gestioneUtentiViewModel;
    private FirebaseFirestore db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gestioneUtentiViewModel =
                new ViewModelProvider(this).get(GestioneUtentiViewModel.class);

        binding = FragmentGestioneutentiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button btnAccettaRichiesta = root.findViewById(R.id.btnAddPatologia);
        btnAccettaRichiesta.setOnClickListener(v -> {
            // Qui dovresti implementare la logica per accettare la richiesta.
            // Ad esempio, puoi aggiornare il documento Firestore associato all'utente
            // o notificare l'operatore sanitario dell'azione compiuta
            // tramite una Toast o un altro meccanismo di notifica.
            db.collection("richieste")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Recupera il timestamp dal documento
                                Timestamp timestamp = (Timestamp) document.get("timestamp");

                                // Verifica se la richiesta è scaduta in base al timestamp
                                if (timestamp != null) {
                                    long currentTime = System.currentTimeMillis();
                                    long requestTime = timestamp.getSeconds() * 1000 + timestamp.getNanoseconds() / 1_000_000; //tempo in millisecondi
                                    long limiteTempo = 60 * 60 * 1000; // 1 ora in millisecondi

                                    if (currentTime - requestTime > limiteTempo) {
                                        // La richiesta è scaduta, esegui l'azione appropriata (rimuovila, notifica l'utente, ecc.)
                                        String requestId = document.getId();
                                        Toast.makeText(requireContext(), "Richiesta scaduta", Toast.LENGTH_SHORT).show();
                                        // Esegui l'azione per la richiesta scaduta
                                        rimuoviRichiestaScaduta(requestId);
                                    }
                                    else {
                                        // La richiesta non è scaduta, recupera il messaggio
                                        String messaggio = document.getString("messaggio");
                                        Toast.makeText(requireContext(), "Richiesta ancora attiva", Toast.LENGTH_SHORT).show();
                                        NavController navController = NavHostFragment.findNavController(GestioneUtentiFragment.this);
                                        AggiungiPatologiaFragment aggiungiPatologiaFragment = new AggiungiPatologiaFragment();
                                        aggiungiPatologiaFragment.setMessaggio(messaggio);
                                        navController.navigate(R.id.action_gestioneUtenti_to_aggiungiPatologia);
                                        Log.d("AggiungiPatologiaFragment", "Messaggio ricevuto: " + messaggio);
                                    }
                                }
                            }
                        } else {
                            Log.e("GestioneRichieste", "Errore nel recupero delle richieste", task.getException());
                        }
                    });


            // Esempio di notifica con Toast
            //Toast.makeText(requireContext(), "Richiesta accettata", Toast.LENGTH_SHORT).show();

            // Puoi aggiornare eventualmente il documento Firestore associato all'utente
            // es. db.collection("utenti").document(userId).update("stato", "accettato");
        });

        return root;
    }

    private void rimuoviRichiestaScaduta(String requestId) {
        // Rimuovi la richiesta dal database
        db.collection("richieste").document(requestId)
                .delete()
                .addOnSuccessListener(aVoid -> Log.d("GestioneRichieste", "Richiesta rimossa: " + requestId))
                .addOnFailureListener(e -> Log.e("GestioneRichieste", "Errore nella rimozione della richiesta", e));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}