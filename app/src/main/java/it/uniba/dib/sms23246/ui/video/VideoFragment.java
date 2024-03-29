package it.uniba.dib.sms23246.ui.video;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
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


public class VideoFragment extends Fragment {


    private VideoViewModel viewModel;

    private TextView textVideo1, textVideo2, textMalattie, textEsercizi;

    private Button pulsanteVideo1, pulsanteVideo2, pulsanteMalattie, pulsanteEsercizi;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        VideoViewModel videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        // Inizializza il viewModel
        viewModel = new ViewModelProvider(this).get(VideoViewModel.class);

        //dichiarazione delle view

        textVideo1 = view.findViewById(R.id.textVideo1);
        pulsanteVideo1 = view.findViewById(R.id.pulsanteVideo1);

        // References to elements in the 2 card
        textVideo2 = view.findViewById(R.id.textVideo2);
        pulsanteVideo2 = view.findViewById(R.id.pulsanteVideo2);

        // References to elements in the 3 card
        textMalattie = view.findViewById(R.id.textMalattie);
        pulsanteMalattie = view.findViewById(R.id.pulsanteMalattie);

        // References to elements in the 4 card
        textEsercizi = view.findViewById(R.id.textEsercizi);
        pulsanteEsercizi = view.findViewById(R.id.pulsanteEsercizi);

        metodoFaiVedereCose();

        return view;
    }



    private void metodoFaiVedereCose(){

        viewModel.getVideoLiveData().observe(getViewLifecycleOwner(), piuVideo -> {
            if ((piuVideo != null) && (piuVideo.size() >= 4)) {
                // Card 1
                PiuVideo video1 = piuVideo.get(0);
                textVideo1.setText(getString(R.string.titolo_video));
                pulsanteVideo1.setText(getString(R.string.guarda_video));
                pulsanteVideo1.setOnClickListener(v -> onButtonClick(video1.getLink()));

                PiuVideo video2 = piuVideo.get(1);
                textVideo2.setText(getString(R.string.titolo_video));
                pulsanteVideo2.setText(getString(R.string.guarda_video));
                pulsanteVideo2.setOnClickListener(v -> onButtonClick(video2.getLink()));

                PiuVideo malattie = piuVideo.get(2);
                textMalattie.setText(getString(R.string.malattie));
                pulsanteMalattie.setText(getString(R.string.guarda_video));
                pulsanteMalattie.setOnClickListener(v -> onButtonClick(malattie.getLink()));

                PiuVideo esercizi = piuVideo.get(3);
                textEsercizi.setText(getString(R.string.esercizi));
                pulsanteEsercizi.setText(getString(R.string.guarda_video));
                pulsanteEsercizi.setOnClickListener(v -> onButtonClick(esercizi.getLink()));


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