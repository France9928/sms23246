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
    private MutableLiveData<String> userBirthplace = new MutableLiveData<>();
    private MutableLiveData<String> nomePatologia1 = new MutableLiveData<>();
    private MutableLiveData<Integer> livelloPatologia1 = new MutableLiveData<>();
    private MutableLiveData<String> nomePatologia2 = new MutableLiveData<>();
    private MutableLiveData<Integer> livelloPatologia2 = new MutableLiveData<>();
    private MutableLiveData<String> nomePatologia3 = new MutableLiveData<>();
    private MutableLiveData<Integer> livelloPatologia3 = new MutableLiveData<>();
    private MutableLiveData<String> nomePatologia4 = new MutableLiveData<>();
    private MutableLiveData<Integer> livelloPatologia4 = new MutableLiveData<>();
    private MutableLiveData<String> nomePatologia5 = new MutableLiveData<>();
    private MutableLiveData<Integer> livelloPatologia5 = new MutableLiveData<>();

    public UserViewModel() {
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
                        String birthplace = documentSnapshot.getString("luogoDiNascita");
                        String patologia1 = documentSnapshot.getString("patologia1");
                        Integer patologylevel1 = Objects.requireNonNull(documentSnapshot.getLong("livelloPatologia1")).intValue();
                        String patologia2 = documentSnapshot.getString("patologia2");
                        Integer patologylevel2 = Objects.requireNonNull(documentSnapshot.getLong("livelloPatologia2")).intValue();
                        String patologia3 = documentSnapshot.getString("patologia3");
                        Integer patologylevel3 = Objects.requireNonNull(documentSnapshot.getLong("livelloPatologia3")).intValue();
                        String patologia4 = documentSnapshot.getString("patologia4");
                        Integer patologylevel4 = Objects.requireNonNull(documentSnapshot.getLong("livelloPatologia4")).intValue();
                        String patologia5 = documentSnapshot.getString("patologia5");
                        Integer patologylevel5 = Objects.requireNonNull(documentSnapshot.getLong("livelloPatologia5")).intValue();

                        userName.setValue(name);
                        userAge.setValue(age);
                        userLastName.setValue(lastName);
                        userBirthplace.setValue(birthplace);
                        nomePatologia1.setValue(patologia1);
                        livelloPatologia1.setValue(patologylevel1);
                        nomePatologia2.setValue(patologia2);
                        livelloPatologia2.setValue(patologylevel2);
                        nomePatologia3.setValue(patologia3);
                        livelloPatologia3.setValue(patologylevel3);
                        nomePatologia4.setValue(patologia4);
                        livelloPatologia4.setValue(patologylevel4);
                        nomePatologia5.setValue(patologia5);
                        livelloPatologia5.setValue(patologylevel5);

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
    public LiveData<String> getUserBirthplace() {
        return userBirthplace;
    }
    public LiveData<String> getNomePatologia1() {
        return nomePatologia1;
    }
    public LiveData<Integer> getLivelloPatologia1() {
        return livelloPatologia1;
    }
    public LiveData<String> getNomePatologia2() {
        return nomePatologia2;
    }
    public LiveData<Integer> getLivelloPatologia2() {
        return livelloPatologia2;
    }
    public LiveData<String> getNomePatologia3() {
        return nomePatologia3;
    }
    public LiveData<Integer> getLivelloPatologia3() {
        return livelloPatologia3;
    }
    public LiveData<String> getNomePatologia4() {
        return nomePatologia4;
    }
    public LiveData<Integer> getLivelloPatologia4() {
        return livelloPatologia4;
    }
    public LiveData<String> getNomePatologia5() {
        return nomePatologia5;
    }
    public LiveData<Integer> getLivelloPatologia5() {
        return livelloPatologia5;
    }

}
