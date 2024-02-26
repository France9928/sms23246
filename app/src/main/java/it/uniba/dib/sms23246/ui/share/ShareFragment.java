package it.uniba.dib.sms23246.ui.share;

import android.content.Context;
import android.content.Intent;
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
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import it.uniba.dib.sms23246.R;
import it.uniba.dib.sms23246.ui.user.UserViewModel;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;
    private UserViewModel userViewModel;

    public interface OnRichiestaInviataListener {
        void onRichiestaInviata(String messaggio);
    }
    private OnRichiestaInviataListener onRichiestaInviataListener;
    private FirebaseFirestore db;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Assicurati che il genitore implementi l'interfaccia OnRichiestaInviataListener
        try {
            onRichiestaInviataListener = (OnRichiestaInviataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " deve implementare OnRichiestaInviataListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel = new ViewModelProvider(this).get(ShareViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);

        final TextView nomeTextView = root.findViewById(R.id.nomeTextView);
        final TextView cognomeTextView = root.findViewById(R.id.cognomeTextView);
        final TextView etaTextView = root.findViewById(R.id.etaTextView);
        final TextView userIdTextView = root.findViewById(R.id.userIdTextView);
        final TextView luogoNascitaTextView = root.findViewById(R.id.luogoNascitaTextView);

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

        userViewModel.getUserId().observe(getViewLifecycleOwner(), userIde -> {
            userIdTextView.setText(String.valueOf(userIde));
        });

        userViewModel.getUserBirthplace().observe(getViewLifecycleOwner(), luogoNascita -> {
            luogoNascitaTextView.setText(luogoNascita);
        });

        Button shareButton = root.findViewById(R.id.shareButton);
        Button shareButton2 = root.findViewById(R.id.shareButton2); // Aggiunto
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userIde = userIdTextView.getText().toString();
                // Invia la richiesta al genitore e a Firestore
                onRichiestaInviataListener.onRichiestaInviata(userIde);
                inviaRichiestaAFirestore(userIde);
            }
        });

        shareButton2.setOnClickListener(new View.OnClickListener() { // Aggiunto
            @Override
            public void onClick(View view) {
                String nome = nomeTextView.getText().toString();
                String cognome = cognomeTextView.getText().toString();
                String eta = etaTextView.getText().toString();
                String userIde = userIdTextView.getText().toString();
                String luogoNascita = luogoNascitaTextView.getText().toString();
                String nomestring = getResources().getString(R.string.nome);
                String cognomestring = getResources().getString(R.string.cognome);
                String etastring = getResources().getString(R.string.age);
                String luogonascitastring = getResources().getString(R.string.place_of_birth);
                String userstring = getResources().getString(R.string.userID);

                String textToShare = nomestring + nome + "\n" +
                        cognomestring + cognome + "\n" +
                        etastring + eta + "\n" +
                        userstring + userIde + "\n" +
                        luogonascitastring + luogoNascita;
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });

        return root;
    }

    private void inviaRichiestaAFirestore(String messaggio) {
        // Aggiungi il messaggio a Firestore
        Map<String, Object> richiesta = new HashMap<>();
        richiesta.put("messaggio", messaggio);
        richiesta.put("timestamp", FieldValue.serverTimestamp());

        db.collection("richieste")
                .add(richiesta)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        String toastRichiesta = getResources().getString(R.string.richiesta_inviata_con_successo);

                        Toast.makeText(requireContext(), toastRichiesta, Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String toastErroreRichiesta = getResources().getString(R.string.errore_invio_richiesta);

                        Toast.makeText(requireContext(), toastErroreRichiesta, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
