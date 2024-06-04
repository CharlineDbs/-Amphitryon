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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ServiceProposerPlatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_proposerplat);

        try {
            listeServices();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Button buttonQuitterPlats = findViewById(R.id.btn_Quitter);
        buttonQuitterPlats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceProposerPlatActivity.this.finish();
            }
        });

    }

    public void listeServices() throws IOException {

        OkHttpClient client = new OkHttpClient();
        ArrayList arrayListServices = new ArrayList<String>();

        Request request = new Request.Builder()
                .url("http://10.100.0.6/~cdubos2/Amphitryon/controleurs/proposerPlat/afficheServiceProposerPlat.php")
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();
                final JSONArray jsonArrayService ;
                try {
                    jsonArrayService = new JSONArray(responseStr);

                    for (int i = 0; i < jsonArrayService.length(); i++) {
                        JSONObject jsonService = null;
                        jsonService = jsonArrayService.getJSONObject(i);
                        arrayListServices.add(jsonService.getInt("IDSERVICE") + " - " +
                                jsonService.getString("LIBELLESERVICE") + " : " + jsonService.getString("DATESERVICE"));
                    }


                    ListView listViewLesServices = findViewById(R.id.listViewLesServices);
                    ArrayAdapter<String> arrayAdapterCateg = new ArrayAdapter<String>(ServiceProposerPlatActivity.this, android.R.layout.simple_list_item_1, arrayListServices);
                    runOnUiThread(() -> {listViewLesServices.setAdapter(arrayAdapterCateg);});
                    listViewLesServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            try {
                                JSONObject unService = jsonArrayService.getJSONObject(position);
                                Intent intent = new Intent(ServiceProposerPlatActivity.this, ListePlatServiceActivity.class);
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