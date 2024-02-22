package it.uniba.dib.sms23246.ui.sensori;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import it.uniba.dib.sms23246.R;

public class Sensori extends Fragment {

    private Button button1, button2, button3, button4, button5;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Infla il layout del fragment
        return inflater.inflate(R.layout.fragment_sensori, container, false);

    }
}
