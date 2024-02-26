package it.uniba.dib.sms23246;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import it.uniba.dib.sms23246.databinding.ActivityOperatorBinding;

public class operatorActivity extends AppCompatActivity {
    private ActivityOperatorBinding binding;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOperatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        button = findViewById(R.id.logout);

        button.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        });
        setSupportActionBar(findViewById(R.id.toolbar));


        BottomNavigationView navView2 = findViewById(R.id.nav_view2);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_gestioneUtenti, R.id.navigation_datiOperatore, R.id.navigation_cassettaAttrezzi, R.id.navigation_sensori)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_operator);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView2, navController);
        navView2.getMenu().findItem(R.id.navigation_sensori).setEnabled(false);

    }
}