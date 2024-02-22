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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import it.uniba.dib.sms23246.R;
import it.uniba.dib.sms23246.databinding.FragmentUserBinding;

public class UserFragment extends Fragment {
    private FragmentUserBinding binding;
    private UserViewModel userViewModel;
    private PatologieAdapter patologieAdapter;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding = FragmentUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView nomeTextView = binding.nomeTextView;
        final TextView cognomeTextView = binding.cognomeTextView;
        final TextView etaTextView = binding.etaTextView;
        final TextView luogoNascitaTextView = binding.luogoNascitaTextView;

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            final TextView userIdTextView = binding.userIdTextView;
            userViewModel.getUserId().observe(getViewLifecycleOwner(), userId -> {
                userIdTextView.setText("User Id: " + userId);
            });
        }


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

            RecyclerView recyclerView = binding.patologieRecyclerView;
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            patologieAdapter = new PatologieAdapter();
            recyclerView.setAdapter(patologieAdapter);

            // Osserva le patologie
            userViewModel.getPatologieList().observe(getViewLifecycleOwner(), patologie -> {
                // Aggiorna l'adapter con la nuova lista di patologie
                patologieAdapter.setPatologie(patologie);
            });

            return root;

    }
        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }}

