/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author abbes
 */
public class Covoiturage {
           
     private int id;
    private String depart,destination;
  
    private int Prix,nbrplace ;
   
    private int idc;
private String datecovoiturage, vehicule;


    public Covoiturage() {
    }

    public Covoiturage(int idc ,String depart, String destination,String datecovoiturage ,int Prix, int nbrplace,  String vehicule) {
        this.idc = idc;
        this.depart = depart;
        this.destination = destination;
        this.datecovoiturage = datecovoiturage;
        this.Prix = Prix;
         
        this.nbrplace = nbrplace;
        
       
        this.vehicule = vehicule;
    }

    public Covoiturage(int id, String depart, String destination, int Prix, int nbrplace, int idc, String datecovoiturage, String vehicule) {
        this.id = id;
        this.depart = depart;
        this.destination = destination;
        this.Prix = Prix;
        this.nbrplace = nbrplace;
        this.idc = idc;
        this.datecovoiturage = datecovoiturage;
        this.vehicule = vehicule;
    }

    public Covoiturage(int id, String depart, String destination, String telephone, String nom, int Prix, int nbrplace, float latitude, float longitude, int idc, String datecovoiturage) {
        this.id = id;
        this.depart = depart;
        this.destination = destination;
    
        
        this.Prix = Prix;
        this.nbrplace = nbrplace;
        this.idc = idc;
        
        this.datecovoiturage = datecovoiturage;
    }

    public Covoiturage(String depart, String destination, String telephone, String nom, int Prix, int nbrplace, int idc, int user, String datecovoiturage) {
        this.depart = depart;
        this.destination = destination;
        
        
        this.Prix = Prix;
        this.nbrplace = nbrplace;
       
        this.idc = idc;
     
        this.datecovoiturage = datecovoiturage;
    }

   

    public Covoiturage(int id, String depart, String destination, int Prix, int nbrplace, String datecovoiturage) {
        this.id = id;
        this.depart = depart;
        this.destination = destination;
        this.Prix = Prix;
        this.nbrplace = nbrplace;
        this.datecovoiturage = datecovoiturage;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getPrix() {
        return Prix;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    public int getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(int nbrplace) {
        this.nbrplace = nbrplace;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public String getDatecovoiturage() {
        return datecovoiturage;
    }

    public void setDatecovoiturage(String datecovoiturage) {
        this.datecovoiturage = datecovoiturage;
    }

    public String getVehicule() {
        return vehicule;
    }

    public void setVehicule(String vehicule) {
        this.vehicule = vehicule;
    }

    @Override
    public String toString() {
        return "Covoiturage{" + "id=" + id + ", depart=" + depart + ", destination=" + destination + ", Prix=" + Prix + ", nbrplace=" + nbrplace + ", idc=" + idc + ", datecovoiturage=" + datecovoiturage + ", vehicule=" + vehicule + '}';
    }

    
   

    

    

}
