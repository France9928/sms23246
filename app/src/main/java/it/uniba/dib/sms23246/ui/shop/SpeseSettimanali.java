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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSpesesettimanaliBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final Button buttonVisualizzaSpese = root.findViewById(R.id.buttonVisualizzaSpese);

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

                // Ottieni una referenza alla tua raccolta "prodotti"
                CollectionReference prodottiRef = FirebaseFirestore.getInstance().collection("prodotti");

                // Esegui la query
                prodottiRef
                        .whereGreaterThanOrEqualTo("data", dataInizio)
                        .whereLessThanOrEqualTo("data", dataFine)
                        .get()
                        .addOnSuccessListener(queryDocumentSnapshots -> {
                            // Handle dei risultati della query
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                // Ottieni i dati del documento
                                Prodotto prodotto = documentSnapshot.toObject(Prodotto.class);
                                // Crea dinamicamente una TextView per ogni prodotto
                                TextView textViewProdotto = new TextView(requireContext());
                                textViewProdotto.setText(String.format(Locale.getDefault(), "%s - %.2f", prodotto.getNomeProdotto(), prodotto.getCategoriaProdotto(), prodotto.getCosto(), prodotto.getData()));

                                // Ottieni il riferimento al layout in cui vuoi inserire la TextView
                                LinearLayout fragment_SpeseSettimanali = root.findViewById(R.id.fragment_SpeseSettimanali);

                                // Aggiungi la TextView al layout
                                fragment_SpeseSettimanali.addView(textViewProdotto);

                            }
                        })
                        .addOnFailureListener(e -> {
                            // Gestione dell'errore
                            Log.e(TAG, "Errore durante l'esecuzione della query", e);
                        });
            }
        });


        return root;
    }

}
