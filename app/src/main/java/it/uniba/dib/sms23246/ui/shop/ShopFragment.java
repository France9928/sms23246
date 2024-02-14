package it.uniba.dib.sms23246.ui.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import it.uniba.dib.sms23246.R;
import it.uniba.dib.sms23246.databinding.FragmentShopBinding;

//guidovedisefunziona

public class ShopFragment extends Fragment {
    private FragmentShopBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ShopViewModel shopViewModel =
                new ViewModelProvider(this).get(ShopViewModel.class);

        binding = FragmentShopBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textViewWelcome;
        shopViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        final Button buttonAggiungiSpese = root.findViewById(R.id.buttonAddExpense);

        buttonAggiungiSpese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Azioni da eseguire quando il pulsante viene cliccato
                //Puoi implementare qui la logica desiderata
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
