package it.uniba.dib.sms23246.ui.share;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import it.uniba.dib.sms23246.R;
import it.uniba.dib.sms23246.ui.user.UserViewModel;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;
    private UserViewModel userViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                new ViewModelProvider(this).get(ShareViewModel.class);
        userViewModel =
                new ViewModelProvider(this).get(UserViewModel.class);

        View root = inflater.inflate(R.layout.fragment_share, container, false);

        final TextView nomeTextView = root.findViewById(R.id.nomeTextView);
        final TextView cognomeTextView = root.findViewById(R.id.cognomeTextView);
        final TextView etaTextView = root.findViewById(R.id.etaTextView);

        // Ottieni i dati dell'utente da UserViewModel
        userViewModel.getUserName().observe(getViewLifecycleOwner(), nome -> {
            nomeTextView.setText(nome);
        });

        userViewModel.getUserLastName().observe(getViewLifecycleOwner(), cognome -> {
            cognomeTextView.setText(cognome);
        });

        userViewModel.getUserAge().observe(getViewLifecycleOwner(), eta -> {
            etaTextView.setText(String.valueOf(eta));
        });

        Button shareButton = root.findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ottieni i dati attuali dell'utente
                String nome = nomeTextView.getText().toString();
                String cognome = cognomeTextView.getText().toString();
                String eta = etaTextView.getText().toString();

                // Crea il testo da condividere
                String textToShare = "Nome: " + nome + "\n" +
                        "Cognome: " + cognome + "\n" +
                        "Età: " + eta;

                // Crea l'intent per la condivisione
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
                sendIntent.setType("text/plain");

                // Avvia l'activity per la condivisione
                startActivity(sendIntent);
            }
        });

        return root;
    }
}
