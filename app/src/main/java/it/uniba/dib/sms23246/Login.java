package it.uniba.dib.sms23246;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;


public class Login extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword;
    Button buttonLogin, buttonAccessoDirettoUtente, buttonAccessoDirettoOperatore;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;
    private FirebaseFirestore db;

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
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.btn_Login);
        buttonAccessoDirettoUtente = findViewById(R.id.btn_accessoDirettoUtente);
        buttonAccessoDirettoOperatore = findViewById(R.id.btn_accessoDirettoOperatore);
        String loginSuccessoString = getResources().getString(R.string.login_effettuato_successo);
        String autenticazioneFallitaString = getResources().getString(R.string.autenticazione_fallita);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.registerNow);
        textView.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Register.class);
            startActivity(intent);
            finish();
        });

        buttonLogin.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            String email, password;
            email = String.valueOf(editTextEmail.getText());
            password = String.valueOf(editTextPassword.getText());

            if (TextUtils.isEmpty(email)) {
                String inserisciEmailString = getResources().getString(R.string.inserisci_email);
                Toast.makeText(Login.this, inserisciEmailString, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                return;
            }

            if (TextUtils.isEmpty(password)) {
                String inserisciPasswordString = getResources().getString(R.string.inserisci_password);
                Toast.makeText(Login.this, inserisciPasswordString, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                return;
            }


            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), loginSuccessoString, Toast.LENGTH_SHORT).show();
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            db = FirebaseFirestore.getInstance();
                            if (currentUser != null) {
                                String userId = currentUser.getUid();
                                DocumentReference userDocRef = db.collection("utenti").document(userId);
                                userDocRef.addSnapshotListener((documentSnapshot, e) -> {
                                    if (documentSnapshot.exists()) {
                                        // Verifica se è un operatore sanitario
                                        String flagOperatore = documentSnapshot.getString("flag operatore");
                                        if (Objects.equals(flagOperatore, "yes")) {
                                            Intent intent = new Intent(getApplicationContext(), operatorActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else {
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                });
                            }
                        }
                        else {
                            // Caso in cui fallisce il login
                            Toast.makeText(Login.this, autenticazioneFallitaString,
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });



        buttonAccessoDirettoUtente.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            String email, password;
            email = "guest@gmail.com";
            password = "guest1";

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), loginSuccessoString, Toast.LENGTH_SHORT).show();
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            db = FirebaseFirestore.getInstance();
                            if (currentUser != null) {
                                String userId = currentUser.getUid();
                                DocumentReference userDocRef = db.collection("utenti").document(userId);
                                userDocRef.addSnapshotListener((documentSnapshot, e) -> {
                                    if (documentSnapshot.exists()) {
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            }
                        }
                        else {
                            // Caso in cui fallisce il login
                            Toast.makeText(Login.this, autenticazioneFallitaString,
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        buttonAccessoDirettoOperatore.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            String email, password;
            email = "guestOperatore@gmail.com";
            password = "guestOperatore1";

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), loginSuccessoString, Toast.LENGTH_SHORT).show();
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            db = FirebaseFirestore.getInstance();
                            if (currentUser != null) {
                                String userId = currentUser.getUid();
                                DocumentReference userDocRef = db.collection("utenti").document(userId);
                                userDocRef.addSnapshotListener((documentSnapshot, e) -> {
                                    if (documentSnapshot.exists()) {
                                        Intent intent = new Intent(getApplicationContext(), operatorActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                            }
                        }
                        else {
                            // Caso in cui fallisce il login
                            Toast.makeText(Login.this, autenticazioneFallitaString,
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}