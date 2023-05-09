package com.mycompany.nour.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Covoiturage;
import com.mycompany.nour.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceCovoiturage {
   
    private ArrayList<Covoiturage> Covs;
   
    public static ServiceCovoiturage instance = null;
    private boolean resultOK;
    private ConnectionRequest req;
   
    private ServiceCovoiturage() {
        req = new ConnectionRequest();
    }
   
    public static ServiceCovoiturage getInstance() {
        if (instance == null) {
            instance = new ServiceCovoiturage();
        }
        return instance;
    }
   
    public boolean addTask(Covoiturage t) {
        int idc = t.getIdc();
        String depart = t.getDepart();
        String destination = t.getDestination();
        String datecovoiturage = t.getDatecovoiturage();
        int prix = t.getPrix();
        int nbrplace = t.getNbrplace();
        String vehicule = t.getVehicule();
       
        String url = Statics.BASE_URL + "/addCovoiturageJSON?idc=" + idc + "&depart=" + depart + "&destination=" + destination + "&datecovoiturage=" + datecovoiturage + "&prix=" + prix + "&nbrplace=" + nbrplace + "&vehicule=" + vehicule;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
   
    public ArrayList<Covoiturage> parseCovoiturages(String jsonText) {
        try {
            Covs = new ArrayList<>();
            JSONParser parser = new JSONParser();
            Map<String, Object> covoituragesJson = parser.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) covoituragesJson.get("root");
            for (Map<String, Object> obj : list) {
                Covoiturage covoiturage = new Covoiturage();
               // double id = Double.parseDouble(obj.get("id").toString());
 double idc = Double.parseDouble(obj.get("idc").toString());
 double prix = Double.parseDouble(obj.get("prix").toString());
  double nbrplace = Double.parseDouble(obj.get("nbrplace").toString());


 //covoiturage.setId((int) id);
   covoiturage.setIdc((int) idc);
   covoiturage.setPrix((int) prix);
   covoiturage.setNbrplace((int) nbrplace);
   
 covoiturage.setDepart(obj.get("depart").toString());
                covoiturage.setDestination(obj.get("destination").toString());
                covoiturage.setVehicule(obj.get("vehicule").toString());
              //  covoiturage.setIdc((int) Float.parseFloat(obj.get("idc").toString()));
               // covoiturage.setNbrplace((int) Float.parseFloat(obj.get("nbrplace").toString()));
                //covoiturage.setPrix((int) Float.parseFloat(obj.get("prix").toString()));
                covoiturage.setDatecovoiturage(obj.get("datecovoiturage").toString());
                Covs.add(covoiturage);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Covs;
    }
   
    public ArrayList<Covoiturage> getAllTasks() {
        String url = "http://127.0.0.1:8000/listeCovs";
       
       
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            if (req.getResponseData() != null) {
                Covs = parseCovoiturages(new String(req.getResponseData()));
            }
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return Covs;
}

 

   
    public boolean deleteTask(int id) {
        String url = Statics.BASE_URL + "/alllaaa/mmm/" + id;
       
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
public boolean updateTask(Covoiturage t) {
       
        int id = t.getId() ;
        int idc = t.getIdc() ;
        String depart = t.getDepart();
        String destination = t.getDestination();
        String datecovoiturage = t.getDatecovoiturage();
        int prix = t.getPrix();
        int nbrplace = t.getNbrplace();
        String vehicule = t.getVehicule();
       
        System.out.println(t);

        String url = Statics.BASE_URL + "/updateCovoiturageJSON?id=" + id + "&idc=" +idc + "&depart=" + depart + "&destination=" + destination + "&datecovoiturage=" + datecovoiturage + "&prix=" + prix + "&nbrplace="+ nbrplace + "&vehicule=" + vehicule ;
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
   
      public ArrayList<Covoiturage> searchTask(String query) {
    ArrayList<Covoiturage> result = new ArrayList<>();
    for (Covoiturage covoiturage : Covs) {
        if (covoiturage.getDepart().toLowerCase().contains(query.toLowerCase()) ||
            covoiturage.getDestination().toLowerCase().contains(query.toLowerCase())
           ) {
            result.add(covoiturage);
        }
    }
    return result;
}

}
