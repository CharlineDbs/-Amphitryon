package com.example.amphitryon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

public class AjouterPlatProposeActivity extends AppCompatActivity {

    String responseStr;
    OkHttpClient client = new OkHttpClient();
    EditText editPrix;
    EditText editQuantité;
    Spinner spinPlats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_plat_propose);

        try {
            final JSONObject unService = new JSONObject(getIntent().getStringExtra("unService"));
            final TextView textViewService = findViewById(R.id.textService);
            String texteService = unService.getString("LIBELLESERVICE") + " le " + unService.getString("DATESERVICE");
            textViewService.setText(texteService);

            int idService = unService.getInt("IDSERVICE");
            String dateService = unService.getString("DATESERVICE");
            afficherSpinner(idService, dateService);


            final Button buttonAjouter = findViewById(R.id.btn_Ajouter);
            buttonAjouter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Test" , "Click ok");
                    editQuantité = findViewById(R.id.editQuantité);
                    editPrix = findViewById(R.id.editPrix);
                    try {

                        AjouterProposerPlat(idService, dateService);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final Button buttonQuitterProposer = findViewById(R.id.btn_Quitter);
        buttonQuitterProposer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final JSONObject unService;
                try {
                    unService = new JSONObject(getIntent().getStringExtra("unService"));
                    Intent intent = new Intent(AjouterPlatProposeActivity.this, ListePlatServiceActivity.class);
                    intent.putExtra("unService" , unService.toString());
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void AjouterProposerPlat(int idService, String dateService) throws IOException {

        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = null;

        formBody = new FormBody.Builder()
                .add("IDSERVICE", String.valueOf(idService))
                .add("DATESERVICE", dateService)
                .add("IDPLAT", spinPlats.getSelectedItem().toString().split("-")[0].replaceAll(" ", " "))
                .add("QUANTITEPROPOSEE", editQuantité.getText().toString())
                .add("PRIXVENTE", editPrix.getText().toString())
                .add("QTEDISPO", editQuantité.getText().toString())
                .build();

        Request request = new Request.Builder()
                .url("http://10.100.0.6/~cdubos2/Amphitryon/controleurs/proposerPlat/proposerPlat.php")
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

    public void afficherSpinner(int idService, String dateService){

        OkHttpClient client = new OkHttpClient();
        ArrayList spinPlat = new ArrayList<String>();
        RequestBody formBody = null;

        formBody = new FormBody.Builder()
                .add("IDSERVICE", String.valueOf(idService))
                .add("DATESERVICE", dateService)
                .build();


        Request request = new Request.Builder()
                .url("http://10.100.0.6/~cdubos2/Amphitryon/controleurs/proposerPlat/affichePlatAProposer.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public  void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();
                Log.d("test", responseStr);
                JSONArray jsonArrayPlat = null;
                try {
                    jsonArrayPlat = new JSONArray(responseStr);
                    for (int i = 0; i < jsonArrayPlat.length(); i++) {

                        JSONObject jsonPlat = null;
                        jsonPlat = jsonArrayPlat.getJSONObject(i);
                        spinPlat.add(jsonPlat.getInt("IDPLAT") + " - " + jsonPlat.getString("NOMPLAT"));
                    }

                    spinPlats = findViewById(R.id.SpinPlat);
                    ArrayAdapter<String> arrayAdapterPlat = new ArrayAdapter<String>(AjouterPlatProposeActivity.this, android.R.layout.simple_spinner_item, spinPlat);

                    runOnUiThread(()->{
                        spinPlats.setAdapter(arrayAdapterPlat);
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




}