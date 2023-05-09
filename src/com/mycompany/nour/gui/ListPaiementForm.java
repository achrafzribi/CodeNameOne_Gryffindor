/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nour.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Paiement;
import com.mycompany.nour.services.ServicePaiement;
import java.util.ArrayList;

/**
 *
 * @author INFOTEC
 */
public class ListPaiementForm extends Form{
    
     public ListPaiementForm(Form previous) {
        setTitle("List tasks");
        setLayout(BoxLayout.y());

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        ArrayList<Paiement> Paiements = ServicePaiement.getInstance().getAllPaiements();
        for (Paiement p : Paiements) {
            addElement(p);
        }

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
