package it.uniba.dib.sms23246.ui.video;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import it.uniba.dib.sms23246.ui.video.PiuVideo;

public class VideoViewModel extends ViewModel {

    public VideoViewModel() {
        MutableLiveData<List<PiuVideo>> placesLiveData = new MutableLiveData<>();
        // Initialize the list of places, you may load it from an external data source
        List<PiuVideo> video = new ArrayList<>();
        video.add(new PiuVideo("Policlinico di Bari", "https://www.youtube.com/watch?v=8LXikfC_xj4&t=2s"));
        video.add(new PiuVideo("Ospedale di Venere Bari", "https://www.youtube.com/watch?v=sjIvLsD-iy8"));

        // Add more places if needed
        placesLiveData.setValue(video);
    }

    public LiveData<List<PiuVideo>> getPlacesLiveData() {
        LiveData<List<PiuVideo>> placesLiveData = null;
        return placesLiveData;
    }
}
