package it.uniba.dib.sms23246.ui.sensori;

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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import it.uniba.dib.sms23246.R;

public class Sensori extends Fragment {

    private Button button1, button2, button3, button4, button5;
    private SensorManager sensorManager;
    private Sensor temperatureSensor;
    private TextView messageTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager != null) {
            temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            if (temperatureSensor == null) {
                // Il dispositivo non supporta il sensore di temperatura
                String messaggio = getResources().getString(R.string.no_sensore);
                showMessage(messaggio);
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
                                String sensore = getResources().getString(R.string.temperatura);
                                showMessage(sensore + temperatureInCelsius + "°C");
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

        button2 = rootView.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {

            String sensore = getResources().getString(R.string.sfigmomanometro);
            @Override

            public void onClick(View v) {
                showMessage(sensore + getRandomValue());
            }
        });

        button3 = rootView.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            String sensore = getResources().getString(R.string.emoglobinometro);
            @Override
            public void onClick(View v) {
                showMessage(sensore + getRandomValue());
            }
        });

        button4 = rootView.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            String sensore = getResources().getString(R.string.bilancia);
            @Override
            public void onClick(View v) {
                showMessage(sensore + getRandomValue());
            }
        });

        button5 = rootView.findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            String sensore = getResources().getString(R.string.glucometro);
            @Override
            public void onClick(View v) {
                showMessage(sensore + getRandomValue());
            }
        });

        messageTextView = rootView.findViewById(R.id.messageTextView);

        return rootView;
    }

    // Metodo di utilità per ottenere un valore random
    private float getRandomValue() {
        return (float) (Math.random() * 100); // Puoi regolare il range a seconda del valore desiderato
    }

    // Metodo di utilità per ottenere una temperatura random
    private float getRandomTemperature() {
        return (float) (Math.random() * 50); // Modifica il range a seconda delle tue esigenze
    }

    // Metodo di utilità per mostrare un messaggio nella TextView
    private void showMessage(String message) {
        if (messageTextView != null) {
            messageTextView.setText(message);
            messageTextView.setVisibility(View.VISIBLE);
        }
    }
}