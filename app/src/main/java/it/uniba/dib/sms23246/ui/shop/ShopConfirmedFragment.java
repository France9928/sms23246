package it.uniba.dib.sms23246.ui.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import it.uniba.dib.sms23246.R;
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
        // Ottieni il prodotto dal bundle

        Bundle bundle = getArguments();
        if (bundle != null) {
            //noinspection deprecation
            Prodotto prodotto = (Prodotto) bundle.getSerializable("prodotto");

            // Utilizzo i dati del prodotto per la visualizzazione
            TextView textViewProdotto = getView().findViewById(R.id.textViewProdotto);
            textViewProdotto.setText("Prodotto:" + prodotto.getNomeProdotto());

            TextView textViewCategoria = getView().findViewById(R.id.textViewCategoria);
            textViewCategoria.setText("Categoria: "+prodotto.getCategoriaProdotto());

            TextView textViewCosto = getView().findViewById(R.id.textViewCosto);
            textViewCosto.setText("Costo: "+(int) prodotto.getCosto());

            TextView textViewData = getView().findViewById(R.id.textViewData);
            textViewData.setText("Data: "+prodotto.getData());


        }

        return inflater.inflate(R.layout.fragment_shopconfirmed, container, false);
    }

    // Resto del codice del fragment...
    }

