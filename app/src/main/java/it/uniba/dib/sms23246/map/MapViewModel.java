package it.uniba.dib.sms23246.map;

// MapViewModel.java
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MapViewModel extends ViewModel {
    private final MutableLiveData<List<Place>> placesLiveData;

    public MapViewModel() {
        placesLiveData = new MutableLiveData<>();
        // Initialize the list of places, you may load it from an external data source
        List<Place> places = new ArrayList<>();
        places.add(new Place("Santa Maria Spa", "3.9/5", "https://maps.app.goo.gl/WDHTJ3E5U5kigSx66"));
        places.add(new Place("Comunità Educativa Annibale M. di Francia", "4.1/5", "https://maps.app.goo.gl/Gph4RqFvyordUxeb8"));
        places.add(new Place("Caritas Diocesana Bari-Bitonto", "4.5/5", "https://maps.app.goo.gl/rw26LZWDVpVhynzbA"));
        places.add(new Place("Centro Salute Mentale Carrassi Bari", "4.4/5", "https://maps.app.goo.gl/y79HfeQSwnidGwWa9"));
        places.add(new Place("Centro Accoglienza Caritas Don Vito Diana", "4.8/5", "https://maps.app.goo.gl/r5Nindz4hm4ZuCNw7"));
        // Add more places if needed
        placesLiveData.setValue(places);
    }

    // Method to get LiveData of the list of places
    public LiveData<List<Place>> getPlacesLiveData() {
        return placesLiveData;
    }

    // Method to set a new list of places
    public void setPlaces(List<Place> places) {
        placesLiveData.setValue(places);
    }
}


