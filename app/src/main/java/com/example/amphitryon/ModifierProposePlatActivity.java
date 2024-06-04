package com.example.amphitryon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class ModifierProposePlatActivity extends AppCompatActivity {

    JSONObject proposerPlat;
    EditText editTextPrix;
    EditText editTextQteProp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_propose_plat);

        try {

            proposerPlat = new JSONObject(getIntent().getStringExtra("proposerPlat"));

            Log.d("TEST", String.valueOf(proposerPlat));

            editTextPrix = findViewById(R.id.editPrix);
            editTextQteProp = findViewById(R.id.editQteProp);

            editTextPrix.setText(String.valueOf(proposerPlat.getDouble("PRIXVENTE")));
            editTextQteProp.setText(String.valueOf(proposerPlat.getInt("QUANTITEPROPOSEE")));


            final TextView textViewPlat = findViewById(R.id.textPlat);
            String textePlat = proposerPlat.getString("NOMPLAT");
            textViewPlat.setText(textePlat);

            final JSONObject unService = new JSONObject(getIntent().getStringExtra("unService"));
            final TextView textViewService= findViewById(R.id.textService);
            String texteService = unService.getString("LIBELLESERVICE") + " le " + unService.getString("DATESERVICE");
            textViewService.setText(texteService);


            final Button buttonValiderModif = findViewById(R.id.btn_Modifier);
            buttonValiderModif.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Test" , "Click ok");
                    try {
                        modifierPlatPropose();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("Test" , e.getMessage());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            final Button buttonSupprimer = findViewById(R.id.btn_Supprimer);
            buttonSupprimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Test" , "Click ok");
                    try {
                        supprimerPlatPropose();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("Test" , e.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final Button buttonQuitterModif = findViewById(R.id.btn_Quitter);
        buttonQuitterModif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ModifierProposePlatActivity.this.finish();
            }
        });
    }

    public void modifierPlatPropose() throws IOException, JSONException {

        OkHttpClient client = new OkHttpClient();
                RequestBody formBody = null;

        formBody = new FormBody.Builder()
                .add("IDSERVICE", proposerPlat.getString("IDSERVICE"))
                .add("DATESERVICE", proposerPlat.getString("DATESERVICE"))
                .add("IDPLAT", proposerPlat.getString("IDPLAT"))
                .add("PRIXVENTE", editTextPrix.getText().toString())
                .add("QUANTITEPROPOSEE",editTextQteProp.getText().toString())
                .build();

        Request request = new Request.Builder()
                .url("http://10.100.0.6/~cdubos2/Amphitryon/controleurs/proposerPlat/modifieProposerPlat.php")
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

    }

    public void supprimerPlatPropose() throws IOException, JSONException {

        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = null;

        formBody = new FormBody.Builder()
                .add("IDSERVICE", proposerPlat.getString("IDSERVICE"))
                .add("DATESERVICE", proposerPlat.getString("DATESERVICE"))
                .add("IDPLAT", proposerPlat.getString("IDPLAT"))
                .build();

        Request request = new Request.Builder()
                .url("http://10.100.0.6/~cdubos2/Amphitryon/controleurs/proposerPlat/nePlusProposer.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();
                Log.d("TestSuppr","Reponse : "+responseStr);
                if (responseStr.compareTo("false") != 0) {
                    Log.d("Test", "suppr ok");
                    Intent intent = new Intent(ModifierProposePlatActivity.this, ListePlatServiceActivity.class);
                    startActivity(intent);
                } else {
                    Log.d("Test", "erreur!!! Suppression impossible");
                }
            }
            public void onFailure(Call call, IOException e) {
                Log.d("Test", "erreur!!! connexion impossible");
            }

        });

    }

}