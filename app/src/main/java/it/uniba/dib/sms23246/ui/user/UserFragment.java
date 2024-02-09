package it.uniba.dib.sms23246.ui.user;

import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import it.uniba.dib.sms23246.R;
import it.uniba.dib.sms23246.databinding.FragmentUserBinding;

public class UserFragment extends Fragment {
    private FragmentUserBinding binding;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UserViewModel userViewModel =
                new ViewModelProvider(this).get(UserViewModel.class);

        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView nomeTextView = binding.nomeTextView;
        final TextView cognomeTextView = binding.cognomeTextView;
        final TextView etaTextView = binding.etaTextView;
        final TextView luogoNascitaTextView = binding.luogoNascitaTextView;

        // Ottiene i dati dell'utente da UserViewModel
        LiveData<UserData> userData = UserViewModel.getUserData();
        userData.observe(getViewLifecycleOwner(), uData -> {
            nomeTextView.setText("Nome: " + uData.getNome());
            cognomeTextView.setText("Cognome: " + uData.getCognome());
            etaTextView.setText("Età: " + uData.getEta());
            luogoNascitaTextView.setText("Luogo di nascita: " + uData.getLuogoNascita());
        });

        final TextView nomePatologiaTextView = binding.nomePatologiaTextView;
        final SeekBar livelloSeekBar = binding.customSeekBar;

        //Ottiene i dati della patologia da UserViewModel
        LiveData<Patologia> patologia = UserViewModel.getPatologia();
        patologia.observe(getViewLifecycleOwner(), patologia1 -> {
            nomePatologiaTextView.setText(patologia1.getNome());

            int livelloValue = patologia1.getLivello().getValoreNumerico();
            livelloSeekBar.setProgress(livelloValue);

            // Imposta il colore della SeekBar in base al valore di livello
            if (livelloValue == 0) {
                livelloSeekBar.setProgressDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.green_progress_bar, null));
            } else if (livelloValue == 1) {
                livelloSeekBar.setProgressDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.yellow_progress_bar, null));
            } else if (livelloValue == 2) {
                livelloSeekBar.setProgressDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.red_progress_bar, null));
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}