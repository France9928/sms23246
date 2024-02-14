package it.uniba.dib.sms23246;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import it.uniba.dib.sms23246.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


        FirebaseAuth auth;
        Button button;

        TextView textView;
        FirebaseUser user;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            it.uniba.dib.sms23246.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());


            auth =FirebaseAuth.getInstance();
            button = findViewById(R.id.logout);
            textView = findViewById(R.id.user_details);
            user = auth.getCurrentUser();
            if(user == null){
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();

            }
            else{
                textView.setText(user.getEmail());
            }

            button.setOnClickListener(v -> {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();


            });


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
            AppBarConfiguration appBarConfiguration;
            appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_map, R.id.navigation_user, R.id.navigation_video, R.id.navigation_share, R.id.navigation_shop)
                    .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}