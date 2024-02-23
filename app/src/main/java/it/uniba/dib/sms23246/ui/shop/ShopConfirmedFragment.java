package it.uniba.dib.sms23246.ui.shop;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import it.uniba.dib.sms23246.R;
import it.uniba.dib.sms23246.databinding.FragmentShopBinding;
import it.uniba.dib.sms23246.databinding.FragmentShopconfirmedBinding;
import it.uniba.dib.sms23246.ui.shop.ShopFragment;

public class ShopConfirmedFragment extends Fragment {

    public FragmentShopconfirmedBinding binding;
    public ShopFragment shop= new ShopFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ShopConfirmedViewModel shopConfirmedViewModel =
                new ViewModelProvider(this).get(ShopConfirmedViewModel.class);

        // Utilizza il metodo inflate per ottenere un'istanza del binding
        binding = FragmentShopconfirmedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Bundle bundle = getArguments();
        Log.d("Contenuto del Bundle", bundle.toString());

        if (bundle != null) {
            //noinspection deprecation
            Prodotto prodotto = (Prodotto) bundle.getSerializable("prodotto");
            if (root != null) {
                // Utilizzo i dati del prodotto per la visualizzazione
                TextView textViewProdotto = root.findViewById(R.id.textViewProdotto);
                if(textViewProdotto != null){
                    String nomeProdotto = "Prodotto:" + prodotto.getNomeProdotto();
                    textViewProdotto.setText(nomeProdotto);
                }

                TextView textViewCategoria = root.findViewById(R.id.textViewCategoria);
                if(textViewCategoria != null){
                    String categoriaProdotto = "Categoria: " + prodotto.getCategoriaProdotto();
                    textViewCategoria.setText(categoriaProdotto);
                }

                TextView textViewCosto = root.findViewById(R.id.textViewCosto);
                if(textViewCosto != null) {
                    String costoProdotto = "Costo: " + (int) prodotto.getCosto();
                    textViewCosto.setText(costoProdotto);
                }

                TextView textViewData = root.findViewById(R.id.textViewData);
               if(textViewData != null) {
                   String dataProdotto = "Data: " + prodotto.getData();
                   textViewData.setText(dataProdotto);
               }
            }

        }

        return root;
    }

    // Resto del codice del fragment...
    }

