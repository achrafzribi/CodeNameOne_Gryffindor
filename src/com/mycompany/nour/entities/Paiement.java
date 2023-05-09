/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;


/**
 *
 * @author sanabenfadhel
 */




public class Paiement {
    private int id;
    private String Description;
    private String datepaiement;
    private double montant;
    private String ipAddress;
    private int m_id ; 
  
    
    

    public Paiement(int id , double montant , String Description , String datePaiement, String ipAddress, int m_id ) {
        this.id= id;
        this.montant = montant;
        this.Description = Description;
        this.datepaiement = datePaiement;
        this.ipAddress = ipAddress;
        this.m_id= m_id ; 
     
        
    }
    
      public Paiement( int id ,String datePaiement, String Description ) {
        this.id=id;
        
        this.Description = Description;
        this.datepaiement = datePaiement; 
         
    }
    
    
    public Paiement( double montant , String Description , String datePaiement, String ipAddress , int m_id) {
        this.montant = montant;
        this.Description = Description;
        this.datepaiement = datePaiement;
        this.ipAddress = ipAddress;
        this.m_id= m_id;
        
    }
     public Paiement( double montant , String Description , String datePaiement) {
        this.montant = montant;
        this.Description = Description;
        this.datepaiement = datePaiement;
        this.m_id= m_id;
        
    }
    public Paiement( double montant , String Description , String ip, int m_id) {
        this.montant = montant;
        this.Description = Description;
        this.ipAddress= ip ; 
       
        this.m_id= m_id;
        
    }
     

    public Paiement() {
        
    }

   
    
    public String getIpAddress(){
        return ipAddress;
    }
    public void setIpAddress(String ipAddress){
        this.ipAddress = ipAddress; 
    }
    
    public int getM_id(){
        return m_id;
    }
    public void setM_id(int m_id){
        this.m_id = m_id;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 

    public double getmontant() {
        return montant;
    }

    public void setmontant(double montant) {
        this.montant = montant;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDate() {
        return datepaiement;
    }

    public void setDate(String datepaiement) {
        this.datepaiement = datepaiement;
    }
 
    
    @Override
    public String toString() {
        return "Paiement{" + "id=" + id + ", Date Paiement=" + datepaiement + ", Description=" + Description + ", montant=" + montant + ", ipAddress = " + ipAddress + '}';
    }
    
    
}
