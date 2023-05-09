package com.mycompany.nour.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;


import com.mycompany.nour.entities.Chauffeur;
import com.mycompany.nour.services.ChauffeurService;

/**
 *
 * @author Oubeid Mezni
 */
public class AddChauffeur extends Form {

    public AddChauffeur(Form previous) {
        setTitle("Ajouter Covoiturage");
        setLayout(BoxLayout.y());
        
     /*   TextField tfdepart = new TextField("", "depart");
        TextField tfDescription = new TextField("", "Description");
        TextField tfPriority = new TextField("", "Priority");
        CheckBox cbState = new CheckBox("State"); */ 
        TextField nom = new TextField("", "nomc");
        TextField prenom = new TextField("", "prenomc");
        TextField num = new TextField("", "numc");
             TextField adress = new TextField("", "adressec");
        TextField email = new TextField("", "emailc");
       
     //   TextField latitudelab = new TextField("", "latitude");
       // TextField longitudelab = new TextField("", "longitude");
      //  TextField userlab = new TextField("", "user");
        //TextField id_utilisateurlab = new TextField("", "idutilisateur");

         

        Button btnValider = new Button("Add Covoiturage");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((email.getText().length() == 0) || (nom.getText().length() == 0)
                        || (adress.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the required fields", new Command("OK"));
                } else {
                    try {
                        int numc = Integer.parseInt(num.getText());
          //             int nbrplace = Integer.parseInt(nbrplaceLabel.getText());
//int user=Integer.parseInt(userlab.getText());
//float latitude = Float.parseFloat(latitudelab.getText());
//int id_utilisateur=Integer.parseInt(id_utilisateurlab.getText());

//float longitude = Float.parseFloat(longitudelab.getText());

                       // boolean state = cbState.isSelected();
                        Chauffeur t = new Chauffeur(nom.getText(), prenom.getText(), numc , adress.getText() , 
                           email.getText() );
                        System.out.println(t);
                        if (ChauffeurService.getInstance().addTask(t)) {
                            Dialog.show("Success", "Task added successfully", new Command("OK"));
                            previous.showBack();
                        } else {
                            Dialog.show("Error", "Task could not be added", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("Error", "Priority, Child and Parent fields must be numbers", new Command("OK"));
                    }
                }
            }
        });
      
        addAll(nom, prenom, num, adress, email   ,  btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }  
}
 