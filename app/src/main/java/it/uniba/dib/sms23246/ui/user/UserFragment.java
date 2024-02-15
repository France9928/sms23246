package it.uniba.dib.sms23246.ui.user;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import it.uniba.dib.sms23246.R;
import it.uniba.dib.sms23246.databinding.FragmentUserBinding;

public class UserFragment extends Fragment {
    private FragmentUserBinding binding;
    private UserViewModel userViewModel;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userViewModel =
                new ViewModelProvider(this).get(UserViewModel.class);

        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView nomeTextView = binding.nomeTextView;
        final TextView cognomeTextView = binding.cognomeTextView;
        final TextView etaTextView = binding.etaTextView;
        final TextView luogoNascitaTextView = binding.luogoNascitaTextView;

        // Ottiene i dati dell'utente da UserViewModel
        userViewModel.getUserName().observe(getViewLifecycleOwner(), nome -> {
            nomeTextView.setText("Nome: " + nome);
        });

        userViewModel.getUserLastName().observe(getViewLifecycleOwner(), cognome -> {
            cognomeTextView.setText("Cognome: " + cognome);
        });

        userViewModel.getUserAge().observe(getViewLifecycleOwner(), age -> {
            etaTextView.setText("Età: " + age);
        });


        final TextView nomePatologiaTextView = binding.nomePatologiaTextView;
        final SeekBar livelloSeekBar = binding.customSeekBar;

        // Ottiene i dati della patologia da UserViewModel
        userViewModel.getNomePatologia().observe(getViewLifecycleOwner(), nomePatologiaTextView::setText);

        userViewModel.getLivelloPatologia().observe(getViewLifecycleOwner(), livelloPatologia -> {
            livelloSeekBar.setProgress(livelloPatologia);

            // Imposta il colore della SeekBar in base al valore di livello
            int progressDrawableId = R.drawable.green_progress_bar;
            if (livelloPatologia == 1) {
                progressDrawableId = R.drawable.yellow_progress_bar;
            } else if (livelloPatologia == 2) {
                progressDrawableId = R.drawable.red_progress_bar;
            }
            livelloSeekBar.setProgressDrawable(ResourcesCompat.getDrawable(getResources(), progressDrawableId, null));
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
