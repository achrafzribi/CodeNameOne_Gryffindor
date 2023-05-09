/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nour.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.l10n.DateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.nour.entities.Chauffeur;
import com.mycompany.nour.services.ChauffeurService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author medzr
 */
public class Listchauffeur extends Form {
    private TextField searchField;
    
    public Listchauffeur(Form previous) {
        setTitle("List covoiturages");
        setLayout(BoxLayout.y());

        ArrayList<Chauffeur> covs = ChauffeurService.getInstance().getAllTasks();
        for (Chauffeur t : covs) {
            addElement(t);
        }

        searchField = new TextField();
        Button searchButton = new Button("Search");
        searchButton.addActionListener(e -> {
            String query = searchField.getText();
            Listchauffeur results = new Listchauffeur(this);
            results.filter(query);
            results.show();
        });
        Container searchContainer = BoxLayout.encloseY(searchField, searchButton);
        add(searchContainer);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void addElement(Chauffeur covoiturage) {
        Label nomLabel = new Label("nom: " + covoiturage.getNomc());
        Label prenomabel = new Label("prenom: " + covoiturage.getPrenomc());
        Label numLabel = new Label("num: " + covoiturage.getNumc());
        Label emailLabel = new Label("email: " + covoiturage.getEmailc());
        Label adresseLabel = new Label("adresse: " + covoiturage.getAdressec());
      
      
        
          
        Button btnUpdate = new Button("Update");
        btnUpdate.addPointerPressedListener(e -> {
            new updatechauffeur(this,covoiturage).show();
        });

        Button pdf=new Button("obtenir fiche pdf");
            pdf.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_STANDBY, pdf.getUnselectedStyle()));
           pdf.addActionListener(m -> {
    try {
        Document document = new Document();
        String outputPath = "file:///C://Users//medzr//OneDrive//Documents//pdfmobile//Chauffeur" + covoiturage.getId() + ".pdf";
        PdfWriter.getInstance(document, FileSystemStorage.getInstance().openOutputStream(outputPath));

        
        //        // Ajouter le logo  C:\xampp\htdocs
//       String logoPath = "file:///C:/xampp/htdocs/CodenameOne/logo.jpg"; // Remplace le chemin par le chemin réel de ton logo
//        Image logo = Image.getInstance(new URL(logoPath));
//        logo.scaleAbsolute(70,70);
//        document.add(logo);
        
    

 DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
        String currentDate = dateFormat.format(new Date());

        
//        //        // Ajouter le logo
//       String logoPath = "file:///C:/xampp/htdocs/logo.png"; // Remplace le chemin par le chemin réel de ton logo
//        Image logo = Image.getInstance(new URL(logoPath));
//        logo.scaleAbsolute(70,70);
//        document.add(logo);
        
//        
//       String imagePath = "file:///C:/xampp/htdocs/logo.png";
//    EncodedImage encodedImage = EncodedImage.createFromImage(Image.createImage(imagePath), false);
//    Image logo = encodedImage.scaledEncoded(70, 70);

        document.open();
        document.add(new Paragraph("nom : " + covoiturage.getNomc()));
        document.add(new Paragraph("prenom :" + covoiturage.getPrenomc()));
        document.add(new Paragraph("num :" + covoiturage.getNumc()));
        document.add(new Paragraph("adresse :" + covoiturage.getAdressec()));
        document.add(new Paragraph("email :" + covoiturage.getEmailc()));
        
        
        document.close();
        Dialog.show("Enregistré", "", "", "OK");

        Log.p("PDF file successfully created!");
    } catch (Exception e) {
        Log.e(e);
    }
});
        
      
        Container covoiturageContainer = BoxLayout.encloseY(
                nomLabel,
                prenomabel,
                numLabel,
                emailLabel,
                adresseLabel,
                btnUpdate,
                pdf
               
        );

        add(covoiturageContainer);
    }

    public void filter(String query) {
        removeAll();
        setLayout(BoxLayout.y());

        ArrayList<Chauffeur> covs = ChauffeurService.getInstance().searchTask(query);
        for (Chauffeur t : covs) {
            addElement(t);
        }
    }
}
