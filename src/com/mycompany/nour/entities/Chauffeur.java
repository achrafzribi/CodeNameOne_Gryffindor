/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nour.entities;


/**
 *
 * @author medzr
 */
public class Chauffeur {

    
    
    private int id;
    private String nomc;
    private String prenomc;
    private int numc;
    private String adressec;
    private String emailc;
    
    public Chauffeur() {
    }

    public Chauffeur(String nomc, String prenomc, int numc, String adressec, String emailc) {
        this.nomc = nomc;
        this.prenomc = prenomc;
        this.numc = numc;
        this.adressec = adressec;
        this.emailc = emailc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomc() {
        return nomc;
    }

    public void setNomc(String nomc) {
        this.nomc = nomc;
    }

    public String getPrenomc() {
        return prenomc;
    }

    public void setPrenomc(String prenomc) {
        this.prenomc = prenomc;
    }

    public int getNumc() {
        return numc;
    }

    public void setNumc(int numc) {
        this.numc = numc;
    }

    public String getAdressec() {
        return adressec;
    }

    public void setAdressec(String adressec) {
        this.adressec = adressec;
    }

    public String getEmailc() {
        return emailc;
    }

    public void setEmailc(String emailc) {
        this.emailc = emailc;
    }
    
    public Chauffeur(int id, String nomc, String prenomc, int numc, String adressec, String emailc) {
        this.id = id;
        this.nomc = nomc;
        this.prenomc = prenomc;
        this.numc = numc;
        this.adressec = adressec;
        this.emailc = emailc;
    }
    
    
}
