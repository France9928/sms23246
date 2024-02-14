package it.uniba.dib.sms23246.ui.video;

import static android.os.Build.VERSION_CODES.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.w3c.dom.Text;

import it.uniba.dib.sms23246.R;
import it.uniba.dib.sms23246.databinding.FragmentVideoBinding;
import it.uniba.dib.sms23246.ui.shop.ShopViewModel;

public class VideoFragment extends Fragment {


    private FragmentVideoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        VideoViewModel videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);

        binding = FragmentVideoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView primoT = root.findViewById(it.uniba.dib.sms23246.R.id.primoT);
        final TextView secondoT = root.findViewById(it.uniba.dib.sms23246.R.id.secondoT);
        final TextView terzoT = root.findViewById(it.uniba.dib.sms23246.R.id.terzoT);
        final VideoView videoView = root.findViewById(it.uniba.dib.sms23246.R.id.videoView);

        final SearchView searchView = root.findViewById(it.uniba.dib.sms23246.R.id.searchView);
        final RatingBar ratingBar = root.findViewById(it.uniba.dib.sms23246.R.id.ratingBar);


        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoLink = videoViewModel.getVideoLink().getValue();
                if (videoLink != null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoLink));
                    startActivity(intent);
                }
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