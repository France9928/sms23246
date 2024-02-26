package it.uniba.dib.sms23246.ui.datiOperatore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import it.uniba.dib.sms23246.R;
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
            String nomeOperatore = getResources().getString(R.string.nome);
            nomeTextView.setText(nomeOperatore + nome);
        });

        datiOperatoreViewModel.getUserLastName().observe(getViewLifecycleOwner(), cognome -> {
            String cognomeOperatore = getResources().getString(R.string.cognome);
            cognomeTextView.setText(cognomeOperatore + cognome);
        });

        datiOperatoreViewModel.getUserAge().observe(getViewLifecycleOwner(), age -> {
            String etaStringa = getResources().getString(R.string.età);
            etaTextView.setText(etaStringa + age);
        });

        datiOperatoreViewModel.getUserBirthplace().observe(getViewLifecycleOwner(), birthplace -> {
            String luogoOperatore = getResources().getString(R.string.place_of_birth);
            luogoNascitaTextView.setText(luogoOperatore + birthplace);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}