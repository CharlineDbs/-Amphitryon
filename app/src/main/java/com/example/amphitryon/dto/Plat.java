package com.example.amphitryon.dto;

import androidx.appcompat.app.AppCompatActivity;
import java.io.Serializable;
import android.os.Bundle;

public class Plat implements Serializable {

    private int idPlat;
    private int idCateg;
    private String nomPlat;
    private String decriptif;


    public Plat(int idPlat, int idCateg, String nomPlat, String descriptif){

        this.idPlat = idPlat;
        this.idCateg = idCateg;
        this.nomPlat = nomPlat;
        this.decriptif = descriptif;

    }

    public String getNomPlat(){
        return nomPlat;
    }

}