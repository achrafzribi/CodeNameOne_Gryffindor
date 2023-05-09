package com.mycompany.nour.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.Passager;
import com.mycompany.nour.gui.ListPassagerForm;
import com.mycompany.nour.services.ServicePassager;

public class ModifierPassagerForm extends Form {
    private Form previous;
    private Passager annoncef;

    private TextField tfnom;
    private TextField tfprenom;
    private TextField tfnum;
    private TextField tfadresse;
    private TextField tfemail;
    private Button btnModifier;
    private Button btnAnnuler;
    private final Passager passager;

    public ModifierPassagerForm(Form previous, Passager passager) {
        super("Modifier passager", BoxLayout.y());
        this.previous = previous;
        this.passager = passager;

        tfnom = new TextField(passager.getNomp(), "Nom du passager");
        tfprenom = new TextField(passager.getPrenomp(), "Prenom du passager");
        tfnum = new TextField(String.valueOf(passager.getNump()), "Num du passager");
        tfadresse = new TextField(String.valueOf(passager.getAdressep()), "Adresse du passager");
        tfemail = new TextField(String.valueOf(passager.getEmailp()), "Email du passager");

        btnModifier = new Button("Modifier");
        btnModifier.addActionListener(l -> {
            passager.setNomp(tfnom.getText());
            passager.setPrenomp(tfprenom.getText());
            passager.setNump(tfnum.getText());
            passager.setAdressep(tfadresse.getText());
            passager.setEmailp(tfemail.getText());

            if (ServicePassager.getInstance().modifierPassager(passager)) {
                Dialog.show("Passagé modifié", "Votre passager a été modifié avec succès", "OK", null);
                new ListPassagerForm(previous).show();
            }
        });

        btnAnnuler = new Button("Back to list");
        btnAnnuler.addActionListener(e -> new ListPassagerForm(previous).show());
        addAll(tfnom, tfprenom, tfnum, tfadresse, tfemail, btnModifier, btnAnnuler);
     
    }

    public void show() {
        super.show();
    }
}
