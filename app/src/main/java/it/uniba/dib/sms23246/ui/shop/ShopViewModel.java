package it.uniba.dib.sms23246.ui.shop;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
//gggggggggg

public class ShopViewModel extends ViewModel{
    private final MutableLiveData<String> sText;
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    public ShopViewModel() {

        sText = new MutableLiveData<>();
        sText.setValue("Ciao, " + user.getDisplayName());

    }

    public LiveData<String> getText() {
        return sText;
    }

}
