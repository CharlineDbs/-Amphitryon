package com.example.amphitryon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.amphitryon.dto.Plat;
import com.example.amphitryon.dto.Plats;
import com.example.amphitryon.dto.ProposerPlat;
import com.example.amphitryon.dto.ProposerPlats;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ListePlatServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_plat_service);

        try {
            final JSONObject unService = new JSONObject(getIntent().getStringExtra("unService"));
            final TextView textViewService = findViewById(R.id.textService);
            String texteService = unService.getString("LIBELLESERVICE") + " le " + unService.getString("DATESERVICE");
            textViewService.setText(texteService);

            int idService = unService.getInt("IDSERVICE");
            String dateService = unService.getString("DATESERVICE");

            Log.d("TEST",idService + " " + dateService);


            listePlatsProposés(idService, dateService);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final Button buttonQuitterPlats = findViewById(R.id.btn_Quitter);
        buttonQuitterPlats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListePlatServiceActivity.this.finish();
            }
        });

        final Button buttonProposerPlats = findViewById(R.id.btn_Ajouter);
        buttonProposerPlats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final JSONObject unService;
                try {
                    unService = new JSONObject(getIntent().getStringExtra("unService"));
                    Intent intent = new Intent(ListePlatServiceActivity.this, AjouterPlatProposeActivity.class);
                    intent.putExtra("unService" , unService.toString());
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    protected void onResume() {
        super.onResume();
        try {

            final JSONObject unService = new JSONObject(getIntent().getStringExtra("unService"));
            final TextView textViewService = findViewById(R.id.textService);
            String texteService = unService.getString("LIBELLESERVICE") + " le " + unService.getString("DATESERVICE");
            textViewService.setText(texteService);

            int idService = unService.getInt("IDSERVICE");
            String dateService = unService.getString("DATESERVICE");

            Log.d("TEST",idService + " " + dateService);

            listePlatsProposés(idService, dateService);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }


    private void listePlatsProposés(int idService, String dateService) throws IOException {

        OkHttpClient client = new OkHttpClient();
        ArrayList arrayListPlatsProp = new ArrayList<String>();
        ProposerPlats lesPlatsProp = new ProposerPlats();

        RequestBody formBody = new FormBody.Builder()
                .add("IDSERVICE", String.valueOf(idService))
                .add("DATESERVICE", dateService)
                .build();


        Request request = new Request.Builder()
                .url("http://10.100.0.6/~cdubos2/Amphitryon/controleurs/proposerPlat/afficheProposerPlat.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();
                final JSONArray jsonArrayPlatsProposés ;
                try {
                    jsonArrayPlatsProposés = new JSONArray(responseStr);
                    for (int i = 0; i < jsonArrayPlatsProposés.length(); i++) {
                        JSONObject jsonPlatsProp = null;
                        jsonPlatsProp = jsonArrayPlatsProposés.getJSONObject(i);

                       ProposerPlat unPlatProp = new ProposerPlat(jsonPlatsProp.getInt("IDSERVICE"), jsonPlatsProp.getString("DATESERVICE"),
                               jsonPlatsProp.getInt("IDPLAT"), jsonPlatsProp.getInt("QUANTITEPROPOSEE"), jsonPlatsProp.getDouble("PRIXVENTE"),
                               jsonPlatsProp.getInt("QTEDISPO"), jsonPlatsProp.getInt("QTEVENDUE"));
                       lesPlatsProp.ajouterProposerPlats(unPlatProp);

                        arrayListPlatsProp.add(jsonPlatsProp.getInt("IDPLAT") + " - " + jsonPlatsProp.getString("NOMPLAT") +
                                "  Qte Dispo : " + jsonPlatsProp.getInt("QTEDISPO") + " Qte Vendu : " + jsonPlatsProp.getInt("QTEVENDUE"));
                    }

                    Log.d("TEST", String.valueOf(arrayListPlatsProp));

                    ListView listViewLesPlatsProposés = findViewById(R.id.listViewLesPlatsProposés);

                    ArrayAdapter<String> arrayAdapterPlats = new ArrayAdapter<String>(ListePlatServiceActivity.this, android.R.layout.simple_list_item_1, arrayListPlatsProp);
                    runOnUiThread(() -> {listViewLesPlatsProposés.setAdapter(arrayAdapterPlats);});

                    JSONArray finalJsonArrayPlats = jsonArrayPlatsProposés;
                    listViewLesPlatsProposés.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            try {
                                //JSONObject unPlat = jsonArrayPlatsProposés.getJSONObject(position);
                                JSONObject unPlatProp = finalJsonArrayPlats.getJSONObject(position);
                                final JSONObject unService = new JSONObject(getIntent().getStringExtra("unService"));
                                Intent intent = new Intent(ListePlatServiceActivity.this, ModifierProposePlatActivity.class);
                                //intent.putExtra("unPlat" , unPlat.toString());
                                intent.putExtra("proposerPlat", unPlatProp.toString());
                                intent.putExtra("unService" , unService.toString());
                                startActivity(intent);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            public void onFailure(Call call, IOException e) {
                Log.d("Test", "erreur!!! connexion impossible");
            }

        });




    }
}