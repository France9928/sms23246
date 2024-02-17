package it.uniba.dib.sms23246.ui.video;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.RatingBar.OnRatingBarChangeListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import it.uniba.dib.sms23246.R;

public class VideoFragment extends Fragment {


    private VideoViewModel viewModel;

    private TextView primoT, secondoT, terzoT;
    private VideoView videoView;
    private RatingBar ratingBar;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        VideoViewModel videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);

        View view = inflater.inflate(R.layout.fragment_video, container, false);

        primoT = view.findViewById(R.id.primoT);
        secondoT = view.findViewById(R.id.secondoT);
        terzoT = view.findViewById(R.id.terzoT);
        videoView = view.findViewById(R.id.videoView);
        ratingBar = view.findViewById(R.id.ratingBar);

        //mettere valutazione ma controllare se prima l'utente è autenticato
        /*float ratingValue = 5f;//valutazione impostata a 5
        // impostare il valore di valutazione desiderato
        ratingBar.setRating(ratingValue);*/

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                boolean userIsAuthenticated= false;
                if (userIsAuthenticated) {
                    // Invia la valutazione
                    Toast.makeText(getApplicationContext(), "Valutazione inviata!", Toast.LENGTH_SHORT).show();
                } else {
                    // Mostra un messaggio di errore all'utente
                    Toast.makeText(getApplicationContext(), "Devi essere autenticato per inviare una valutazione", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //far vedere i due video di presentazione
        String videoUrl1 = "URL_DEL_PRIMO_VIDEO";
        String videoUrl2 = "URL_DEL_SECONDO_VIDEO";
        videoView.setVideoPath(videoUrl1);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                              @Override
                                              public void onCompletion(MediaPlayer mp) {
                                                  videoView.setVideoPath(videoUrl2);
                                                  videoView.start();
                                              }
                                          });

        metodo();

        return view;
    }

    private Context getApplicationContext() {
        return null;
    }

    private void metodo(){



    }
}