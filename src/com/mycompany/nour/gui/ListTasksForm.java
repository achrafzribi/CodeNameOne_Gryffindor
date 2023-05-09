package com.mycompany.nour.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.IconHolder;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Covoiturage;
import com.mycompany.nour.services.ServiceCovoiturage;
import java.io.IOException;
import java.util.ArrayList;

public class ListTasksForm extends Form {
    private TextField searchField;
   
    public ListTasksForm(Form previous) {
        setTitle("List covoiturages");
        setLayout(BoxLayout.y());

        ArrayList<Covoiturage> covs = ServiceCovoiturage.getInstance().getAllTasks();
        for (Covoiturage t : covs) {
            addElement(t);
        }

        searchField = new TextField();
        Button searchButton = new Button("Search");
        searchButton.addActionListener(e -> {
            String query = searchField.getText();
            ListTasksForm results = new ListTasksForm(this);
            results.filter(query);
            results.show();
        });
        Container searchContainer = BoxLayout.encloseY(searchField, searchButton);
        add(searchContainer);
       
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void addElement(Covoiturage covoiturage) {
                Label idc = new Label("idc: " + covoiturage.getIdc());

        Label departLabel = new Label("Depart: " + covoiturage.getDepart());
        Label destinationLabel = new Label("Destination: " + covoiturage.getDestination());
        Label dateLabel = new Label("Date: " + covoiturage.getDatecovoiturage());

        Label prixLabel = new Label("Prix: " + covoiturage.getPrix());
        Label nbrplaceLabel = new Label("Nbr Place: " + covoiturage.getNbrplace());
        Label vehicule = new Label("vehicule: " + covoiturage.getVehicule());
     
     
       
       
        Button btnUpdate = new Button("Update");
        btnUpdate.addPointerPressedListener(e -> {
            //new UpdateTaskForm(this,covoiturage).show();
        });
System.out.println(covoiturage) ;
        Container covoiturageContainer = BoxLayout.encloseY(
                idc ,
                departLabel,
                destinationLabel,
               dateLabel,
                prixLabel,
                nbrplaceLabel,
               
                vehicule,
                 btnUpdate
        );

        add(covoiturageContainer);
    }

    public void filter(String query) {
        removeAll();
        setLayout(BoxLayout.y());

        ArrayList<Covoiturage> Covs = ServiceCovoiturage.getInstance().searchTask(query);
        for (Covoiturage t : Covs) {
            addElement(t);
        }
    }
}
