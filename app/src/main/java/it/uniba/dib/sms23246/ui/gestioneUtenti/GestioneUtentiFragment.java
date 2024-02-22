package it.uniba.dib.sms23246.ui.gestioneUtenti;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
        utentiAdapter = new UtentiAdapter(requireContext());

        // Collega l'Adapter al RecyclerView
        recyclerView.setAdapter(utentiAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Osserva la lista degli utenti e aggiorna l'Adapter quando cambia
        gestioneUtentiViewModel.getUserList().observe(getViewLifecycleOwner(), utentiAdapter::setUtenti);

        // Carica la lista degli utenti
        gestioneUtentiViewModel.caricaListaUtenti();

        return root;
    }

    public void apriAggiungiPatologiaFragment(String userId) {
        // Crea un nuovo fragment e passa l'ID dell'utente come argomento
        AggiungiPatologiaFragment fragment = new AggiungiPatologiaFragment();
        Bundle args = new Bundle();
        args.putString("userId", userId);
        fragment.setArguments(args);

        // Sostituisci il fragment corrente con il nuovo fragment
        // Replace the current fragment with the new fragment
        getParentFragmentManager().beginTransaction()
                .replace(R.id.navigation_gestioneUtenti, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}