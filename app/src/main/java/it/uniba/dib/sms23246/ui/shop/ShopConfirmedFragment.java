package it.uniba.dib.sms23246.ui.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import it.uniba.dib.sms23246.R;
import it.uniba.dib.sms23246.databinding.FragmentShopBinding;
import it.uniba.dib.sms23246.databinding.FragmentShopconfirmedBinding;

public class ShopConfirmedFragment extends Fragment {

    public FragmentShopconfirmedBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ShopConfirmedViewModel shopConfirmedViewModel =
                new ViewModelProvider(this).get(ShopConfirmedViewModel.class);

        // Utilizza il metodo inflate per ottenere un'istanza del binding
        binding = FragmentShopconfirmedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Ottieni il prodotto dal bundle
        final TextView textView = binding.textViewWelcome;
        shopConfirmedViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Bundle bundle = getArguments();
        if (bundle != null) {
            //noinspection deprecation
            Prodotto prodotto = (Prodotto) bundle.getSerializable("prodotto");
            if (root != null) {
                // Utilizzo i dati del prodotto per la visualizzazione
                TextView textViewProdotto = root.findViewById(R.id.textViewProdotto);
                textViewProdotto.setText("Prodotto:" + prodotto.getNomeProdotto());

                TextView textViewCategoria = root.findViewById(R.id.textViewCategoria);
                textViewCategoria.setText("Categoria: " + prodotto.getCategoriaProdotto());

                TextView textViewCosto = root.findViewById(R.id.textViewCosto);
                textViewCosto.setText("Costo: " + (int) prodotto.getCosto());

                TextView textViewData = getView().findViewById(R.id.textViewData);
                textViewData.setText("Data: " + prodotto.getData());
            }

        }

        return root;
    }

    // Resto del codice del fragment...
    }

