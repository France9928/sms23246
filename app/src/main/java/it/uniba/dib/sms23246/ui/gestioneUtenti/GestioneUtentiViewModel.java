package it.uniba.dib.sms23246.ui.gestioneUtenti;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GestioneUtentiViewModel extends ViewModel {
    /*private final MutableLiveData<List<Utente>> userList;

    public GestioneUtentiViewModel() {
        userList = new MutableLiveData<>();
        // Inizializza la lista degli utenti come vuota all'inizio
        userList.setValue(new ArrayList<>());
    }

    public LiveData<List<Utente>> getUserList() {
        return userList;
    }

    // Metodo per aggiornare la lista degli utenti nel ViewModel
    public void updateUserList(List<Utente> users) {
        userList.setValue(users);
    }

    public void caricaListaUtenti() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("utenti")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Utente> utenti = new ArrayList<>();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        // Ottieni i dettagli dell'utente dal documento
                        String nome = document.getString("nome");
                        String cognome = document.getString("cognome");
                        String isOperator = document.getString("flag operatore");
                        if (!Objects.equals(isOperator, "yes")) {
                            // Crea un oggetto User e aggiungilo alla lista
                            @SuppressLint("RestrictedApi") Utente user = new Utente(document.getId(), nome, cognome);
                            utenti.add(user);
                        }
                    }
                    updateUserList(utenti);
                });
    }*/


}