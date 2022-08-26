package com.example.trackfm;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    GoogleSignInClient mGoogleSignInClient;
    DatabaseReference db_reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.Theme_TrackFM);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtencion de informacion de la cuenta de Google de usuario
        db_reference = FirebaseDatabase.getInstance().getReference().child("Usuarios");

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Intent intent = getIntent();
        String msg = intent.getStringExtra("msg");
        if(msg != null){
            if(msg.equals("cerrarSesion")){
                cerrarSesion();
            }


        }
    }

    //Metodo de inicio de sesion y verificacion de usuarios ingresados
    public void iniciarSesion(View view) {
        EditText us = findViewById(R.id.usuario);
        String usuario = us.getText().toString();



        db_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<String> usuariosdis= new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String usua = String.valueOf(snapshot.child("usuario").getValue());
                    usuariosdis.add(usua);
                }

                if (usuariosdis.contains(usuario)){
                    resultLauncher.launch(new Intent(mGoogleSignInClient.getSignInIntent()));
                }else{
                    if (usuario.isEmpty()){
                        Toast toast = Toast.makeText(getApplicationContext(),"Ingrese usuario",Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(),"Usuario no permitido",Toast.LENGTH_LONG);
                        toast.show();
                    }

                }

            }
            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println(error.toException());
            }
        });



    }
    private void cerrarSesion() {
        mGoogleSignInClient.signOut().addOnCompleteListener(this, task -> updateUI(null));
    }

    //Metodo para llenar un formulario y pedir usuario de acceso a la app
    public void solicitarusuario(View view) {
        Intent i = new Intent(MainActivity.this, Solitusuario.class);
        startActivity(i);
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new
            ActivityResultContracts.StartActivityForResult(), new
            ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
                        try {
                            GoogleSignInAccount account = task.getResult(ApiException.class);
                            if (account != null) firebaseAuthWithGoogle(account);
                        } catch (ApiException e) {
                            Log.w("TAG", "Fallo el inicio de sesión con google.", e);
                        }
                    }
                }
            });
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d("TAG", "firebaseAuthWithGoogle:" + acct.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        System.out.println("error");
                        updateUI(null);
                    }
                });
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            String photo = String.valueOf(user.getPhotoUrl());
            HashMap<String, String> info_user = new HashMap<String, String>();
            info_user.put("user_name", name);
            info_user.put("user_email", email);
            info_user.put("user_photo", photo);
            info_user.put("user_id", user.getUid());

            finish();
            Intent intent = new Intent(this, Menu.class);
            intent.putExtra("info_user", info_user);
            startActivity(intent);
        } else {
            System.out.println("sin registrarse");
        }

    }
}