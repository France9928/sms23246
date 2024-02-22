package it.uniba.dib.sms23246.ui.gestioneUtenti;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import it.uniba.dib.sms23246.R;
import it.uniba.dib.sms23246.databinding.FragmentGestioneutentiBinding;

public class GestioneUtentiFragment extends Fragment {

    private FragmentGestioneutentiBinding binding;
    private GestioneUtentiViewModel gestioneUtentiViewModel;
    private RecyclerView recyclerView;
    private UtentiAdapter utentiAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gestioneUtentiViewModel =
                new ViewModelProvider(this).get(GestioneUtentiViewModel.class);

        binding = FragmentGestioneutentiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inizializza il RecyclerView e l'Adapter
        recyclerView = root.findViewById(R.id.recyclerView);
        utentiAdapter = new UtentiAdapter();

        // Collega l'Adapter al RecyclerView
        recyclerView.setAdapter(utentiAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Osserva la lista degli utenti e aggiorna l'Adapter quando cambia
        gestioneUtentiViewModel.getUserList().observe(getViewLifecycleOwner(), utentiAdapter::setUtenti);

        // Carica la lista degli utenti
        gestioneUtentiViewModel.caricaListaUtenti();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}