/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nour.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.nour.entities.Chauffeur;
import com.mycompany.nour.services.ChauffeurService;

/**
 *
 * @author medzr
 */
public class updatechauffeur extends Form {

    public updatechauffeur(Form previous, Chauffeur t) {
        setTitle("update");
        setLayout(BoxLayout.y());
  Label departLabels = new Label("Depart: " );
        TextField departLabel = new TextField(t.getNomc(), "nomc");
        TextField destinationLabel = new TextField(t.getPrenomc(), "prenomc");
       //  TextField dateLabel = new TextField(t.getNumc(), "numc");
        TextField telephonelab = new TextField(t.getAdressec(), "adressec");
        TextField namelab = new TextField(t.getEmailc(), "emailc");
           //     TextField userlab = new TextField(Integer.toString(t.getUser()), "user");
                TextField tfnum = new TextField(Integer.toString(t.getNumc()), "numc");
            
  
      
       

         
        
        
        
        Button btnUpdate = new Button("Update");
        btnUpdate.addPointerPressedListener(e -> {
            t.setNomc(departLabel.getText());
            t.setPrenomc(destinationLabel.getText());
            t.setAdressec(telephonelab.getText());
                        t.setEmailc(namelab.getText());
        //    int user = Integer.parseInt(userlab.getText());
            int num = Integer.parseInt(tfnum.getText());
       //     int id_utilisateur = Integer.parseInt(tfidutilisateur.getText());
         //   int prix = Integer.parseInt(tfprix.getText());
       //     t.setUser(user);
              //          t.setNbrplace(nbrplace);
         //   t.setPrix(prix);

        //  t.setId_utilisateur(id_utilisateur);
            t.setNumc(num);
            boolean success = ChauffeurService.getInstance().updateTask(t);
            if (success) {
                // Remove task from UI
                previous.showBack();
            }

        }
        );
        addAll(departLabel, destinationLabel, telephonelab, namelab  , tfnum , btnUpdate);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
 
    
    
    
}
