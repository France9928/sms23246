package it.uniba.dib.sms23246.ui.shop;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import it.uniba.dib.sms23246.R;
import it.uniba.dib.sms23246.databinding.FragmentSpesecategoriaBinding;

public class SpeseCategoriaFragment extends Fragment {
    public FragmentSpesecategoriaBinding binding;
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private EditText editTextCategoriaProdotto;

    private List<Prodotto> listaProdotti = new ArrayList<>();
    private ProdottoAdapter prodottoAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ShopConfirmedViewModel shopConfirmedViewModel =
                new ViewModelProvider(this).get(ShopConfirmedViewModel.class);

        // Utilizza il metodo inflate per ottenere un'istanza del binding
        binding = FragmentSpesecategoriaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button buttonSpeseCategoria = root.findViewById(R.id.buttonVisualizzaSpese);

        RecyclerView recyclerViewProdotti = root.findViewById(R.id.recyclerViewProdotti);
        prodottoAdapter = new ProdottoAdapter(listaProdotti);
        recyclerViewProdotti.setAdapter(prodottoAdapter);
        recyclerViewProdotti.setLayoutManager(new LinearLayoutManager(getContext()));

        editTextCategoriaProdotto = root.findViewById(R.id.TextCategoria);

        String categoriaProdotto = editTextCategoriaProdotto.getText().toString();

        buttonSpeseCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Controlla se l'utente è loggato
                if (user != null) {
                    String userId = user.getUid();

                    // Ottieni una referenza alla tua raccolta 'prodotti' dell'utente
                    CollectionReference prodottiRef = FirebaseFirestore.getInstance()
                            .collection("utenti")
                            .document(userId)
                            .collection("prodotti");

                    // Specifica la categoria desiderata (sostituisci con la tua categoria)
                    String categoriaDesiderata = categoriaProdotto;

                    // Converti la categoria in minuscolo (ignora le maiuscole e le minuscole)
                    String categoriaMinuscola = categoriaDesiderata.toLowerCase(Locale.getDefault());

                    // Esegui la query ignorando le maiuscole e le minuscole
                    prodottiRef
                            .orderBy("categoriaProdotto")
                            .startAt(categoriaMinuscola)
                            .endAt(categoriaMinuscola + "\uf8ff")
                            .get()
                            .addOnSuccessListener(queryDocumentSnapshots -> {
                                listaProdotti.clear();  // Pulisci la lista prima di aggiungere nuovi dati
                                // Handle dei risultati della query
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    // Ottieni i dati del documento
                                    Prodotto prodotto = documentSnapshot.toObject(Prodotto.class);
                                    listaProdotti.add(prodotto);
                                }
                                prodottoAdapter.notifyDataSetChanged();  // Aggiorna l'adapter con i nuovi dati
                            })
                            .addOnFailureListener(e -> {
                                // Gestione dell'errore
                                Log.e(TAG, "Errore durante l'esecuzione della query", e);
                            });
                }

            }
        });


        final Button buttonVisualizzaSpese = root.findViewById(R.id.buttonShop);

        buttonVisualizzaSpese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = NavHostFragment.findNavController(SpeseCategoriaFragment.this);
                navController.navigate(R.id.action_Shop);
            }
        });

        final Button buttonSpeseSettimanali = root.findViewById(R.id.buttonWeeklyQuery);

        buttonSpeseSettimanali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = NavHostFragment.findNavController(SpeseCategoriaFragment.this);
                navController.navigate(R.id.action_ShopSettimanali);
            }
        });

        final Button buttonSpeseMensili = root.findViewById(R.id.buttonMonthlyQuery);

        buttonSpeseMensili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = NavHostFragment.findNavController(SpeseCategoriaFragment.this);
                navController.navigate(R.id.action_ShopMensili);
            }
        });



        return root;
    }
    }

