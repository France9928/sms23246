package it.uniba.dib.sms23246.ui.shop;

import static android.text.TextUtils.replace;
import static android.widget.Toast.*;

import static androidx.fragment.app.FragmentManagerKt.commit;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;

import it.uniba.dib.sms23246.Login;
import it.uniba.dib.sms23246.MainActivity;
import it.uniba.dib.sms23246.R;
import it.uniba.dib.sms23246.databinding.FragmentShopBinding;
import it.uniba.dib.sms23246.databinding.FragmentShopconfirmedBinding;

public class ShopFragment extends Fragment {
    public FragmentShopBinding binding;
    private EditText editTextNomeProdotto;
    private EditText editTextCategoriaProdotto;
    private EditText editTextCosto;
    private EditText editTextData;

    FirebaseFirestore database = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ShopViewModel shopViewModel =
                new ViewModelProvider(this).get(ShopViewModel.class);

        binding = FragmentShopBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textViewWelcome;
        shopViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Inizializza gli elementi UI
        editTextNomeProdotto = root.findViewById(R.id.editTextProduct);
        editTextCategoriaProdotto = root.findViewById(R.id.editTextCategory);
        editTextCosto = root.findViewById(R.id.editTextCost);
        editTextData = root.findViewById(R.id.editTextDate);

        final Button buttonAggiungiSpese = root.findViewById(R.id.buttonAddExpense);

        buttonAggiungiSpese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registraProdotto();
            }
        });

        final Button buttonSpeseSettimanali = root.findViewById(R.id.buttonWeeklyQuery);

        buttonSpeseSettimanali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Azioni da eseguire quando il pulsante viene cliccato
                //Puoi implementare qui la logica desiderata
            }
        });

        final Button buttonSpeseMensili = root.findViewById(R.id.buttonMonthlyQuery);

        buttonSpeseMensili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Azioni da eseguire quando il pulsante viene cliccato
                //Puoi implementare qui la logica desiderata
            }
        });

        final Button buttonSpeseCategoria = root.findViewById(R.id.buttonCategoryQuery);

        buttonSpeseCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Azioni da eseguire quando il pulsante viene cliccato
                //Puoi implementare qui la logica desiderata
            }
        });


        return root;

    }

    private void registraProdotto() {
        // Ottieni i dati dai campi di input
        String nomeProdotto = editTextNomeProdotto.getText().toString();
        String categoriaProdotto = editTextCategoriaProdotto.getText().toString();
        double costo = Double.parseDouble(editTextCosto.getText().toString());
        String data = editTextData.getText().toString();

        if (user != null) {
            String idUtente = user.getUid();
            DocumentReference utenteRef = database.collection("utenti").document(idUtente);

            // Creare un nuovo prodotto
            Prodotto nuovoProdotto = new Prodotto();
            nuovoProdotto.setNomeProdotto(nomeProdotto);
            nuovoProdotto.setCategoriaProdotto(categoriaProdotto);
            nuovoProdotto.setCosto(costo);
            nuovoProdotto.setData(data);

            // Inserire il prodotto nel sotto-documento "prodotti" di questo utente
            utenteRef.collection("prodotti").add(nuovoProdotto.toMap())
                    .addOnSuccessListener(documentReference -> {
                        // Gestisci il successo
                        visualizzaProdotto(nuovoProdotto);

                        /*ShopConfirmedFragment shopConfirmedFragment = new ShopConfirmedFragment();

                        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentShop, shopConfirmedFragment);
                        fragmentTransaction.addToBackStack(null);  // Aggiungi alla back stack se necessario
                        fragmentTransaction.commit();

                        /*Intent intent = new Intent(getContext(), ShopConfirmedFragment.class);
                        startActivity(intent);*/

                    })
                    .addOnFailureListener(e -> {
                        // Gestisci l'errore
                        Toast.makeText(getContext(), "Errore durante la registrazione del prodotto", Toast.LENGTH_SHORT).show();
                    });
        }
    }

    public void visualizzaProdotto(Prodotto prodotto) {
        // Crea un bundle e aggiungi il prodotto ad esso
        Bundle bundle = new Bundle();
        bundle.putSerializable("prodotto", (Serializable) prodotto);

        // Crea un nuovo fragment e imposta il bundle
        ShopConfirmedFragment shopConfirmedFragment = new ShopConfirmedFragment();
        shopConfirmedFragment.setArguments(bundle);

        // Esegui la transazione per visualizzare il nuovo fragment
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentShop, shopConfirmedFragment);
        transaction.commit();

/*        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragmentShop, shopConfirmedFragment)
                .addToBackStack(null)
                .commit();*/

        Toast.makeText(getContext(), "Prodotto registrato con successo", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
