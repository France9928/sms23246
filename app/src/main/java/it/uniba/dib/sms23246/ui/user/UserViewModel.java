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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserViewModel extends ViewModel {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private MutableLiveData<String> userName = new MutableLiveData<>();
    private MutableLiveData<Integer> userAge = new MutableLiveData<>();
    private MutableLiveData<String> userLastName = new MutableLiveData<>();
    private MutableLiveData<String> userBirthplace = new MutableLiveData<>();
    private MutableLiveData<List<Patologia>> patologieList = new MutableLiveData<>();
    private MutableLiveData<String> userId = new MutableLiveData<>();

    public UserViewModel() {
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // Carica i dati dell'utente corrente quando viene istanziata la view model
        loadUserData();
    }

    LiveData<List<Patologia>> getPatologieList() {
        return patologieList;
    }

    // Metodo per caricare i dati dell'utente corrente da Firestore
    private void loadUserData() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userIde = currentUser.getUid();
            DocumentReference userDocRef = db.collection("utenti").document(userIde);
            userId.setValue(userIde);
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



                    // Ottieni la lista di patologie dell'utente e aggiorna la view model
                    List<Patologia> patologie = new ArrayList<>();
                    for (int i = 1; i <= 5; i++) {
                        String nomePatologia = documentSnapshot.getString("patologia" + i);
                        Long livelloPatologia = documentSnapshot.getLong("livelloPatologia" + i);

                        if (nomePatologia != null && livelloPatologia != null) {
                            int livello = livelloPatologia.intValue();
                            Patologia patologia = new Patologia(nomePatologia, livello);
                            patologie.add(patologia);
                        }
                    }
                    patologieList.setValue(patologie);
                }
            });
        }
    }

    // Metodi per ottenere i dati dell'utente
    public LiveData<String> getUserId(){
        return userId;
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
