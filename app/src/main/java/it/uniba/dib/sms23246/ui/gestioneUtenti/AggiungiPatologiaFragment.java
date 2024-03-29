package it.uniba.dib.sms23246.ui.gestioneUtenti;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.sms23246.R;

public class AggiungiPatologiaFragment extends Fragment {
    private FirebaseFirestore db;
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
                String nomePatologia = nomePatologiaEditText.getText().toString();
                String livelloPatologiaString = gravitaPatologiaEditText.getText().toString();

                // Verifica se i campi sono vuoti
                if (nomePatologia.isEmpty() || livelloPatologiaString.isEmpty()) {
                    String nomeLivelloStringa = getResources().getString(R.string.inserire_nome_livello_patologia);
                    Toast.makeText(requireContext(), nomeLivelloStringa, Toast.LENGTH_SHORT).show();
                    return;
                }

                // Creazione di un nuovo documento per la patologia
                Map<String, Object> nuovoDocumento = new HashMap<>();
                nuovoDocumento.put("nomePatologia", nomePatologia);
                int livelloPatologia = Integer.parseInt(livelloPatologiaString);
                String aggiuntaSuccessoStringa = getResources().getString(R.string.successo_aggiunta);
                String erroreAggiuntaStringa = getResources().getString(R.string.errore_aggiunta);
                nuovoDocumento.put("livelloPatologia", livelloPatologia);

                assert messaggio != null;
                db.collection("utenti").document(messaggio)
                        .collection("patologie")
                        .add(nuovoDocumento)
                        .addOnSuccessListener(aVoid -> Toast.makeText(requireContext(), aggiuntaSuccessoStringa, Toast.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Toast.makeText(requireContext(), erroreAggiuntaStringa, Toast.LENGTH_SHORT).show());
            }
        });

        return root;
    }
}