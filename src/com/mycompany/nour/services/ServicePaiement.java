/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nour.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Paiement;
import com.mycompany.nour.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author INFOTEC
 */
public class ServicePaiement {
    public static ServicePaiement instance = null ; 
     public boolean resultOK;
    private ConnectionRequest req;
    
    public static ServicePaiement getInstance(){
        if(instance==null)
            instance = new ServicePaiement();
            return instance;
        
    }
    
    public ServicePaiement(){
        req = new ConnectionRequest();
    }
    //ajouter
    public boolean AjoutPaiement(Paiement paiement){
        
        String url = Statics.BASE_URL+"/ajouterP?amount="+paiement.getmontant() +"&PaiementDate="+paiement.getDate() +"&description=" +paiement.getDescription()+"&m_id="+paiement.getM_id()+"&ip_address=" +paiement.getIpAddress();
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            resultOK = req.getResponseCode() == 200;
            System.out.println("data == " +str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    //display all paiements 
    public ArrayList<Paiement> getAllPaiements() {
    ArrayList<Paiement> paiementsList = new ArrayList<>();
    String url = Statics.BASE_URL + "get";

    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener((evt) -> {
        JSONParser jsonParser = new JSONParser();
        try {
            Map<String, Object> response = jsonParser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
            List<Map<String, Object>> paiements = (List<Map<String, Object>>) response.get("root");

            for (Map<String, Object> paiement : paiements) {
                int id = (int) Float.parseFloat(paiement.get("id").toString());
                String date = paiement.get("PaiementDate").toString();
                String description = paiement.get("Description").toString();
                float amount = Float.parseFloat(paiement.get("Amount").toString());
               
                
                Paiement p = new Paiement(amount, description,date);
                paiementsList.add(p);
            }

        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return paiementsList;
}
    
    //supprimer paiement 
    
    public boolean supprimerPaiement(int id) {
    String url = Statics.BASE_URL + "/supprimer?id=" + id;

    req.setUrl(url);
    req.addResponseListener((e) -> {
        String str = new String(req.getResponseData());
        resultOK = req.getResponseCode() == 200;
        System.out.println("data == " + str);
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}

    
    //modifier paiement 
    public boolean modifierPaiement(Paiement paiement) {
    String url = Statics.BASE_URL + "/modifier?id=" + paiement.getId() 
                + "&description=" + paiement.getDescription() 
                + "&paiement_date=" + paiement.getDate();

    req.setUrl(url);
    req.addResponseListener((e) -> {
        String str = new String(req.getResponseData());
        resultOK = req.getResponseCode() == 200;
        System.out.println("data == " + str);
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}
    
    
    
    //detail function still not ready 

    public ArrayList<Paiement> getpaiementById(int idd) {
    ArrayList<Paiement> paiementsList = new ArrayList<>();
    String url = Statics.BASE_URL +"/paiement/get";

    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener((evt) -> {
        JSONParser jsonParser = new JSONParser();
        try {
            Map<String, Object> response = jsonParser.parseJSON(new InputStreamReader(new ByteArrayInputStream(req.getResponseData()), "UTF-8"));
            List<Map<String, Object>> paiements = (List<Map<String, Object>>) response.get("root");

           
    for (Map<String, Object> paiement : paiements) {
        int id = (int) Float.parseFloat(paiement.get("id").toString());
        if (id == idd) {
            String date = paiement.get("PaiementDate").toString();
            String description = paiement.get("Description").toString();
            float amount = Float.parseFloat(paiement.get("Amount").toString());

            Paiement p = new Paiement(amount, description, date);
            paiementsList.add(p);
        
    }
}

        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return paiementsList;
}

     
    
   
}
