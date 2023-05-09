/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entites;

/**
 *
 * @author imtinen
 */
public class Passager {
    int idp;
    String nomp,prenomp,adressep,emailp,nump;

    
    public Passager(){}

    public Passager(int idp, String nomp) {
        this.idp = idp;
        this.nomp = nomp;
    }
    
 
  
    
    public Passager(String nomp ,String prenomp, String nump, String adressep, String emailp) {
        
        this.nomp = nomp;
        this.prenomp = prenomp;
        this.nump = nump;
        this.adressep = adressep;
        this.emailp = emailp;
  
    }

    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public String getNomp() {
        return nomp;
    }

    public void setNomp(String nomp) {
        this.nomp = nomp;
    }

    public String getPrenomp() {
        return prenomp;
    }

    public void setPrenomp(String prenomp) {
        this.prenomp = prenomp;
    }

    public String getNump() {
        return nump;
    }

    public void setNump(String nump) {
        this.nump = nump;
    }

    public String getAdressep() {
            return adressep;
    }

    public void setAdressep(String adressep) {
        this.adressep = adressep;
    }
    public String getEmailp() {
            return emailp;
    }

    public void setEmailp(String emailp) {
        this.emailp = emailp;
    }


    @Override
    public String toString() {
        return "Passager{" + "nom=" + nomp + ", prenom=" + prenomp + ", num=" + nump + ", adresse=" + adressep + ", email=" + emailp +'}';
    }



  

    
}
