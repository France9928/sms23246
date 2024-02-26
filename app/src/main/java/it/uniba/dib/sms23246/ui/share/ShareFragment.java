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
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userIde = userIdTextView.getText().toString();
                // Invia la richiesta al genitore e a Firestore
                onRichiestaInviataListener.onRichiestaInviata(userIde);
                inviaRichiestaAFirestore(userIde);
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
                        Toast.makeText(requireContext(), "Richiesta inviata con successo", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(requireContext(), "Errore invio richiesta", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
