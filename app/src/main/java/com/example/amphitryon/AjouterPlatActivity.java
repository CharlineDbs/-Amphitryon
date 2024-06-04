package com.example.amphitryon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AjouterPlatActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String responseStr;
    OkHttpClient client = new OkHttpClient();
    EditText editTextNomPlat;
    EditText editTextDescriptif;
    EditText editTextCateg;
    Spinner spinCategorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_plat);
        try {

            afficherSpinner();

            final Button buttonAjouter = findViewById(R.id.btn_Ajouter);
            buttonAjouter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Test" , "Click ok");
                    try {
                        editTextNomPlat = findViewById(R.id.editNomPlat);
                        editTextDescriptif = findViewById(R.id.editDescriptif);
                        //editTextCateg = findViewById(R.id.editCateg);

                        AjouterPlat();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("Test" , e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        final Button buttonQuitterPlats = findViewById(R.id.btn_Quitter);
        buttonQuitterPlats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(AjouterPlatActivity.this, ChefCuisinierActivity.class);
                //startActivity(intent);
                AjouterPlatActivity.this.finish();
            }
        });

    }


    public void AjouterPlat() throws IOException {

        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = null;

        formBody = new FormBody.Builder()
                .add("NOMPLAT", editTextNomPlat.getText().toString())
                .add("DESCRIPTIF", editTextDescriptif.getText().toString())
                .add("IDCATEG", spinCategorie.getSelectedItem().toString().split("-")[0].replaceAll(" ", " "))
                .build();

        Request request = new Request.Builder()
                .url("http://10.100.0.6/~cdubos2/Amphitryon/controleurs/plat/ajoutePlat.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();
                Log.d("TestAjout","Reponse : "+responseStr);
                if (responseStr.compareTo("false") != 0) {
                    Log.d("Test", "ajout ok");
                } else {
                    Log.d("Test", "erreur!!! ajout impossible");
                }
            }

            public void onFailure(Call call, IOException e) {
                Log.d("Test", "erreur!!! connexion impossible");
            }

        });

    }

    public void afficherSpinner(){

        OkHttpClient client = new OkHttpClient();
        ArrayList spinCateg = new ArrayList<String>();

        Request request = new Request.Builder()

                .url("http://10.100.0.6/~cdubos2/Amphitryon/controleurs/categorie/listeCategorie.php")
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public  void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();
                Log.d("test", responseStr);
                JSONArray jsonArrayCateg = null;
                try {
                    jsonArrayCateg = new JSONArray(responseStr);
                    for (int i = 0; i < jsonArrayCateg.length(); i++) {

                        JSONObject jsonCateg = null;
                        jsonCateg = jsonArrayCateg.getJSONObject(i);
                        spinCateg.add(jsonCateg.getInt("IDCATEG") + " - " + jsonCateg.getString("LIBELLECATEG"));
                    }

                    spinCategorie = findViewById(R.id.SpinCateg);
                    ArrayAdapter<String> arrayAdapterCateg = new ArrayAdapter<String>(AjouterPlatActivity.this, android.R.layout.simple_spinner_item, spinCateg);


                    runOnUiThread(()->{
                        spinCategorie.setAdapter(arrayAdapterCateg);
                    });

                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            public void onFailure(Call call, IOException e)
            {
                Log.d("Test","erreur!!! connexion impossible");
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}