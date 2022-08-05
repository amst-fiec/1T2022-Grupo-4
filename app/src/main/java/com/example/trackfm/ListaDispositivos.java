package com.example.trackfm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        registerForContextMenu(lis);

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