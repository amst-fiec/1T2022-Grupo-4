package com.example.trackfm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class Solitusuario extends AppCompatActivity {

    Intent emailIntent = new Intent(Intent.ACTION_SEND);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solitusuario);
    }

    public void solicitar(View view) {
        EditText nom = findViewById(R.id.nombre);
        String nombre = nom.getText().toString();
        EditText ced = findViewById(R.id.cedularuc);
        String cedula = ced.getText().toString();
        EditText tel = findViewById(R.id.celular);
        String telefono = tel.getText().toString();
        EditText cor = findViewById(R.id.correo);
        String correo = cor.getText().toString();

        //Verificacion que los campos del formulario estén completos para enviar la solicitud.
        if(nombre.isEmpty()||cedula.isEmpty()||telefono.isEmpty()||correo.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),"Datos Incompletos",Toast.LENGTH_LONG);
            toast.show();
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),"Solicitud enviada" +
                    "\nTrackFM se comunicará con usted",Toast.LENGTH_LONG);
            toast.show();

            //Envío de solicitud de usuario

            String[] to = {"ifermieles@gmail.com"};
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Solicitud de usuario");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Datos de la solicitud\n" +
                    "Nombres Completos:" + nombre+ "\n" +
                    "Cedula/RUC:" + cedula +"\n" +
                    "Teléfono:" + telefono + "\n" +
                    "Correo:" + correo+ "\n");


        }



    }

}