package it.uniba.dib.sms23246.ui.gestioneUtenti;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

public class SharedViewModel extends AndroidViewModel {

    private final MutableLiveData<String> messaggio = new MutableLiveData<>();

    public SharedViewModel(@NonNull Application application) {
        super(application);
    }

    public void setMessaggio(String value) {
        messaggio.setValue(value);
    }

    public MutableLiveData<String> getMessaggio() {
        return messaggio;
    }
}