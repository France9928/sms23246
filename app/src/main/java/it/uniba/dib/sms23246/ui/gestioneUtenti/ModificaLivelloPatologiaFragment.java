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
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.sms23246.R;

public class ModificaLivelloPatologiaFragment extends Fragment {
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
        View root = inflater.inflate(R.layout.fragment_modifica_livello_patologia, container, false);

        // Inizializza gli EditText e il pulsante per l'aggiunta della patologia
        EditText nomeModificaPatologiaEditText = root.findViewById(R.id.nomeModificaPatologiaEditText);
        EditText livelloModificaPatologiaEditText = root.findViewById(R.id.livelloModificaPatologiaEditText);
        Button modificaPatologiaButton = root.findViewById(R.id.modificaPatologiaButton);

        // Recupera il messaggio dal ViewModel
        String messaggio = sharedViewModel.getMessaggio().getValue();

        modificaPatologiaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomePatologia = nomeModificaPatologiaEditText.getText().toString();
                String livelloPatologiaString = livelloModificaPatologiaEditText.getText().toString();

                // Verifica se i campi sono vuoti
                if (nomePatologia.isEmpty() || livelloPatologiaString.isEmpty()) {
                    String nomeLivelloStringa = getResources().getString(R.string.inserire_nome_livello_patologia);
                    Toast.makeText(requireContext(), nomeLivelloStringa, Toast.LENGTH_SHORT).show();
                    return;
                }

                String modificaSuccessoStringa = getResources().getString(R.string.successo_modifica);
                String erroreModificaStringa = getResources().getString(R.string.errore_modifica);
                int livelloPatologia = Integer.parseInt(livelloPatologiaString);

                // Creazione di un nuovo documento con un campo "livelloPatologia"
                Map<String, Object> aggiornamentoLivello = new HashMap<>();
                aggiornamentoLivello.put("livelloPatologia", livelloPatologia);

                assert messaggio != null;
                db.collection("utenti").document(messaggio)
                        .collection("patologie")
                        .whereEqualTo("nomePatologia", nomePatologia)
                        .get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    // Aggiorna il documento esistente con il nuovo livello
                                    db.collection("utenti").document(messaggio)
                                            .collection("patologie")
                                            .document(document.getId())
                                            .update(aggiornamentoLivello)
                                            .addOnSuccessListener(aVoid -> Toast.makeText(requireContext(), modificaSuccessoStringa, Toast.LENGTH_SHORT).show())
                                            .addOnFailureListener(e -> Toast.makeText(requireContext(), erroreModificaStringa, Toast.LENGTH_SHORT).show());
                                }
                            }
                        });
            }
        });

        return root;
    }
}