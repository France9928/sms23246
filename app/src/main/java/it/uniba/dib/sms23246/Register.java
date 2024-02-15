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

    TextInputEditText editTextEmail, editTextPassword, editTextFirstName, editTextLastName, editTextAge, editTextPlaceOfBirth;
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
        buttonReg = findViewById(R.id.btn_Registration);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginNow);
        textView.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        });

        buttonReg.setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            String email, password, firstName, lastName, birthplace;
            int age;
            email = String.valueOf(editTextEmail.getText());
            password = String.valueOf(editTextPassword.getText());
            firstName = String.valueOf(editTextFirstName.getText());
            lastName = String.valueOf(editTextLastName.getText());
            birthplace = String.valueOf(editTextPlaceOfBirth.getText());

            try {
                age = Integer.parseInt(String.valueOf(editTextAge.getText()));
            } catch (NumberFormatException e) {
                Toast.makeText(Register.this, "Enter a valid age", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                return;
            }

            if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(birthplace) ) {
                Toast.makeText(Register.this, "Enter all required information", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                return;
            }

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(Register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                return;
            }


            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Register.this, "Account created.",
                                    Toast.LENGTH_SHORT).show();

                            // Salva i dati aggiuntivi in Firestore
                            FirebaseUser user = mAuth.getCurrentUser();
                            saveAdditionalUserData(user.getUid(), firstName, lastName, age, birthplace);

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(Register.this, "Authentication failed." + Objects.requireNonNull(task.getException()).getMessage(),
                                    Toast.LENGTH_SHORT).show();

                        }
                    });


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

        db.collection("utenti").document(userId)
                .set(user)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "Dati utente aggiuntivi salvati con successo in Firestore per l'utente con UID: " + userId))
                .addOnFailureListener(e -> Log.w(TAG, "Errore nel salvataggio dei dati utente aggiuntivi in Firestore per l'utente con UID: " + userId, e));
    }

}
