package com.mycompany.nour.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.Passager;
import com.mycompany.nour.services.ServicePassager;

public class AddPassagerfForm extends Form {
   private Form previous;

    public AddPassagerfForm(Form previous) {
        super("Newsfeed", BoxLayout.y());
        this.previous = previous;
        setTitle("Add new passanger");

        TextField tfNomp = new TextField("", "name");
        TextField tfPrenom = new TextField("", "first name");
        TextField tfNum = new TextField("", "Num");
        TextField tfAdresse = new TextField("", "Adresse");
        TextField tfEmail = new TextField("", "Email");
        Button addPassagerButton = new Button("Add passager");

        addPassagerButton.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfNomp.getText().isEmpty() || tfPrenom.getText().isEmpty() ||
                    tfNum.getText().isEmpty() || tfAdresse.getText().isEmpty() || tfEmail.getText().isEmpty()) {
                    Dialog.show("Error", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Passager passager = new Passager(tfNomp.getText(), tfPrenom.getText(),
                            tfNum.getText(), tfAdresse.getText(), tfEmail.getText());
                        if (ServicePassager.getInstance().addPassager(passager)) {
                            Dialog.show("Success", "Passager added", new Command("OK"));
                        } else {
                            Dialog.show("Error", "Server error", new Command("OK"));
                        }
                    } catch (Exception e) {
                        Dialog.show("Error", "An error occurred: " + e.getMessage(), new Command("OK"));
                    }
                }
            }
        });

        addAll(tfNomp, tfPrenom, tfNum, tfAdresse, tfEmail, addPassagerButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new HomeForm().show());
    }}
      
