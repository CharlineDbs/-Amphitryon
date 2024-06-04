package com.example.amphitryon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PlatActivity extends AppCompatActivity {

    JSONObject plat;
    String responseStr;
    OkHttpClient client = new OkHttpClient();
    EditText editTextNomPlat;
    EditText editTextDescriptif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plat);

        try {
            plat = new JSONObject(getIntent().getStringExtra("plat"));

            editTextNomPlat = editTextNomPlat = findViewById(R.id.editNomPlat);
            editTextDescriptif = editTextDescriptif = findViewById(R.id.editDescriptif);

            editTextNomPlat.setText(plat.getString("NOMPLAT"));
            editTextDescriptif.setText(plat.getString("DESCRIPTIF"));

            final Button buttonValiderModif = findViewById(R.id.btn_ValiderModif);
            buttonValiderModif.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Test" , "Click ok");
                    try {
                        modifierPlat();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("Test" , e.getMessage());
                    }
                }
            });

            final Button buttonSupprimer = findViewById(R.id.btn_Supprimer);
            buttonSupprimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Test" , "Click ok");
                    try {
                        supprimerPlat();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("Test" , e.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


        }catch (JSONException e) {
            e.printStackTrace();
        }


        final Button buttonQuitterPlats = findViewById(R.id.btn_Quitter);
        buttonQuitterPlats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(PlatActivity.this, AfficherPlatActivity.class);
                //startActivity(intent);
                PlatActivity.this.finish();
            }
        });


    }

    public void modifierPlat() throws IOException {

        OkHttpClient client = new OkHttpClient();
        Log.d("TestAvantModif",plat.toString());

        RequestBody formBody = null;

        try {
            formBody = new FormBody.Builder()
                    .add("IDPLAT", plat.getString("IDPLAT"))
                    .add("NOMPLAT", editTextNomPlat.getText().toString())
                    .add("DESCRIPTIF", editTextDescriptif.getText().toString())
                    .build();

            Request request = new Request.Builder()
                    .url("http://10.100.0.6/~cdubos2/Amphitryon/controleurs/plat/modifierPlat.php")
                    .post(formBody)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {

                public void onResponse(Call call, Response response) throws IOException {
                    String responseStr = response.body().string();
                    Log.d("TestModif","Reponse : "+responseStr);
                    if (responseStr.compareTo("false") != 0) {
                        Log.d("Test", "modif ok");
                    } else {
                        Log.d("Test", "erreur!!! modif impossible");
                    }
                }

                public void onFailure(Call call, IOException e) {
                    Log.d("Test", "erreur!!! connexion impossible");
                }

            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void supprimerPlat() throws IOException {

        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = null;

        try {
            formBody = new FormBody.Builder()
                    .add("IDPLAT", plat.getString("IDPLAT"))
                    .build();

            Request request = new Request.Builder()
                    .url("http://10.100.0.6/~cdubos2/Amphitryon/controleurs/plat/supprimePlat.php")
                    .post(formBody)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {

                public void onResponse(Call call, Response response) throws IOException {
                    String responseStr = response.body().string();
                    Log.d("TestSuppr","Reponse : "+responseStr);
                    if (responseStr.compareTo("false") != 0) {
                        Log.d("Test", "suppr ok");
                        Intent intent = new Intent(PlatActivity.this, AfficherPlatActivity.class);
                        startActivity(intent);
                    } else {
                        Log.d("Test", "erreur!!! Suppression impossible");
                    }
                }
                public void onFailure(Call call, IOException e) {
                    Log.d("Test", "erreur!!! connexion impossible");
                }

            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}