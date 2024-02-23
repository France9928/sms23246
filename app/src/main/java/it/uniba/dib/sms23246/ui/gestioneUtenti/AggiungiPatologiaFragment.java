package it.uniba.dib.sms23246.ui.gestioneUtenti;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import it.uniba.dib.sms23246.R;

public class AggiungiPatologiaFragment extends Fragment {
    // ...

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_aggiungi_patologia, container, false);

        // Recupera l'ID dell'utente dall'argomento o da un altro modo
        //String userId = getArguments().getString("userId");

        // Inizializza gli EditText e il pulsante per l'aggiunta della patologia
        EditText nomePatologiaEditText = root.findViewById(R.id.nomePatologiaEditText);
        EditText gravitaPatologiaEditText = root.findViewById(R.id.gravitaPatologiaEditText);
        Button aggiungiPatologiaButton = root.findViewById(R.id.aggiungiPatologiaButton);

        aggiungiPatologiaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Questo verrà eseguito quando il pulsante viene cliccato
                String nomePatologia = nomePatologiaEditText.getText().toString();
                String gravitaPatologia = gravitaPatologiaEditText.getText().toString();

                // Fai qualcosa con i valori
            }
        });

        return root;
    }
}