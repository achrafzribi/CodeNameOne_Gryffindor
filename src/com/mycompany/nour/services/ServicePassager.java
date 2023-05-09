package com.mycompany.nour.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entites.Passager;
import com.mycompany.nour.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServicePassager {
    private static ServicePassager instance;
    private ConnectionRequest req;
    
    private ServicePassager() {
        req = new ConnectionRequest();
    }
    
    public static ServicePassager getInstance() {
        if (instance == null) {
            instance = new ServicePassager();
        }
        return instance;
    }
        
    public boolean addPassager(Passager l) {
  String url = Statics.BASE_URL + "/passager/AddjsonPP?nomp=" + l.getNomp() + "&prenomp=" + l.getPrenomp()+ "&nump=" + l.getNump()+ "&adressep=" + l.getAdressep() +"&emailp=" +l.getEmailp();

        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return req.getResponseCode() == 200;
    }
 
public ArrayList<Passager> parsePassagers(String jsonText) throws ParseException {
    System.out.println("Début parsing");
    ArrayList<Passager> passagers = new ArrayList<>();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> passagerListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) passagerListJson.get("root");
        for (Map<String, Object> obj : list) {
            Passager a = new Passager();
            // int idf = (int) Integer.parseInt(obj.get("idf").toString());
            int idp = (int) Float.parseFloat(obj.get("idp").toString());
            a.setIdp(idp);
            a.setNomp(obj.get("nomp").toString());
            a.setPrenomp(obj.get("prenomp").toString());
            a.setNump(obj.get("nump").toString());
            a.setAdressep(obj.get("adressep").toString());
            a.setEmailp(obj.get("emailp").toString());
            
            passagers.add(a);
         
        }
        
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println(passagers);
    return passagers;
}
public ArrayList<Passager> getAllPassagers() {
    String url = Statics.BASE_URL + "/passager/Alllpassager";
    System.out.println(url);
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(false);
    NetworkManager.getInstance().addToQueueAndWait(req);
    String response = new String(req.getResponseData());
    try {
        return parsePassagers(response); // Correction ici
    } catch (ParseException ex) {
        System.out.println(ex.getMessage());
        return new ArrayList<>();
    }
}


public boolean deletePassager(int idf) {
    String url = Statics.BASE_URL + "/passager/deletePassagerJSON/" + idf;
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    request.setHttpMethod("DELETE");
    NetworkManager.getInstance().addToQueueAndWait(request);
    return request.getResponseCode() == 200;
}
public boolean modifierPassager(Passager l){
     
String url = Statics.BASE_URL + "/passager/updatePassagerJSON/" + l.getIdp() + "?nomp=" + l.getNomp() + "&prenomp=" + l.getPrenomp()+ "&nump=" + l.getNump()+ "&adressep=" + l.getAdressep()+ "&emailp=" +l.getEmailp();
       // req.setUrl(url);
    req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            private boolean resultOK;
            

            public void actionPerformed(NetworkEvent evt) {
                 resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        boolean resultOK = false;
        return resultOK;
    }
}