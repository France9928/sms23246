package it.uniba.dib.sms23246.ui.datiOperatore;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class DatiOperatoreViewModel extends ViewModel {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private MutableLiveData<String> userName = new MutableLiveData<>();
    private MutableLiveData<Integer> userAge = new MutableLiveData<>();
    private MutableLiveData<String> userLastName = new MutableLiveData<>();
    private MutableLiveData<String> userBirthplace = new MutableLiveData<>();

    public DatiOperatoreViewModel() {
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // Carica i dati dell'utente corrente quando viene istanziata la view model
        loadUserData();
    }

    // Metodo per caricare i dati dell'utente corrente da Firestore
    private void loadUserData() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            DocumentReference userDocRef = db.collection("utenti").document(userId);

            userDocRef.addSnapshotListener((documentSnapshot, e) -> {
                if (e != null) {
                    // Gestisci errori
                    return;
                }

                if (documentSnapshot.exists()) {
                    // Ottieni i dati dell'utente e aggiorna la view model
                    String name = documentSnapshot.getString("nome");
                    Integer age = Objects.requireNonNull(documentSnapshot.getLong("eta")).intValue();
                    String lastName = documentSnapshot.getString("cognome");
                    String birthplace = documentSnapshot.getString("luogoDiNascita");

                    userName.setValue(name);
                    userAge.setValue(age);
                    userLastName.setValue(lastName);
                    userBirthplace.setValue(birthplace);

                }
            });
        }
    }

    public LiveData<String> getUserName() {
        return userName;
    }
    public LiveData<Integer> getUserAge() {
        return userAge;
    }
    public LiveData<String> getUserLastName() {
        return userLastName;
    }
    public LiveData<String> getUserBirthplace() {
        return userBirthplace;
    }

}