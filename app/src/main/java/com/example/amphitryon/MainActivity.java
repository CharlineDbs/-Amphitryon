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

public class MainActivity extends AppCompatActivity {
    String responseStr;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button btnValider = findViewById(R.id.btnValider);
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Appel de la fonction authentification
                try {
                    authentification();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        final Button btnQuitter = findViewById(R.id.btnQuitter);
        btnQuitter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.finish();

            }
        });

    }

    public void authentification() throws IOException {

        final EditText textLogin = findViewById(R.id.editTextLogin);
        final EditText textMdp = findViewById(R.id.editTextMdp);

        RequestBody formBody = new FormBody.Builder()
                .add("LOGIN", textLogin.getText().toString())
                .add("MDP",  textMdp.getText().toString())
                .build();

        Request request = new Request.Builder()
                .url("http://localhost/public_html/Amphitryon/controleurs/authentification.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public  void onResponse(Call call, Response response) throws IOException {

                responseStr = response.body().string();

                if (responseStr.compareTo("false")!=0){
                    try {
                        JSONObject personnel = new JSONObject(responseStr);
                        Log.d("test", responseStr);
                        Log.d("Test",personnel.getString("NOM") + " est  connecté(e)");
                        if(personnel.getInt("IDSTATUT") == 1) {
                            Intent intent = new Intent(MainActivity.this, ChefSalleActivity.class);
                            intent.putExtra("personnel", personnel.toString());
                            startActivity(intent);
                        }
                        else if(personnel.getInt("IDSTATUT") == 2){
                            Intent intent = new Intent(MainActivity.this, ChefCuisinierActivity.class);
                            intent.putExtra("personnel", personnel.toString());
                            startActivity(intent);
                        }
                        else if(personnel.getInt("IDSTATUT") == 3){
                            Intent intent = new Intent(MainActivity.this, ServeurActivity.class);
                            intent.putExtra("personnel", personnel.toString());
                            startActivity(intent);
                        }
                    }
                    catch(JSONException e){
                        // Toast.makeText(MainActivity.this, "Erreur de connexion !!!! !", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("Test","Login ou mot de  passe non valide !");
                }
            }

            public void onFailure(Call call, IOException e)
            {
                Log.d("Test","erreur!!! connexion impossible");
            }

        });
    }


}