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

public class GestioneUtentiViewModel extends ViewModel {
    private final MutableLiveData<List<User>> userList;

    public GestioneUtentiViewModel() {
        userList = new MutableLiveData<>();
        // Inizializza la lista degli utenti come vuota all'inizio
        userList.setValue(new ArrayList<>());
    }

    public LiveData<List<User>> getUserList() {
        return userList;
    }

    // Metodo per aggiornare la lista degli utenti nel ViewModel
    public void updateUserList(List<User> users) {
        userList.setValue(users);
    }

    public void caricaListaUtenti() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("utenti")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<User> utenti = new ArrayList<>();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        // Ottieni i dettagli dell'utente dal documento
                        String nome = document.getString("nome");
                        String cognome = document.getString("cognome");
                        int eta = document.getLong("eta").intValue();

                        // Crea un oggetto User e aggiungilo alla lista
                        @SuppressLint("RestrictedApi") User user = new User(document.getId());
                        utenti.add(user);
                    }
                    updateUserList(utenti);
                })
                .addOnFailureListener(e -> {
                    // Gestisci errori durante il recupero degli utenti
                });
    }
}