package it.uniba.dib.sms23246.ui.cassettaAttrezzi;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import it.uniba.dib.sms23246.R;

public class CassettaAttrezzi extends Fragment {

    private CassettaAttrezziViewModel mViewModel;

    public static CassettaAttrezzi newInstance() {
        return new CassettaAttrezzi();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cassetta_attrezzi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CassettaAttrezziViewModel.class);
        // TODO: Use the ViewModel
    }

}