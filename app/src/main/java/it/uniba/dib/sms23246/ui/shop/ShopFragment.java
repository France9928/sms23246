package it.uniba.dib.sms23246.ui.shop;

import static android.widget.Toast.*;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import it.uniba.dib.sms23246.R;
import it.uniba.dib.sms23246.databinding.FragmentShopBinding;

//guidovedisefunziona

public class ShopFragment extends Fragment {
    private FragmentShopBinding binding;
    private EditText editTextNomeProdotto;
    private EditText editTextCategoriaProdotto;
    private EditText editTextCosto;
    private EditText editTextData;

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

        // Crea un oggetto Prodotto con i dati
        Prodotto prodotto = new Prodotto();
        prodotto.setNomeProdotto(nomeProdotto);
        prodotto.setCategoriaProdotto(categoriaProdotto);
        prodotto.setCosto(costo);
        prodotto.setData(data);

        // Ottieni il riferimento al tuo database Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Aggiungi il prodotto al database
        db.collection("prodotti")
                .add(prodotto)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // Gestisci il successo
                        makeText(getContext(), "Prodotto registrato con successo", LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Gestisci l'errore
                        makeText(getContext(), "Errore durante la registrazione del prodotto", LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
