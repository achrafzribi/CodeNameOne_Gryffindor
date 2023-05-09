/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nour.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.nour.entities.Chauffeur;
import com.mycompany.nour.services.ChauffeurService;
import java.util.ArrayList;

/**
 *
 * @author medzr
 */
public class deleteChauffeur extends Form {
    
    public deleteChauffeur(Form previous) {
        setTitle("Delete tasks");
        setLayout(BoxLayout.y());

        ArrayList<Chauffeur> covs = ChauffeurService.getInstance().getAllTasks();
        for (Chauffeur t : covs) {
            addElement(t);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

   

    private void addElement(Chauffeur covoiturage) {
         Label nomLabel = new Label("nom: " + covoiturage.getNomc());
        Label prenomabel = new Label("prenom: " + covoiturage.getPrenomc());
        Label numLabel = new Label("num: " + covoiturage.getNumc());
        Label emailLabel = new Label("email: " + covoiturage.getEmailc());
        Label adresseLabel = new Label("adresse: " + covoiturage.getAdressec());
      
               // Label latitude = new Label("telephone: " + covoiturage.getLatitude());
                // Label longitude = new Label("longitude: " + covoiturage.getLongitude());
        Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(e -> {
        boolean success = ChauffeurService.getInstance().deleteTask(covoiturage.getId());
        if (success) {
            // Remove task from UI
            Component taskComponent = deleteButton.getParent();
            taskComponent.remove();
            taskComponent.setHidden(true);
        }
    });
        Container taskContainer = BoxLayout.encloseY(
                 nomLabel,
                prenomabel,
                numLabel,
                emailLabel,
                adresseLabel,
             
                deleteButton
        );

        add(taskContainer);
    }

    
}
