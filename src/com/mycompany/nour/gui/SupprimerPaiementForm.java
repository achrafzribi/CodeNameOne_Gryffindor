/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nour.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.mycompany.nour.services.ServicePaiement;

/**
 *
 * @author INFOTEC
 */
public class SupprimerPaiementForm extends Form {
    public SupprimerPaiementForm(Form previous) {
        setTitle("Delete a Paiement");
        TextField Id = new TextField("", "Id");

        Button btnValider = new Button("Delete Paiement");

        btnValider.addActionListener((evt) -> {
            try {
                int id = Integer.parseInt(Id.getText());

                if (ServicePaiement.getInstance().supprimerPaiement(id)) {
                    Dialog.show("Success", "Paiement deleted", new Command("OK"));
                } else {
                    Dialog.show("Error", "Failed to delete paiement", new Command("OK"));
                }
            } catch (NumberFormatException e) {
                Dialog.show("ERROR", "Id must be a number", new Command("OK"));
            }
        });

        addAll(Id, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
