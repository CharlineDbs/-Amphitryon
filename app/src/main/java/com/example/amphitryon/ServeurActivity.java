package com.example.amphitryon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServeurActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serveur);

        final Button btnQuitter = findViewById(R.id.btn_Quitter);
        btnQuitter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(ServeurActivity.this, MainActivity.class );
                startActivity(intent);
            }
        });

    }
}