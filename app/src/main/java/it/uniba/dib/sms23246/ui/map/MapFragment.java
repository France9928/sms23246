package it.uniba.dib.sms23246.ui.map;

// MapFragment.java
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.content.Intent;

import it.uniba.dib.sms23246.R;

public class MapFragment extends Fragment {

    private TextView textName1, textRating1, textName2, textRating2, textName3, textRating3, textName4, textRating4, textName5, textRating5 ;
    private Button button1, button2, button3, button4, button5;

    private MapViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(MapViewModel.class);

        // References to elements in the 1 card
        textName1 = view.findViewById(R.id.textName1);
        textRating1 = view.findViewById(R.id.textRating1);
        button1 = view.findViewById(R.id.button1);

        // References to elements in the 2 card
        textName2 = view.findViewById(R.id.textName2);
        textRating2 = view.findViewById(R.id.textRating2);
        button2 = view.findViewById(R.id.button2);

        // References to elements in the 3 card
        textName3 = view.findViewById(R.id.textName3);
        textRating3 = view.findViewById(R.id.textRating3);
        button3 = view.findViewById(R.id.button3);

        // References to elements in the 4 card
        textName4 = view.findViewById(R.id.textName4);
        textRating4 = view.findViewById(R.id.textRating4);
        button4 = view.findViewById(R.id.button4);

        // References to elements in the 5 card
        textName5 = view.findViewById(R.id.textName5);
        textRating5 = view.findViewById(R.id.textRating5);
        button5 = view.findViewById(R.id.button5);

        // Set up data for the cards
        setupCardData();

        return view;
    }

    private void setupCardData() {
        // Observe changes in the list of places in the ViewModel
        viewModel.getPlacesLiveData().observe(getViewLifecycleOwner(), places -> {
            if (places != null && places.size() >= 4) {
                // Card 1
                Place place1 = places.get(0);
                textName1.setText(place1.getName());
                textRating1.setText(place1.getRating());
                button1.setOnClickListener(v -> onButtonClick(place1.getLink()));

                // Card 2
                Place place2 = places.get(1);
                textName2.setText(place2.getName());
                textRating2.setText(place2.getRating());
                button2.setOnClickListener(v -> onButtonClick(place2.getLink()));

                // Card 3
                Place place3 = places.get(2);
                textName3.setText(place3.getName());
                textRating3.setText(place3.getRating());
                button3.setOnClickListener(v -> onButtonClick(place3.getLink()));

                // Card 4
                Place place4 = places.get(3);
                textName4.setText(place4.getName());
                textRating4.setText(place4.getRating());
                button4.setOnClickListener(v -> onButtonClick(place4.getLink()));

                // Card 5
                Place place5 = places.get(4);
                textName5.setText(place5.getName());
                textRating5.setText(place5.getRating());
                button5.setOnClickListener(v -> onButtonClick(place5.getLink()));
            }
        });
    }

    // Method to handle button click
    private void onButtonClick(String url) {
        // Actions to perform on button click
        openLink(url);
    }

    // Open the link using an Intent
    private void openLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        // Verifica se esiste un'app in grado di gestire l'intent

        startActivity(intent);
    }
}



