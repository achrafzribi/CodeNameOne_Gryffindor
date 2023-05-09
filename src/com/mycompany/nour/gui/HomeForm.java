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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.nour.entities.Chauffeur;
import java.io.IOException;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
private static final char ADD_ICON_NAME = FontImage.MATERIAL_ADD_CIRCLE_OUTLINE;
    private static final char LIST_ICON_NAME = FontImage.MATERIAL_LIST_ALT;
    private static final char UPDATE_ICON_NAME = FontImage.MATERIAL_CREATE;
    private static final char DELETE_ICON_NAME = FontImage.MATERIAL_DELETE;
    private static final char CONSULT_ICON_NAME = FontImage.MATERIAL_PAGEVIEW;

    public HomeForm() {
      Chauffeur t = new Chauffeur();
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("Choose an option"));
        Button btnAddTask = new Button("Ajouter chauffeur");
                Button btnaff = new Button("afficher chauffuer");

        btnAddTask.setIcon(FontImage.createMaterial(ADD_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        Button btnListTasks = new Button("List Chauffeur");
                        btnListTasks.setIcon(FontImage.createMaterial(CONSULT_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));

        Button btnDeleteTasks = new Button("Delete Chauffeur");
                btnDeleteTasks.setIcon(FontImage.createMaterial(DELETE_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));

        btnAddTask.addActionListener(e-> new AddChauffeur(this).show());
                btnListTasks.addActionListener(e-> new Listchauffeur(this).show());

      //  btnListTasks.addActionListener(e-> new ListTasksForm(this).show());
       btnDeleteTasks.addActionListener(e -> new deleteChauffeur(this).show());
       // addAll(btnAddTask,btnListTasks,btnDeleteTasks);
        
              Button btnAddPassager = new Button("Ajouter passager");
               // Button btnaffp = new Button("afficher passager");

        btnAddPassager.setIcon(FontImage.createMaterial(ADD_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        
        Button btnListPassager= new Button("List Passagers");
                        btnListPassager.setIcon(FontImage.createMaterial(CONSULT_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
Button btnAddCov = new Button("Ajouter covoiturage");
Button btnAffpaiement = new Button("Afficher paiement");
Button btnAddpaiement = new Button("Ajouter paiement");
Button btnModifPaiement = new Button("Modifier paiement");
Button btnDelPaiement = new Button("Delete paiement");
        Button btnAffichcovoit= new Button("List covs");

btnAddCov.setIcon(FontImage.createMaterial(ADD_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
btnModifPaiement.setIcon(FontImage.createMaterial(ADD_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
btnAddpaiement.setIcon(FontImage.createMaterial(ADD_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
btnAffpaiement.setIcon(FontImage.createMaterial(ADD_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
btnDelPaiement.setIcon(FontImage.createMaterial(ADD_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
btnAffichcovoit.setIcon(FontImage.createMaterial(ADD_ICON_NAME, UIManager.getInstance().getComponentStyle("Button")));
        btnAddPassager.addActionListener(e-> new AddPassagerfForm(this).show());
                btnListPassager.addActionListener(e-> new ListPassagerForm(this).show());
                btnAddCov.addActionListener(e-> new AddTaskForm(this).show());
btnAffpaiement.addActionListener(e-> new ShowPaiementForm(this).show());
btnModifPaiement.addActionListener(e-> new ModifierPaiementForm(this).show());
btnAddpaiement.addActionListener(e-> new AjoutPaiementForm(this).show());
btnDelPaiement.addActionListener(e-> new SupprimerPaiementForm(this).show());
btnAffichcovoit.addActionListener(e-> new ListTasksForm(this).show());
        addAll(btnAddTask,btnListTasks,btnDeleteTasks,btnListPassager,btnAddPassager,btnAddCov,btnAffpaiement,btnAddpaiement,btnModifPaiement,btnDelPaiement,btnAffichcovoit);
        
        
    }
    
    
}
