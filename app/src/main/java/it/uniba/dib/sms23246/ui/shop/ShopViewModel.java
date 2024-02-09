package it.uniba.dib.sms23246.ui.shop;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import it.uniba.dib.sms23246.user.UserData;

public class ShopViewModel extends ViewModel{
    private final MutableLiveData<String> sText;
    private final MutableLiveData<String> sText1;

    private String nomeUt;

    private UserData user = new UserData();

    public ShopViewModel() {

        nomeUt = user.getNome();

        sText = new MutableLiveData<>();
        sText.setValue("Ciao, " + nomeUt);

        sText1 = new MutableLiveData<>();
        sText1.setValue("Qui potrai gestire le tue spese");



    }

    public LiveData<String> getText() {
        return sText;
    }
    public LiveData<String> getText1() {
        return sText1;
    }

}
