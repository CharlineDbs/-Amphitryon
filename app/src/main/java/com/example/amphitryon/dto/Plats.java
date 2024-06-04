package com.example.amphitryon.dto;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

public class Plats {

    private ArrayList<Plat> plats;

    public Plats() {
        this.plats = new ArrayList<>();
    }

    public ArrayList<Plat> getPlats(){
        return  plats;
    }

    public Integer getNbPlats() {
        return plats.size();
    }

    public void ajouterPlat(Plat unPlat) {
        ArrayList<Plat> listePlats = new ArrayList<Plat>();
        plats.add(unPlat);
    }

    public Plat getPlats(Integer unIndex) {
        return plats.get(unIndex);
    }

}