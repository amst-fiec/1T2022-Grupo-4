package com.example.trackfm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Perfil extends AppCompatActivity {

    TextView txt_name, txt_email;
    ImageView imv_photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Bundle bundle = getIntent().getExtras();

        txt_name = findViewById(R.id.txt_nombre);
        txt_email = findViewById(R.id.txt_correo);
        imv_photo = findViewById(R.id.imv_foto);
        txt_name.setText(bundle.getString("username"));
        txt_email.setText(bundle.getString("useremail"));
        String photo = bundle.getString("userphoto");
        Picasso.with(getApplicationContext()).load(photo).into(imv_photo);

    }
}