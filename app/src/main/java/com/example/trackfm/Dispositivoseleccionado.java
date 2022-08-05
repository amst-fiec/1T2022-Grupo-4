package com.example.trackfm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Dispositivoseleccionado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivoseleccionado);

        TextView dispositivoselec = (TextView) findViewById(R.id.dispositivo);
        TextView parametro = (TextView) findViewById(R.id.parametro);
        TextView valor = (TextView) findViewById(R.id.valor);
        Bundle bundle = getIntent().getExtras();
        String pa = bundle.getString("parametro");
        dispositivoselec.setText(bundle.getString("id"));
        parametro.setText(pa);

        if (pa.equals("Ancho de Banda")){
            valor.setText("30 dB");

        }else {
            valor.setText("90 Vatios");
        }


    }
}