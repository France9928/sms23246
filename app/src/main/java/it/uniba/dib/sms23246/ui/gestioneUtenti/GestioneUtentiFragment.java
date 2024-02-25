package it.uniba.dib.sms23246.ui.gestioneUtenti;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import it.uniba.dib.sms23246.R;
import it.uniba.dib.sms23246.databinding.FragmentGestioneutentiBinding;

public class GestioneUtentiFragment extends Fragment {

    private FragmentGestioneutentiBinding binding;
    private GestioneUtentiViewModel gestioneUtentiViewModel;
    private FirebaseFirestore db;
    private SharedViewModel sharedViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
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
                                        // Passa il messaggio al nuovo fragment utilizzando il Bundle
                                        //Bundle args = new Bundle();
                                        //args.putString("messaggio", messaggio);
                                        //AggiungiPatologiaFragment aggiungiPatologiaFragment = new AggiungiPatologiaFragment();
                                        // Imposta gli argomenti nel Fragment di destinazione (AggiungiPatologiaFragment)
                                        //aggiungiPatologiaFragment.setArguments(args);
                                        // Imposta il messaggio nel ViewModel
                                        sharedViewModel.setMessaggio(messaggio);

                                        NavController navController = NavHostFragment.findNavController(GestioneUtentiFragment.this);
                                        navController.navigate(R.id.action_gestioneUtenti_to_aggiungiPatologia);
                                        Log.d("AggiungiPatologiaFragment", "Messaggio ricevuto: " + messaggio);
                                    }
                                }
                            }
                        } else {
                            Log.e("GestioneRichieste", "Errore nel recupero delle richieste", task.getException());
                        }
                    });
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
