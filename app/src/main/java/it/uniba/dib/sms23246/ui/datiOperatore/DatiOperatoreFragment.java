package it.uniba.dib.sms23246.ui.datiOperatore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import it.uniba.dib.sms23246.databinding.FragmentDatioperatoreBinding;

public class DatiOperatoreFragment extends Fragment {

    private FragmentDatioperatoreBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DatiOperatoreViewModel datiOperatoreViewModel =
                new ViewModelProvider(this).get(DatiOperatoreViewModel.class);

        binding = FragmentDatioperatoreBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView nomeTextView = binding.nomeTextView;
        final TextView cognomeTextView = binding.cognomeTextView;
        final TextView etaTextView = binding.etaTextView;
        final TextView luogoNascitaTextView = binding.luogoNascitaTextView;

        // Ottiene i dati dell'utente da UserViewModel
        datiOperatoreViewModel.getUserName().observe(getViewLifecycleOwner(), nome -> {
            nomeTextView.setText("Nome: " + nome);
        });

        datiOperatoreViewModel.getUserLastName().observe(getViewLifecycleOwner(), cognome -> {
            cognomeTextView.setText("Cognome: " + cognome);
        });

        datiOperatoreViewModel.getUserAge().observe(getViewLifecycleOwner(), age -> {
            etaTextView.setText("Età: " + age);
        });

        datiOperatoreViewModel.getUserBirthplace().observe(getViewLifecycleOwner(), birthplace -> {
            luogoNascitaTextView.setText("Luogo di nascita: " + birthplace);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}