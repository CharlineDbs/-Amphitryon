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

public class CategoriePlatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie_plat);
        try {
            listeCategorie();

            final Button buttonQuitterCategorie = findViewById(R.id.btn_Quitter);
            buttonQuitterCategorie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CategoriePlatActivity.this, ChefCuisinierActivity.class);
                    startActivity(intent);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listeCategorie() throws IOException {

        OkHttpClient client = new OkHttpClient();
        ArrayList arrayListCategorie = new ArrayList<String>();

        Request request = new Request.Builder()
                .url("http://10.100.0.6/~cdubos2/Amphitryon/controleurs/categorie/listeCategorie.php")
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();
                final JSONArray jsonArrayCategorie ;
                try {
                    jsonArrayCategorie = new JSONArray(responseStr);

                    for (int i = 0; i < jsonArrayCategorie.length(); i++) {
                        JSONObject jsonCategorie = null;
                        jsonCategorie = jsonArrayCategorie.getJSONObject(i);
                        arrayListCategorie.add(jsonCategorie.getString("LIBELLECATEG"));
                    }


                    ListView listViewCateg = findViewById(R.id.listViewCateg);
                    ArrayAdapter<String> arrayAdapterCateg = new ArrayAdapter<String>(CategoriePlatActivity.this, android.R.layout.simple_list_item_1, arrayListCategorie);
                    runOnUiThread(() -> {listViewCateg.setAdapter(arrayAdapterCateg);});
                    listViewCateg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            try {
                                JSONObject unPlat = jsonArrayCategorie.getJSONObject(position);
                                Intent intent = new Intent(CategoriePlatActivity.this, AfficherPlatActivity.class);
                                intent.putExtra("unPlat" , unPlat.toString());
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