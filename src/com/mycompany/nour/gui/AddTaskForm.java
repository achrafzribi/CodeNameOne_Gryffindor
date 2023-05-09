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
import com.mycompany.myapp.entities.Covoiturage;

import com.mycompany.nour.services.ServiceCovoiturage;


/**
 *
 * @author Oubeid Mezni
 */
public class AddTaskForm extends Form {

    public AddTaskForm(Form previous) {
        setTitle("Ajouter Covoiturage");
        setLayout(BoxLayout.y());
        
     /*   TextField tfdepart = new TextField("", "depart");
        TextField tfDescription = new TextField("", "Description");
        TextField tfPriority = new TextField("", "Priority");
        CheckBox cbState = new CheckBox("State"); */ 
             TextField telephonelab = new TextField("", "idc");   
     TextField departLabel = new TextField("", "depart");
        TextField destinationLabel = new TextField("", "destination");
            TextField dateLabel = new TextField("", "datecovoiturage");
        TextField prixLabel = new TextField("", "prix");
        TextField nbrplaceLabel = new TextField("", "nbrplace");
    

        TextField namelab = new TextField("", "vehicule");
     //   TextField latitudelab = new TextField("", "latitude");
       // TextField longitudelab = new TextField("", "longitude");
      //  TextField userlab = new TextField("", "user");
        //TextField id_utilisateurlab = new TextField("", "idutilisateur");

         

        Button btnValider = new Button("Add Covoiturage");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((departLabel.getText().length() == 0) || (destinationLabel.getText().length() == 0)
                        || (telephonelab.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the required fields", new Command("OK"));
                } else {
                    try {
                int idc = Integer.parseInt(telephonelab.getText());
                        int prix = Integer.parseInt(prixLabel.getText());
                       int nbrplace = Integer.parseInt(nbrplaceLabel.getText());
                       
//int user=Integer.parseInt(userlab.getText());
//float latitude = Float.parseFloat(latitudelab.getText());
//int id_utilisateur=Integer.parseInt(id_utilisateurlab.getText());

//float longitude = Float.parseFloat(longitudelab.getText());


                       // boolean state = cbState.isSelected();
                       // Covoiturage t = new Covoiturage(departLabel.getText(), destinationLabel.getText(), telephonelab.getText() , namelab.getText() , 
                         //       prix, nbrplace   , dateLabel.getText() );
                       Covoiturage t = new Covoiturage( idc , departLabel.getText(), destinationLabel.getText(),dateLabel.getText(),prix,nbrplace , namelab.getText());
                        System.out.println(t);
                        if (ServiceCovoiturage.getInstance().addTask(t)) {
                            Dialog.show("Success", "Covoiturage added successfully", new Command("OK"));
                            previous.showBack();
                        } else {
                            Dialog.show("Error", "Covoiturage could not be added", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("Error", "Priority, Child and Parent fields must be numbers", new Command("OK"));
                    }
                }
            }
        });
      
        addAll(telephonelab, departLabel ,destinationLabel,dateLabel, prixLabel, nbrplaceLabel ,namelab   ,  btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }  
}
 