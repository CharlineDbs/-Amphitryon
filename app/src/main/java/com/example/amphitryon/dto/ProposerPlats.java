package com.example.amphitryon.dto;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONObject;

import java.util.ArrayList;

public class ProposerPlats {

    private ArrayList<ProposerPlat> ProposerPlats;

    public ProposerPlats() {

        this.ProposerPlats = new ArrayList<>();
    }

    public ArrayList<ProposerPlat> getPlats(){

        return  ProposerPlats;
    }

    public Integer getNbPlatsProp() {

        return ProposerPlats.size();
    }

    public void ajouterProposerPlats(ProposerPlat unPlatProp) {
        ArrayList<ProposerPlat> listePlatsProp = new ArrayList<ProposerPlat>();
        ProposerPlats.add(unPlatProp);
    }

    public ProposerPlat getPlatsProp(Integer unIndex) {

        return ProposerPlats.get(unIndex);
    }



}
