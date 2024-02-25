package it.uniba.dib.sms23246.ui.gestioneUtenti;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.sms23246.R;

public class AggiungiPatologiaFragment extends Fragment {
    private FirebaseFirestore db;
    private String messaggio;
    private SharedViewModel sharedViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_aggiungi_patologia, container, false);

        // Inizializza gli EditText e il pulsante per l'aggiunta della patologia
        EditText nomePatologiaEditText = root.findViewById(R.id.nomePatologiaEditText);
        EditText gravitaPatologiaEditText = root.findViewById(R.id.gravitaPatologiaEditText);
        Button aggiungiPatologiaButton = root.findViewById(R.id.aggiungiPatologiaButton);

        // Recupera il messaggio dal ViewModel
        String messaggio = sharedViewModel.getMessaggio().getValue();

        aggiungiPatologiaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recupera gli argomenti passati
                //if (getArguments() != null) {
                //    messaggio = getArguments().getString("messaggio");
                //}
                Log.d("AggiungiPatologiaFragment", "Messaggio ricevuto: " + messaggio);

                // Questo verrà eseguito quando il pulsante viene cliccato
                String nomePatologia = nomePatologiaEditText.getText().toString();
                String livelloPatologia = gravitaPatologiaEditText.getText().toString();

                // Creazione di un nuovo documento con un campo "valoreNuovoCampo"
                Map<String, Object> nuovoDocumento = new HashMap<>();
                nuovoDocumento.put("nomePatologia", nomePatologia);
                nuovoDocumento.put("livelloPatologia", livelloPatologia);

                db.collection("utenti").document(messaggio)
                        .collection("patologia")
                        .add(nuovoDocumento)
                        .addOnSuccessListener(aVoid -> Log.d("GestioneRichieste", "Campo aggiunto con successo"))
                        .addOnFailureListener(e -> Log.e("GestioneRichieste", "Errore nell'aggiunta del campo", e));
            }
        });

        return root;
    }
}