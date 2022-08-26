package com.example.trackfm;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import android.graphics.Color;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


public class Dispositivoseleccionado extends AppCompatActivity {

    DatabaseReference db_reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivoseleccionado);

        db_reference = FirebaseDatabase.getInstance().getReference().child("datos");

        TextView dispositivoselec = (TextView) findViewById(R.id.dispositivo);
        TextView parametro = (TextView) findViewById(R.id.parametro);
        TextView valor = (TextView) findViewById(R.id.valor);
        String id1 = "potencia reflejada";
        String id2 = "potencia";
        String id3 = "corriente";
        String id4 = "voltaje";
        String u1u2 = " W";
        String u3 = " A";
        String u4 = " V";
        Bundle bundle = getIntent().getExtras();
        String pa = bundle.getString("parametro");
        dispositivoselec.setText(bundle.getString("id"));
        parametro.setText(pa);

        if (pa.equals("Potencia Reflejada")){
            valor.setText("30 W");
            //leerparamatros(valor,id1,u1u2);
            //grafico(id1);

        }
        else if (pa.equals("Potencia")) {

            leerparamatros(valor,id2,u1u2);
            grafico(id2);

        }
        else if (pa.equals("Corriente")) {
            valor.setText("90 A");
            //leerparamatros(valor,id3,u3);
            //grafico(id3);
        }
        else if (pa.equals("Voltaje")) {
            valor.setText("90 V");
            //leerparamatros(valor,id4,u4);
            //grafico(id4);
        }


    }

    private void leerparamatros(TextView valor, String id, String unidad){
        db_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String val = String.valueOf(snapshot.child(id).getValue());
                    valor.setText(val+unidad);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println(error.toException());
            }
        });
    }

    private void grafico(String id){

        db_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<Entry> linelist = new ArrayList();
                int cont = 1;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String val = String.valueOf(snapshot.child(id).getValue());
                    float ejex = (float) cont;
                    float ejey = (float) Integer.parseInt(val) ;
                    linelist.add(new Entry(ejex*1,ejey*1));
                    cont++;
                }

                LineDataSet lineDataSet = new LineDataSet(linelist, id);
                LineData lineData = new LineData(lineDataSet);
                LineChart lchart = findViewById(R.id.line_chart);
                lchart.setData(lineData);

            }
            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println(error.toException());
            }
        });
    }
}