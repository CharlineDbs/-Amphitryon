package com.example.amphitryon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChefCuisinierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_cuisinier);

        final Button btnQuitter = findViewById(R.id.btn_Quitter);
        btnQuitter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ChefCuisinierActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final Button btnAfficherPlats = findViewById(R.id.btnAfficherPlat);
        btnAfficherPlats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChefCuisinierActivity.this, CategoriePlatActivity.class);
                startActivity(intent);
            }
        });

        final Button btnAjouterPlats = findViewById(R.id.btnAjouterPlat);
        btnAjouterPlats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChefCuisinierActivity.this, AjouterPlatActivity.class);
                startActivity(intent);
            }
        });

        final Button btnProposerPlats =findViewById(R.id.btnProposerPlat);
        btnProposerPlats.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ChefCuisinierActivity.this, ServiceProposerPlatActivity.class);
            startActivity(intent);
        }
        });

    }

}