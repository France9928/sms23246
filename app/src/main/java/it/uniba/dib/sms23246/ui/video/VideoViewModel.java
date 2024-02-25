package it.uniba.dib.sms23246.ui.video;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class VideoViewModel extends ViewModel {

    private LiveData<List<PiuVideo>> videoLiveData;

    public VideoViewModel() {
        // Initialize the list of videos
        List<PiuVideo> video = new ArrayList<>();
        video.add(new PiuVideo("Policlinico di Bari", "https://www.youtube.com/watch?v=8LXikfC_xj4&t=2s"));
        video.add(new PiuVideo("Ospedale di Venere Bari", "https://www.youtube.com/watch?v=sjIvLsD-iy8"));
        video.add(new PiuVideo("Spiegazione malattie genetiche", "https://www.youtube.com/watch?v=ontb8B6hcUE"));
        video.add(new PiuVideo("Esercizi riabilitazione", "https://www.youtube.com/watch?v=_vSmVT0SBWw"));
        /*video.add(new PiuVideo("Ospedale di Venere Bari", "https://www.youtube.com/watch?v=sjIvLsD-iy8"));*/

        // Set the MutableLiveData value
        this.videoLiveData = new MutableLiveData<>(video);
    }

    public LiveData<List<PiuVideo>> getVideoLiveData() {

        return videoLiveData;
    }
}
