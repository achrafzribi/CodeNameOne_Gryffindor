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
import com.codename1.ui.events.ActionListener;
import com.mycompany.nour.entities.Chauffeur;
import com.mycompany.nour.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author abbes
 */
public class ChauffeurService {
    
      public ArrayList<Chauffeur> cov;
    
    public static ChauffeurService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    private ChauffeurService() {
        req = new ConnectionRequest();
    }
    
    public static ChauffeurService getInstance() {
        if (instance == null) {
            instance = new ChauffeurService();
        }
        return instance;
    }
    
    public boolean addTask(Chauffeur t) {
        
        String nomc = t.getNomc();
        String prenomc = t.getPrenomc();
          String adressec = t.getAdressec();
                    String emailc = t.getEmailc();

        int numc = t.getNumc();
               
        System.out.println(t);

        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        // http://127.0.0.1:8000/covoiturage/addVoitureJSON?depart=%22radeqq%22&date_covoiturage=2023-04-08&prix=84&destination=%22deemej%22&id_utilisateur=29&nbrplace=27&user=27&longitude=27.3&latitude=27.0&nom=aaa&telephone=20108815
            String url = Statics.BASE_URL + "/chauffeur/addChauffeurJSON?nomc=" +nomc+ "&prenomc=" + prenomc + "&numc=" + numc + "&adressec=" + adressec + "&emailc=" + emailc ;

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
    }

    public boolean ajouterChauffeur(Chauffeur t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

        
public ArrayList<Chauffeur> parseCovoiturages(String jsonText) {
     try {
   // ArrayList<Covoiturage> cov = new ArrayList<>();
   cov = new ArrayList<>() ; 
        JSONParser parser = new JSONParser();
        Map<String, Object> covoituragesJson = parser.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) covoituragesJson.get("root");
        for (Map<String, Object> obj : list) {
            Chauffeur covoiturage = new Chauffeur();
            covoiturage.setId((int)Float.parseFloat(obj.get("id").toString()));
            covoiturage.setNomc(obj.get("nomc").toString());
            covoiturage.setEmailc(obj.get("emailc").toString());
           covoiturage.setPrenomc(obj.get("prenomc").toString());
            covoiturage.setAdressec(obj.get("adressec").toString());
            covoiturage.setNumc((int)Float.parseFloat(obj.get("numc").toString()));
           
            cov.add(covoiturage);
        }
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return cov;
}
    
   public ArrayList<Chauffeur> getAllTasks() {
    String url = Statics.BASE_URL + "/chauffeur/display/list";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            if (req.getResponseData() != null) {
                cov = parseCovoiturages(new String(req.getResponseData()));
            }
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return cov;
}

      public ArrayList<Chauffeur> searchTask(String query) {
    ArrayList<Chauffeur> result = new ArrayList<>();
    for (Chauffeur covoiturage : cov) {
        if (covoiturage.getNomc().toLowerCase().contains(query.toLowerCase()) ||
            covoiturage.getPrenomc().toLowerCase().contains(query.toLowerCase()) 
           ) {
            result.add(covoiturage);
        }
    }
    return result;
}
      
        public boolean deleteTask(int id) {
        String url = Statics.BASE_URL + "/chauffeur/deleteTaskJSON/" + id;
       
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return resultOK;
    }

         
   
    public boolean updateTask(Chauffeur t) {
        
   //     String url = Statics.BASE_URL + "/updateVoitureJSONs/sssss/"+t.getId()+"?name=" + t.getName() + "&description=" + t.getDescription() + "&state=" + t.isState() + "&priority=" + t.getPriority() + "&child=" +14;
                String url = Statics.BASE_URL + "/chauffeur/updatejson/"+t.getId()+"?nomc=" + t.getNomc()+ "&prenomc=" + t.getPrenomc()+ "&numc=" + t.getNumc()+ "&adressec=" + t.getAdressec()+ "&emailc=" + t.getEmailc() ;

        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
  

    
    
  
         
         
         
    
}
