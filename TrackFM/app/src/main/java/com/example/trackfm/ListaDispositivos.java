package com.example.trackfm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListaDispositivos extends AppCompatActivity {

    //private ArrayList disps;
    private ArrayAdapter adaptador1;
    private ListView lis;
    DatabaseReference db_reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dispositivos);


        //Lectura de Base de Datos para mostrar los dispositivos conectados
        db_reference = FirebaseDatabase.getInstance().getReference().child("Dispositivos");

        db_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<String> disps= new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String nd = String.valueOf(snapshot.child("nomdisp").getValue());
                    disps.add(nd);
                }

                adaptador1 = new ArrayAdapter(ListaDispositivos.this,android.R.layout.simple_list_item_1,disps);
                lis= (ListView) findViewById(R.id.ListV);
                lis.setAdapter(adaptador1);


                lis.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long i){
                        mostrarSelecPara(position, disps);

                    }
                });


            }
            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println(error.toException());
            }
        });


/*
adaptador1 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,disps);
        lis= (ListView) findViewById(R.id.ListV);
        lis.setAdapter(adaptador1);


        lis.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long i){
                mostrarSelecPara(position);

            }
        });
 */



    }

    //Metodo que revisa parametro selecionado y los envia a Dispositivosseleccionado.class
    private void mostrarSelecPara(int position, List<String> disps){
        AlertDialog.Builder builder = new AlertDialog.Builder(ListaDispositivos.this);
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.options, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.show();

        ImageView bpotre = view.findViewById(R.id.imgpf);
        bpotre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaDispositivos.this, Dispositivoseleccionado.class);
                String dispo1 = (String) disps.get(position);
                String param1 = "Potencia Reflejada";
                i.putExtra("id", dispo1);
                i.putExtra("parametro",param1);
                startActivity(i);
            }
        });

        ImageView bpot = view.findViewById(R.id.imgp);
        bpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(ListaDispositivos.this, Dispositivoseleccionado.class);
                String dispo2 = (String) disps.get(position);
                String param2 = "Potencia";
                i2.putExtra("id", dispo2);
                i2.putExtra("parametro",param2);
                startActivity(i2);
            }
        });

        ImageView bcor = view.findViewById(R.id.imgvte);
        bcor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(ListaDispositivos.this, Dispositivoseleccionado.class);
                String dispo3 = (String) disps.get(position);
                String param3 = "Corriente";
                i3.putExtra("id", dispo3);
                i3.putExtra("parametro",param3);
                startActivity(i3);
            }
        });


        ImageView bvol = view.findViewById(R.id.imgv);
        bvol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4 = new Intent(ListaDispositivos.this, Dispositivoseleccionado.class);
                String dispo4 = (String) disps.get(position);
                String param4 = "Voltaje";
                i4.putExtra("id", dispo4);
                i4.putExtra("parametro",param4);
                startActivity(i4);
            }
        });

    }






}