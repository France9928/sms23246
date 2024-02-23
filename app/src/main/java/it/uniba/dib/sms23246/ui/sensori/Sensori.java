package it.uniba.dib.sms23246.ui.sensori;

import static android.content.Context.SENSOR_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import it.uniba.dib.sms23246.R;

public class Sensori extends Fragment {

    private Button button1, button2, button3, button4, button5;
    private SensorManager sensorManager;
    private Sensor temperatureSensor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager != null) {
            temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            if (temperatureSensor == null) {
                // Il dispositivo non supporta il sensore di temperatura
                showToast("Sensore di temperatura non disponibile");
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Infla il layout del fragment
        View rootView = inflater.inflate(R.layout.fragment_sensori, container, false);

        button1 = rootView.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temperatureSensor != null) {
                    // Registra il listener del sensore
                    sensorManager.registerListener(new SensorEventListener() {
                        @Override
                        public void onSensorChanged(SensorEvent event) {
                            if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
                                float temperatureInCelsius = event.values[0];
                                showToast("Temperatura: " + temperatureInCelsius + "°C");
                            }
                        }

                        @Override
                        public void onAccuracyChanged(Sensor sensor, int accuracy) {
                            // Non necessario per il sensore di temperatura
                        }
                    }, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
                }
            }
        });

        return rootView;
    }

    // Metodo di utilità per mostrare un Toast
    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}

