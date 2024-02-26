package it.uniba.dib.sms23246.ui.shop;

import static android.widget.Toast.*;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import it.uniba.dib.sms23246.R;
import it.uniba.dib.sms23246.databinding.FragmentShopBinding;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;


public class ShopFragment extends Fragment {
    public FragmentShopBinding binding;
    private EditText editTextNomeProdotto;
    private EditText editTextCategoriaProdotto;
    private EditText editTextCosto;
    private DatePicker editTextData;

    private int year;
    private int month;
    private int day;

    FirebaseFirestore database = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ShopViewModel shopViewModel =
                new ViewModelProvider(this).get(ShopViewModel.class);

        binding = FragmentShopBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inizializza gli elementi UI
        editTextNomeProdotto = root.findViewById(R.id.editTextProduct);
        editTextCategoriaProdotto = root.findViewById(R.id.editTextCategory);
        editTextCosto = root.findViewById(R.id.editTextCost);
        editTextData = root.findViewById(R.id.editData);



        final Button buttonAggiungiSpese = root.findViewById(R.id.buttonShop);

        buttonAggiungiSpese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Ottieni i dati dai campi di input
                String nomeProdotto = editTextNomeProdotto.getText().toString();
                String categoriaProdotto = editTextCategoriaProdotto.getText().toString();
                double costo = Double.parseDouble(editTextCosto.getText().toString());
                // Imposta un listener per la selezione della data

                year = editTextData.getYear();
                month = editTextData.getMonth();
                day = editTextData.getDayOfMonth();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);

                Date dataSelezionata = calendar.getTime();

                if (user != null) {
                    String idUtente = user.getUid();
                    DocumentReference utenteRef = database.collection("utenti").document(idUtente);

                    // Creare un nuovo prodotto
                    Prodotto nuovoProdotto = new Prodotto();
                    nuovoProdotto.setNomeProdotto(nomeProdotto);
                    nuovoProdotto.setCategoriaProdotto(categoriaProdotto.toLowerCase(Locale.getDefault()));
                    nuovoProdotto.setCosto(costo);
                    Timestamp timestamp = new Timestamp(dataSelezionata);
                    nuovoProdotto.setData(timestamp.toDate());

                    // Inserire il prodotto nel sotto-documento "prodotti" di questo utente
                    utenteRef.collection("prodotti").add(nuovoProdotto.toMap())
                            .addOnSuccessListener(documentReference -> {
                                // Gestisci il successo
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("prodotto", (Serializable) nuovoProdotto);

                                Log.d("Contenuto del Bundle", bundle.toString());


                                NavController navController = NavHostFragment.findNavController(ShopFragment.this);
                                navController.navigate(R.id.action_ShopConfirmed, bundle);
                                String prodottoRegistratoSuccessoString = getResources().getString(R.string.prodotto_registrato_successo);
                                makeText(getContext(), prodottoRegistratoSuccessoString , LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                // Gestisci l'errore
                                String erroreRegistrazioneProdottoString = getResources().getString(R.string.prodotto_registrato_fallito);
                                Toast.makeText(getContext(), erroreRegistrazioneProdottoString, Toast.LENGTH_SHORT).show();
                            });
                }
            }
        });

                final Button buttonSpeseSettimanali = root.findViewById(R.id.buttonWeeklyQuery);

                buttonSpeseSettimanali.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NavController navController = NavHostFragment.findNavController(ShopFragment.this);
                        navController.navigate(R.id.action_ShopSettimanali);
                    }
                });

                final Button buttonSpeseMensili = root.findViewById(R.id.buttonMonthlyQuery);

                buttonSpeseMensili.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NavController navController = NavHostFragment.findNavController(ShopFragment.this);
                        navController.navigate(R.id.action_ShopMensili);
                    }
                });

                final Button buttonSpeseCategoria = root.findViewById(R.id.buttonCategoryQuery);

                buttonSpeseCategoria.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NavController navController = NavHostFragment.findNavController(ShopFragment.this);
                        navController.navigate(R.id.action_ShopCategoria);
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
