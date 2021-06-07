package com.toni.preferenciascompartidas;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
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

        if(savedInstanceState != null){
            contador = savedInstanceState.getInt(CONTADOR_KEY);
            if(contador != 0){
                txtContador.setText(String.format("%s", contador));
            }
            colorSeleccionado = savedInstanceState.getInt(COLOR_KEY);
            txtContador.setBackgroundColor(colorSeleccionado);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cambiarFondo(View view){
        int color = ((ColorDrawable) view.getBackground()).getColor();

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
    }

    @Override
    protected void onSaveInstanceState( Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(CONTADOR_KEY, contador);
        savedInstanceState.putInt(COLOR_KEY, colorSeleccionado);
    }
}