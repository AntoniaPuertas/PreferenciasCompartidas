package com.toni.preferenciascompartidas;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.toni.preferenciascompartidas";

    private TextView txtContador;

    private int contador = 0;
    private int colorSeleccionado;

    private final String CONTADOR_KEY = "contador";
    private final String COLOR_KEY = "color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtContador = findViewById(R.id.txtContador);

        colorSeleccionado = ContextCompat.getColor(this, R.color.design_default_color_background);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        contador = mPreferences.getInt(CONTADOR_KEY, 0);
        txtContador.setText(String.format("%s", contador));

        colorSeleccionado = mPreferences.getInt(COLOR_KEY, colorSeleccionado);
        txtContador.setBackgroundColor(colorSeleccionado);

    }


    public void cambiarFondo(View view){
        int color = ((ColorDrawable) view.getBackground()).getColor();
        txtContador.setBackgroundColor(color);
        colorSeleccionado = color;
    }

    public void contar(View view){
        contador++;
        txtContador.setText(String.format("%s", contador));
    }

    public void reset(View view){
        contador = 0;
        txtContador.setText(String.format("%s", contador));

        colorSeleccionado = ContextCompat.getColor(this, R.color.design_default_color_background);
        txtContador.setBackgroundColor(colorSeleccionado);

        // Clear preferences
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
    }


    @Override
    protected void onPause(){
        super.onPause();

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(CONTADOR_KEY, contador);
        preferencesEditor.putInt(COLOR_KEY, colorSeleccionado);
        preferencesEditor.apply();
    }
}