package it.uniba.dib.sms23246.ui.cassettaAttrezzi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import it.uniba.dib.sms23246.R;

public class CassettaAttrezzi extends Fragment {

    private static final String CORRECT_PASSWORD = "password123"; // Imposta la tua password corretta qui
    private EditText passwordEditText;
    private Button loginButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cassetta_attrezzi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        passwordEditText = view.findViewById(R.id.passwordEditText);
        loginButton = view.findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordEditText.getText().toString();
                if (password.equals(CORRECT_PASSWORD)) {
                    // Password corretta, apri il fragment dei sensori
                    NavController navController = NavHostFragment.findNavController(CassettaAttrezzi.this);
                    navController.navigate(R.id.action_cassettaAttrezzi_to_sensori);
                } else {
                    // Password errata, gestisci di conseguenza
                    // Ad esempio, mostra un messaggio di errore
                }
            }
        });
    }
}
