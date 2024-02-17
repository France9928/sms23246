package it.uniba.dib.sms23246.ui.video;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VideoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private static final MutableLiveData<String> videoLink = new MutableLiveData<>();

    public static void setVideoLink(String link) {
        videoLink.setValue(link);
    }

    public LiveData<String> getVideoLink() {
        return videoLink;
    }

    public VideoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");




    }

    public LiveData<String> getText() {
        return mText;
    }


}
