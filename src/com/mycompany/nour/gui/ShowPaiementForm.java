/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nour.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Paiement;
import com.mycompany.nour.services.ServicePaiement;
import java.util.ArrayList;

/**
 *
 * @author INFOTEC
 */
public class ShowPaiementForm extends Form{

    
    public ShowPaiementForm(Form previous) {
    setTitle("Payment Details");
    setLayout(BoxLayout.y());
    
    TextField id = new TextField("","Id");
    id.setConstraint(TextArea.NUMERIC);
    
     Button btnValider = new Button("Find Paiement");
     
     btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((id.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
              else
                {
                    int idd = Integer.parseInt(id.getText());
                        ArrayList<Paiement> Paiements = ServicePaiement.getInstance().getpaiementById(idd);
        for (Paiement p : Paiements) {
            addElement(p);         
                }
                
                
            }

            
            }
     });
     
  addAll(id, btnValider);
 getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
 
}
        public void addElement(Paiement p ) {

        Container container = new Container(new BorderLayout());

    Label dateLabel = new Label(p.getDate().toString());
    Label amountLabel = new Label(String.valueOf(p.getmontant()));
    Label descriptionLabel = new Label(p.getDescription());

    container.add(BorderLayout.WEST, dateLabel);
    container.add(BorderLayout.CENTER, amountLabel);
    container.add(BorderLayout.EAST, descriptionLabel);

    add(container);

    }

}
