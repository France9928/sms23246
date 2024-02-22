package it.uniba.dib.sms23246.ui.cassettaAttrezzi;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import it.uniba.dib.sms23246.R;
import it.uniba.dib.sms23246.ui.sensori.Sensori;

public class CassettaAttrezzi extends Fragment {

    private EditText passwordEditText;
    private Button loginButton;

    public CassettaAttrezzi() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cassetta_attrezzi, container, false);
        passwordEditText = view.findViewById(R.id.password_edit_text);
        loginButton = view.findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordEditText.getText().toString();
                if (password.equals("ciao")) {
                    // Password corretta, passaggio al fragment protetto
                    Fragment contentFragment = new Sensori();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_sensori, contentFragment);
                    transaction.commit();
                } else {
                    // Password errata, gestisci di conseguenza (ad esempio, mostra un messaggio di errore)
                    Toast.makeText(getActivity(), "Password errata", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private boolean isPasswordCorrect(String password) {
        // Implementa la tua logica di verifica della password qui
        return password.equals("ciao");
    }
}