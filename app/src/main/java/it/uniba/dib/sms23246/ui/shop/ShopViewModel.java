package it.uniba.dib.sms23246.ui.shop;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class ShopViewModel extends ViewModel{
    private final MutableLiveData<String> sText;

    private String nomeUt;

//    private UserData user = new UserData();

    public ShopViewModel() {

  //      nomeUt = user.getNome();

        sText = new MutableLiveData<>();
        sText.setValue("Ciao, " + nomeUt);

    }

    public LiveData<String> getText() {
        return sText;
    }

}
