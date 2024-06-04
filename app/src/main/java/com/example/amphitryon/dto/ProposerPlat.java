package com.example.amphitryon.dto;

import androidx.appcompat.app.AppCompatActivity;
import java.io.Serializable;
import android.os.Bundle;

public class ProposerPlat implements Serializable{

    private int idService;
    private String dateService;
    private int idPlat;
    private int quantiteProposee;
    private double prixVente;
    private int qteDispo;
    private int qteVendue;

    public ProposerPlat(int idService, String dateService, int idPlat, int quantiteProposee, double prixVente, int qteDispo, int qteVendue){

        this.idPlat = idPlat;
        this.idService = idService;
        this.dateService = dateService;
        this.quantiteProposee = quantiteProposee;
        this.prixVente = prixVente;
        this.qteDispo = qteDispo;
        this.qteVendue = qteVendue;

    }


}
