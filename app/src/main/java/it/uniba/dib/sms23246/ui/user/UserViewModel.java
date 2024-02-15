package it.uniba.dib.sms23246.ui.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Objects;

public class UserViewModel extends ViewModel {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    private MutableLiveData<String> userName = new MutableLiveData<>();
    private MutableLiveData<Integer> userAge = new MutableLiveData<>();
    private MutableLiveData<String> userLastName = new MutableLiveData<>();

    private MutableLiveData<String> nomePatologia = new MutableLiveData<>();
    private MutableLiveData<Integer> livelloPatologia = new MutableLiveData<>();

    public UserViewModel() {
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // Carica i dati dell'utente corrente quando viene istanziata la view model
        loadUserData();
        // Carica i dati della patologia corrente quando viene istanziata la view model
        loadPatologiaData();
    }

    // Metodo per caricare i dati dell'utente corrente da Firestore
    private void loadUserData() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            DocumentReference userDocRef = db.collection("utenti").document(userId);

            userDocRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                    if (e != null) {
                        // Gestisci errori
                        return;
                    }

                    if (documentSnapshot.exists()) {
                        // Ottieni i dati dell'utente e aggiorna la view model
                        String name = documentSnapshot.getString("nome");
                        Integer age = Objects.requireNonNull(documentSnapshot.getLong("eta")).intValue();
                        String lastName = documentSnapshot.getString("cognome");
                        String patologia = documentSnapshot.getString("patologia");
                        Integer patologylevel = Objects.requireNonNull(documentSnapshot.getLong("livello patologia")).intValue();

                        userName.setValue(name);
                        userAge.setValue(age);
                        userLastName.setValue(lastName);
                        nomePatologia.setValue(patologia);
                        livelloPatologia.setValue(patologylevel);



                    }
                }
            });
        }
    }

    // Metodo per caricare i dati della patologia corrente da Firestore
    private void loadPatologiaData() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            DocumentReference patologiaDocRef = db.collection("patologie").document(userId);

            patologiaDocRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
                    if (e != null) {
                        // Gestisci errori
                        return;
                    }

                    if (documentSnapshot.exists()) {
                        // Ottieni i dati della patologia e aggiorna la view model
                        String nomePatologia = documentSnapshot.getString("nomePatologia");
                        Integer livelloPatologia = documentSnapshot.getLong("livelloPatologia").intValue();

                        UserViewModel.this.nomePatologia.setValue(nomePatologia);
                        UserViewModel.this.livelloPatologia.setValue(livelloPatologia);
                    }
                }
            });
        }
    }

    // Metodi per ottenere i dati dell'utente
    public LiveData<String> getUserName() {
        return userName;
    }

    public LiveData<Integer> getUserAge() {
        return userAge;
    }

    public LiveData<String> getUserLastName() {
        return userLastName;
    }


    // Metodi per ottenere i dati della patologia
    public LiveData<String> getNomePatologia() {
        return nomePatologia;
    }

    public LiveData<Integer> getLivelloPatologia() {
        return livelloPatologia;
    }
}
