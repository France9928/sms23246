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
                    String patologia1 = documentSnapshot.getString("patologia1");
                    Long patologyLevel1 = (documentSnapshot.getLong("livelloPatologia1"));
                    String patologia2 = documentSnapshot.getString("patologia2");
                    Long patologyLevel2 = (documentSnapshot.getLong("livelloPatologia2"));
                    String patologia3 = documentSnapshot.getString("patologia3");
                    Long patologyLevel3 = (documentSnapshot.getLong("livelloPatologia3"));
                    String patologia4 = documentSnapshot.getString("patologia4");
                    Long patologylevel4 = (documentSnapshot.getLong("livelloPatologia4"));
                    String patologia5 = documentSnapshot.getString("patologia5");
                    Long patologylevel5 = (documentSnapshot.getLong("livelloPatologia5"));

                    userName.setValue(name);
                    userAge.setValue(age);
                    userLastName.setValue(lastName);
                    userBirthplace.setValue(birthplace);
                    nomePatologia1.setValue(patologia1);
                    // Controlla se il valore è null prima di tentare la conversione
                    if (patologyLevel1 != null) {
                        int patologiaLevel1 = patologyLevel1.intValue();
                        livelloPatologia1.setValue(patologiaLevel1);
                    } else {
                        livelloPatologia1.setValue(0);
                    }
                    nomePatologia2.setValue(patologia2);
                    if (patologyLevel2 != null) {
                        int patologiaLevel2 = patologyLevel2.intValue();
                        livelloPatologia2.setValue(patologiaLevel2);
                    } else {
                        livelloPatologia2.setValue(0);
                    }
                    nomePatologia3.setValue(patologia3);
                    if (patologyLevel3 != null) {
                        int patologiaLevel3 = patologyLevel3.intValue();
                        livelloPatologia1.setValue(patologiaLevel3);
                    } else {
                        livelloPatologia3.setValue(0);
                    }
                    nomePatologia4.setValue(patologia4);
                    if (patologylevel4 != null) {
                        int patologiaLevel4 = patologylevel4.intValue();
                        livelloPatologia4.setValue(patologiaLevel4);
                    } else {
                        livelloPatologia4.setValue(0);
                    }
                    nomePatologia5.setValue(patologia5);
                    if (patologylevel5 != null) {
                        int patologiaLevel5 = patologylevel5.intValue();
                        livelloPatologia5.setValue(patologiaLevel5);
                    } else {
                        livelloPatologia5.setValue(0);
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
