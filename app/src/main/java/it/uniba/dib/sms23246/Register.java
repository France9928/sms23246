package it.uniba.dib.sms23246;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Register extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword, editTextFirstName, editTextLastName, editTextAge, editTextPlaceOfBirth, editTextCode;
    String healthOperatorCode = "os12345";
    Button buttonReg;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextFirstName = findViewById(R.id.firstName);
        editTextLastName = findViewById(R.id.lastName);
        editTextAge = findViewById(R.id.age);
        editTextPlaceOfBirth = findViewById(R.id.birthplace);
        editTextCode = findViewById(R.id.code);

        buttonReg = findViewById(R.id.btn_Registration);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginNow);
        textView.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        });

        buttonReg.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            String email, password, firstName, lastName, birthplace, code;
            int age;
            email = String.valueOf(editTextEmail.getText());
            password = String.valueOf(editTextPassword.getText());
            firstName = String.valueOf(editTextFirstName.getText());
            lastName = String.valueOf(editTextLastName.getText());
            birthplace = String.valueOf(editTextPlaceOfBirth.getText());
            code = String.valueOf(editTextCode.getText());

            try {
                age = Integer.parseInt(String.valueOf(editTextAge.getText()));
            } catch (NumberFormatException e) {

                Toast.makeText(Register.this, "Inserisci un'età valida", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                return;
            }

            if (TextUtils.isEmpty(firstName)) {
                Toast.makeText(Register.this, "Inserisci nome", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                return;
            }

            if (TextUtils.isEmpty(lastName)) {
                Toast.makeText(Register.this, "Inserisci cognome", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                return;
            }

            if (TextUtils.isEmpty(birthplace)) {
                Toast.makeText(Register.this, "Inserisci luogo di nascita", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                return;
            }

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(Register.this, "Inserisci email", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(Register.this, "Inserisci Password", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                return;
            }


            if (TextUtils.isEmpty(code)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Register.this, "Account creato",
                                        Toast.LENGTH_SHORT).show();

                                // Salva i dati aggiuntivi in Firestore
                                FirebaseUser user = mAuth.getCurrentUser();
                                saveAdditionalUserData(user.getUid(), firstName, lastName, age, birthplace);

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();

                            } else {
                                // Avvisa l'utente che l'autenticazione è fallita
                                progressBar.setVisibility(View.GONE);

                                Toast.makeText(Register.this, "Autenticazione fallita" + Objects.requireNonNull(task.getException()).getMessage(),
                                        Toast.LENGTH_SHORT).show();

                            }
                        });
            }

            if (!TextUtils.isEmpty(code) && !code.equals(healthOperatorCode)) {
                Toast.makeText(Register.this, "Codice operatore errato", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                return;
            }

            if (!TextUtils.isEmpty(code) && code.equals(healthOperatorCode)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Register.this, "Account operatore creato",
                                        Toast.LENGTH_SHORT).show();

                                // Salva i dati aggiuntivi in Firestore
                                FirebaseUser user = mAuth.getCurrentUser();
                                saveAdditionalOperatorData(user.getUid(), firstName, lastName, age, birthplace);

                                Intent intent = new Intent(getApplicationContext(), operatorActivity.class);
                                startActivity(intent);
                                finish();

                            } else {
                                // Avvisa l'utente che l'autenticazione è fallita
                                progressBar.setVisibility(View.GONE);

                                Toast.makeText(Register.this, "Autenticazione fallita" + Objects.requireNonNull(task.getException()).getMessage(),
                                        Toast.LENGTH_SHORT).show();

                            }
                        });
            }

        });
    }

    private void saveAdditionalUserData(String userId, String firstName, String lastName, int age, String birthplace) {
        // Salva i dati aggiuntivi in Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        user.put("nome", firstName);
        user.put("cognome", lastName);
        user.put("eta", age);
        user.put("luogoDiNascita", birthplace);
        user.put("flag operatore", "no");

        db.collection("utenti").document(userId)
                .set(user)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "Dati utente aggiuntivi salvati con successo in Firestore per l'utente con UID: " + userId))
                .addOnFailureListener(e -> Log.w(TAG, "Errore nel salvataggio dei dati utente aggiuntivi in Firestore per l'utente con UID: " + userId, e));
    }

    private void saveAdditionalOperatorData(String userId, String firstName, String lastName, int age, String birthplace) {
        // Salva i dati aggiuntivi dell'operatore in Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        user.put("nome", firstName);
        user.put("cognome", lastName);
        user.put("eta", age);
        user.put("luogoDiNascita", birthplace);
        user.put("flag operatore", "yes");

        db.collection("utenti").document(userId)
                .set(user)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "Dati utente aggiuntivi salvati con successo in Firestore per l'utente con UID: " + userId))
                .addOnFailureListener(e -> Log.w(TAG, "Errore nel salvataggio dei dati utente aggiuntivi in Firestore per l'utente con UID: " + userId, e));
    }

}