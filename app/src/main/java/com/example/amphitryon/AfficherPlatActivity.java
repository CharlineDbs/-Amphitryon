package com.example.amphitryon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;

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

public class AfficherPlatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_plat);

        try {

            final JSONObject unPlat = new JSONObject(getIntent().getStringExtra("unPlat"));
            int codeCateg = unPlat.getInt("IDCATEG");
            Log.d("Test " , String.valueOf(codeCateg));

            listePlatsCateg((String.valueOf(codeCateg)));

            final Button buttonQuitterPlats = findViewById(R.id.btn_Quitter);
            buttonQuitterPlats.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent(AfficherPlatActivity.this, CategoriePlatActivity.class);
                    //startActivity(intent);
                    AfficherPlatActivity.this.finish();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected void onResume() {
        super.onResume();
        try {

            final JSONObject unPlat = new JSONObject(getIntent().getStringExtra("unPlat"));
            int codeCateg = unPlat.getInt("IDCATEG");
            Log.d("Test " , String.valueOf(codeCateg));

            listePlatsCateg((String.valueOf(codeCateg)));

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }


    public void listePlatsCateg(String codeCateg) throws IOException {

        OkHttpClient client = new OkHttpClient();
        ArrayList arrayListPlats = new ArrayList<String>();
        Plats lesPlats = new Plats();

        RequestBody formBody = new FormBody.Builder()
                .add("IDCATEG", codeCateg)
                .build();


        Request request = new Request.Builder()
                .url("http://10.100.0.6/~cdubos2/Amphitryon/controleurs/plat/listePlats.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public  void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();

                JSONArray jsonArrayPlats = null;
                try {
                    jsonArrayPlats = new JSONArray(responseStr);
                    for (int i = 0; i < jsonArrayPlats.length(); i++) {
                        JSONObject jsonPlat = jsonArrayPlats.getJSONObject(i);
                        Log.d("Test" , jsonPlat.toString());
                        Plat unPlat = new Plat(jsonPlat.getInt("IDPLAT"), jsonPlat.getInt("IDCATEG") ,jsonPlat.getString("NOMPLAT"), jsonPlat.getString("DESCRIPTIF"));
                        lesPlats.ajouterPlat(unPlat);
                    }


                    ListView listViewPlats = findViewById(R.id.listViewLesPlats);
                    List<Plat> plats = lesPlats.getPlats();
                    ArrayAdapter<String> arrayAdapterPlats = new ArrayAdapter<String>(AfficherPlatActivity.this, android.R.layout.simple_list_item_1, arrayListPlats);
                    runOnUiThread(() -> {listViewPlats.setAdapter(arrayAdapterPlats);});
                    runOnUiThread(() -> {listViewPlats.setAdapter(new PlatsListAdapter(getApplicationContext(),plats)); });

                    JSONArray finalJsonArrayPlats = jsonArrayPlats;
                    listViewPlats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            try {
                                JSONObject plat = finalJsonArrayPlats.getJSONObject(position);
                                Intent intent = new Intent(AfficherPlatActivity.this, PlatActivity.class);
                                intent.putExtra("plat" , plat.toString());
                                startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });



                } catch (JSONException e) {
                    Log.d("Test", e.getMessage());
                }

            }

            public void onFailure(Call call, IOException e)
            {
                Log.d("Test","erreur!!! connexion impossible");
            }

        });
    }


}
