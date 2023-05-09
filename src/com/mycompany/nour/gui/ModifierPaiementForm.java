/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nour.gui;

import com.codename1.l10n.SimpleDateFormat;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Paiement;
import com.mycompany.nour.services.ServicePaiement;
import java.util.Date;

/**
 *
 * @author INFOTEC
 */
public class ModifierPaiementForm extends Form {

    public ModifierPaiementForm(Form previous) {
        setTitle("Modify a new Paiement");
        TextField Id = new TextField("", "Id");

        TextField Description = new TextField("", "Description");

        Description.setConstraint(TextArea.ANY);

        Date currentDate = new Date();

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        String formattedDate = dateFormatter.format(currentDate);

        CheckBox cbStatus = new CheckBox("Status");
        Button btnValider = new Button("Modify Paiement");

        btnValider.addActionListener((evt) -> {
            if (Description.getText().length() == 0) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    String description = Description.getText().toString();
                    int id = Integer.parseInt(Id.getText());

                    Paiement p = new Paiement(id, formattedDate, description);
                    if (ServicePaiement.getInstance().modifierPaiement(p)) {
                        Dialog.show("Success", "Paiement updated", new Command("OK"));

                    } else {
                        Dialog.show("Error", "Failed to update paiement", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Amount must be a number", new Command("OK"));
                }
            }
        });

        addAll(Id, Description, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
