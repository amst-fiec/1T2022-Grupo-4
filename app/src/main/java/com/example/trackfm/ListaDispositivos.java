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

import java.util.ArrayList;

public class ListaDispositivos extends AppCompatActivity {

    private ArrayList disps;
    private ArrayAdapter adaptador1;
    private ListView lis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dispositivos);
        disps=new ArrayList();
        disps.add("NAUTEL GV5 - 5KW");
        disps.add("DB Mozart NEXT7000 - 7KW");
        disps.add("DB Mozart NEXT1000 - 1KW");
        adaptador1 = new ArrayAdapter(this,android.R.layout.simple_list_item_1,disps);
        lis= (ListView) findViewById(R.id.ListV);
        lis.setAdapter(adaptador1);


        lis.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long i){
                mostrarSelecPara(position);

            }
        });

        //registerForContextMenu(lis);

    }

    private void mostrarSelecPara(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(ListaDispositivos.this);
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.options, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.show();

        //Button btnpotreflejada = view.findViewById(R.id.potr);
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

        //Button btnpotencia = view.findViewById(R.id.pot);
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

        //Button btncorriente = view.findViewById(R.id.corri);
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

        //Button btnvoltaje = view.findViewById(R.id.volt);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Seleccione parámetro a analizar");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.Ancho_de_Banda:
                Intent i = new Intent(ListaDispositivos.this, Dispositivoseleccionado.class);
                String dispo = (String) disps.get(info.position);
                String param1 = "Ancho de Banda";
                i.putExtra("id", dispo);
                i.putExtra("parametro",param1);
                startActivity(i);

                return true;
            case R.id.Potencia:
                Intent i2 = new Intent(ListaDispositivos.this, Dispositivoseleccionado.class);
                String disposi = (String) disps.get(info.position);
                String param2 = "Potencia";
                i2.putExtra("id", disposi);
                i2.putExtra("parametro",param2);
                startActivity(i2);

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


}