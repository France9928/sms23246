package it.uniba.dib.sms23246.ui.user;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    private static MutableLiveData<UserData> userData = new MutableLiveData<>();
    private static MutableLiveData<Patologia> patologia = new MutableLiveData<>();

    // Metodo per caricare i dati dell'utente dal database
    public static void loadUserDataFromDatabase(Context context, int userId) {
       // DBHelper dbHelper = new DBHelper(context);
        //UserData user = dbHelper.getUserData(userId);

        // Aggiorna il MutableLiveData con i dati dell'utente
       // userData.postValue(user);
    }

    public static LiveData<UserData> getUserData() {
        return userData;
    }

    public static LiveData<Patologia> getPatologia() {
        return patologia;
    }

}