package it.uniba.dib.sms23246.ui.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;
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
    private SearchView searchView;
    private RatingBar ratingBar;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        VideoViewModel videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);

        View view = inflater.inflate(R.layout.fragment_video, container, false);

        primoT = view.findViewById(R.id.primoT);
        secondoT = view.findViewById(R.id.secondoT);
        terzoT = view.findViewById(R.id.terzoT);

        videoView = view.findViewById(R.id.videoView);
        searchView = view.findViewById(R.id.searchView);
        ratingBar = view.findViewById(R.id.ratingBar);

        float ratingValue = 5f; // impostare il valore di valutazione desiderato
        ratingBar.setRating(ratingValue);



        metodo();

        return view;
    }
    private void metodo(){



    }
}