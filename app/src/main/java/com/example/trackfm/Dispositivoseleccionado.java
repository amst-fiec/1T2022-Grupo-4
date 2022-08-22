package com.example.trackfm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Dispositivoseleccionado extends AppCompatActivity {

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");



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

        if (pa.equals("Potencia Reflejada")){
            myRef.setValue("Hello, World!");
            valor.setText("30 W");
        }
        else if (pa.equals("Potencia")) {
            valor.setText("90 W");
        }
        else if (pa.equals("Corriente")) {
            valor.setText("90 A");
        }
        else if (pa.equals("Voltaje")) {
            valor.setText("90 V");
        }


    }
}