package it.uniba.dib.sms23246.ui.shop;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import it.uniba.dib.sms23246.R;
import it.uniba.dib.sms23246.databinding.FragmentShopBinding;
import it.uniba.dib.sms23246.databinding.FragmentSpesesettimanaliBinding;

public class SpeseSettimanali extends Fragment {
    public FragmentSpesesettimanaliBinding binding;
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    private DatePicker editTextData;

    private int year;
    private int month;
    private int day, dayFinale;

    private List<Prodotto> listaProdotti = new ArrayList<>();
    private ProdottoAdapter prodottoAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSpesesettimanaliBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final Button buttonVisualizzaSpese = root.findViewById(R.id.buttonVisualizzaSpese);
        RecyclerView recyclerViewProdotti = root.findViewById(R.id.recyclerViewProdotti);
        prodottoAdapter = new ProdottoAdapter(listaProdotti);
        recyclerViewProdotti.setAdapter(prodottoAdapter);
        recyclerViewProdotti.setLayoutManager(new LinearLayoutManager(getContext()));

        buttonVisualizzaSpese.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                editTextData = root.findViewById(R.id.datePicker);
                year = editTextData.getYear();
                month = editTextData.getMonth();
                day = editTextData.getDayOfMonth();
                dayFinale = editTextData.getDayOfMonth() + 7;

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                Calendar calendarFinale = Calendar.getInstance();
                calendarFinale.set(year, month, dayFinale);


                Date dataSelezionata = calendar.getTime();
                Date dataFinale = calendarFinale.getTime();

                // Definisci le due date di inizio e fine
                Date dataInizio = dataSelezionata;  // Sostituisci con la tua data di inizio
                Date dataFine = dataFinale;    // Sostituisci con la tua data di fine

                if (user != null) {
                    String userId = user.getUid();

                    // Riferimento alla raccolta "prodotti" dell'utente corrente
                    CollectionReference prodottiRef = database.collection("utenti").document(userId).collection("prodotti");

                    // Esegui la query
                    prodottiRef
                            .whereGreaterThanOrEqualTo("dataProdotto", dataInizio)
                            .whereLessThanOrEqualTo("dataProdotto", dataFine)
                            .get()
                            .addOnSuccessListener(queryDocumentSnapshots -> {
                                listaProdotti.clear();  // Pulisci la lista prima di aggiungere nuovi dati

                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    // Ottieni i dati del prodotto
                                    Prodotto prodotto = documentSnapshot.toObject(Prodotto.class);
                                    listaProdotti.add(prodotto);
                                }
                                prodottoAdapter.notifyDataSetChanged();  // Aggiorna l'adapter con i nuovi dati
                            })
                            .addOnFailureListener(e -> {
                                // Gestione dell'errore durante l'esecuzione della query
                                Log.e(TAG, "Errore durante l'esecuzione della query", e);
                            });
                }
            }
        });

        final Button buttonRitornaSpesa = root.findViewById(R.id.buttonShop);
        buttonRitornaSpesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = NavHostFragment.findNavController(SpeseSettimanali.this);
                navController.navigate(R.id.action_Shop);
            }
            });


        final Button buttonSpeseMensili = root.findViewById(R.id.buttonMonthlyQuery);
        buttonSpeseMensili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = NavHostFragment.findNavController(SpeseSettimanali.this);
                navController.navigate(R.id.action_ShopMensili);
            }
        });



        final Button buttonSpeseCategoria = root.findViewById(R.id.buttonCategoryQuery);
        buttonSpeseCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = NavHostFragment.findNavController(SpeseSettimanali.this);
                navController.navigate(R.id.action_ShopCategoria);
            }
        });

        return root;
    }

}
