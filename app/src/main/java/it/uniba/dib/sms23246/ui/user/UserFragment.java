package it.uniba.dib.sms23246.ui.user;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
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

        userViewModel.getUserBirthplace().observe(getViewLifecycleOwner(), birthplace -> {
            luogoNascitaTextView.setText("Luogo di nascita: " + birthplace);
        });


        final TextView nomePatologiaTextView1 = binding.nomePatologiaTextView1;
        final SeekBar livelloSeekBar1 = binding.customSeekBar1;
        final TextView nomePatologiaTextView2 = binding.nomePatologiaTextView2;
        final SeekBar livelloSeekBar2 = binding.customSeekBar2;
        final TextView nomePatologiaTextView3 = binding.nomePatologiaTextView3;
        final SeekBar livelloSeekBar3 = binding.customSeekBar3;
        final TextView nomePatologiaTextView4 = binding.nomePatologiaTextView4;
        final SeekBar livelloSeekBar4 = binding.customSeekBar4;
        final TextView nomePatologiaTextView5 = binding.nomePatologiaTextView5;
        final SeekBar livelloSeekBar5 = binding.customSeekBar5;

        // Ottiene i dati della patologia da UserViewModel
        userViewModel.getNomePatologia1().observe(getViewLifecycleOwner(), nomePatologiaTextView1::setText);
        userViewModel.getLivelloPatologia1().observe(getViewLifecycleOwner(), livelloPatologia -> {
            livelloSeekBar1.setProgress(livelloPatologia);
            // Imposta il colore della SeekBar in base al valore di livello
            int progressDrawableId = R.drawable.grey_progress_bar;
            if (livelloPatologia == 1) {
                progressDrawableId = R.drawable.green_progress_bar;
            } else if (livelloPatologia == 2) {
                progressDrawableId = R.drawable.yellow_progress_bar;
            } else if (livelloPatologia == 3) {
                progressDrawableId = R.drawable.red_progress_bar;
            }
            livelloSeekBar1.setProgressDrawable(ResourcesCompat.getDrawable(getResources(), progressDrawableId, null));
        });

        userViewModel.getNomePatologia2().observe(getViewLifecycleOwner(), nomePatologiaTextView2::setText);
        userViewModel.getLivelloPatologia2().observe(getViewLifecycleOwner(), livelloPatologia -> {
            livelloSeekBar2.setProgress(livelloPatologia);
            // Imposta il colore della SeekBar in base al valore di livello
            int progressDrawableId = R.drawable.grey_progress_bar;
            if (livelloPatologia == 1) {
                progressDrawableId = R.drawable.green_progress_bar;
            } else if (livelloPatologia == 2) {
                progressDrawableId = R.drawable.yellow_progress_bar;
            } else if (livelloPatologia == 3) {
                progressDrawableId = R.drawable.red_progress_bar;
            }
            livelloSeekBar2.setProgressDrawable(ResourcesCompat.getDrawable(getResources(), progressDrawableId, null));
        });

        userViewModel.getNomePatologia3().observe(getViewLifecycleOwner(), nomePatologiaTextView3::setText);
        userViewModel.getLivelloPatologia3().observe(getViewLifecycleOwner(), livelloPatologia -> {
            livelloSeekBar3.setProgress(livelloPatologia);
            // Imposta il colore della SeekBar in base al valore di livello
            int progressDrawableId = R.drawable.grey_progress_bar;
            if (livelloPatologia == 1) {
                progressDrawableId = R.drawable.green_progress_bar;
            } else if (livelloPatologia == 2) {
                progressDrawableId = R.drawable.yellow_progress_bar;
            } else if (livelloPatologia == 3) {
                progressDrawableId = R.drawable.red_progress_bar;
            }
            livelloSeekBar3.setProgressDrawable(ResourcesCompat.getDrawable(getResources(), progressDrawableId, null));
        });

        userViewModel.getNomePatologia4().observe(getViewLifecycleOwner(), nomePatologiaTextView4::setText);
        userViewModel.getLivelloPatologia4().observe(getViewLifecycleOwner(), livelloPatologia -> {
            livelloSeekBar4.setProgress(livelloPatologia);
            // Imposta il colore della SeekBar in base al valore di livello
            int progressDrawableId = R.drawable.grey_progress_bar;
            if (livelloPatologia == 1) {
                progressDrawableId = R.drawable.green_progress_bar;
            } else if (livelloPatologia == 2) {
                progressDrawableId = R.drawable.yellow_progress_bar;
            } else if (livelloPatologia == 3) {
                progressDrawableId = R.drawable.red_progress_bar;
            }
            livelloSeekBar4.setProgressDrawable(ResourcesCompat.getDrawable(getResources(), progressDrawableId, null));
        });

        userViewModel.getNomePatologia5().observe(getViewLifecycleOwner(), nomePatologiaTextView5::setText);
        userViewModel.getLivelloPatologia5().observe(getViewLifecycleOwner(), livelloPatologia -> {
            livelloSeekBar5.setProgress(livelloPatologia);
            // Imposta il colore della SeekBar in base al valore di livello
            int progressDrawableId = R.drawable.grey_progress_bar;
            if (livelloPatologia == 1) {
                progressDrawableId = R.drawable.green_progress_bar;
            } else if (livelloPatologia == 2) {
                progressDrawableId = R.drawable.yellow_progress_bar;
            } else if (livelloPatologia == 3) {
                progressDrawableId = R.drawable.red_progress_bar;
            }
            livelloSeekBar5.setProgressDrawable(ResourcesCompat.getDrawable(getResources(), progressDrawableId, null));
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
