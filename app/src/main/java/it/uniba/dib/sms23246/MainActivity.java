package it.uniba.dib.sms23246;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import java.util.Objects;

import it.uniba.dib.sms23246.databinding.ActivityMainBinding;
import it.uniba.dib.sms23246.ui.share.ShareFragment;

public class MainActivity extends AppCompatActivity implements ShareFragment.OnRichiestaInviataListener {

    private FirebaseAuth auth;
    private Button button;
    private TextView textView;
    private FirebaseUser user;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logout);
        textView = findViewById(R.id.user_details);
        user = auth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        if (user == null) {
            redirectToLogin();
        } else {
            textView.setText(user.getEmail());
            checkUserIsHealthOperator(user);
        }

    }

    private void checkUserIsHealthOperator(FirebaseUser user) {
        String userId = user.getUid();
        DocumentReference userDocRef = db.collection("utenti").document(userId);

        userDocRef.addSnapshotListener((documentSnapshot, e) -> {
            if (e != null) {
                // Gestisce errori
                return;
            }

            if (documentSnapshot.exists()) {
                // Verifica che sia un operatore sanitario
                String flagOperatore = documentSnapshot.getString("flag operatore");
                if (Objects.equals(flagOperatore, "yes")) {
                    redirectToOperatorActivity();
                } else {
                    // Se non è un operatore sanitario, fa il setup della MainActivity
                    setupMainActivity();
                }
            }
        });
    }

    private void redirectToLogin() {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
        finish();
    }

    private void redirectToOperatorActivity() {
        Intent intent = new Intent(getApplicationContext(), operatorActivity.class);
        startActivity(intent);
        finish();
    }

    private void setupMainActivity() {
        button.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            redirectToLogin();
        });

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_map, R.id.navigation_user, R.id.navigation_video, R.id.navigation_share, R.id.navigation_shop)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }


    public void onRichiestaInviata(String messaggio) {
        // Implementa la logica di gestione della richiesta inviata dall'utente
        // Ad esempio, puoi aprire un nuovo fragment per accettare la richiesta
        // e passare l'ID dell'utente come argomento
        // Qui puoi gestire l'ID dell'utente inviato dalla richiesta
        // Ad esempio, stampa un messaggio di log
        Log.d("MainActivity", "Richiesta inviata");
    }
}