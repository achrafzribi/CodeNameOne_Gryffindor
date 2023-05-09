/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nour.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Paiement;
import com.mycompany.nour.services.ServicePaiement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import com.stripe.param.ChargeCreateParams;
import org.apache.commons.text.StringEscapeUtils;
/**
 *
 * @author INFOTEC
 */
public class AjoutPaiementForm extends Form {

    static {
        Stripe.apiKey = "sk_test_51MgXUSHDZ477HmaTRBbQ43QrqSPHsC5iUQE5tvFSKL3jVufDWRQ02wnDJ1P8As7ScIybYYpQ3MpGoNZFPI5OSbNI007jqR4B6q";
    }

    // public AjoutPaiementForm(Resources res){
    //     Tool 
    // }
    public AjoutPaiementForm(Form previous) {

        setTitle("Add a new Paiement");
        setLayout(BoxLayout.y());

        TextField Amount = new TextField("", "Amount");
        //  TextField Date = new TextField("","Date");
        TextField Description = new TextField("", "Description");

        String[] paymentOptions = {"Cash", "Bank Transfer", "Credit Card"};
        ComboBox<String> paymentComboBox = new ComboBox<>(paymentOptions);
// Add validation constraints for the fields
        Amount.setConstraint(TextArea.NUMERIC);
// dateField.setConstraint(TextArea.DATE);
        Description.setConstraint(TextArea.ANY);

        //   TextField Paymentmethod = new TextField("","Payemnt Method");
        Date currentDate = new Date();

// Create a date formatter for the desired format
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

// Format the current date using the formatter
        String formattedDate = dateFormatter.format(currentDate);

        // Add fields for credit card information
        TextField cardNumber = new TextField("", "Card Number");
        TextField cvc = new TextField("", "CVC");
        TextField expirationDate = new TextField("", "Expiration Date (MM/YYYY)");
        cardNumber.setVisible(false);
        cvc.setVisible(false);
        expirationDate.setVisible(false);
        Button btnValider = new Button("Add Paiement");

        paymentComboBox.addActionListener(evt -> {
            String selectedPaymentOption = paymentComboBox.getSelectedItem();
            if (selectedPaymentOption.equals("Credit Card")) {
                cardNumber.setVisible(true);
                cvc.setVisible(true);
                expirationDate.setVisible(true);
            } else {
                cardNumber.setVisible(false);
                cvc.setVisible(false);
                expirationDate.setVisible(false);
            }
        });

        //  Date paiementDate = new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("PaiementDate").toString());
        // int m_id = Integer.parseInt(obj.get("m_id").toString());
        //  String ip_address = obj.get("ip_address").toString();
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Amount.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                }
                //  if ((Date.getText().length()==0))
                //     Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                if ((Description.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } // if ((Paymentmethod.getText().length()==0))
                //   Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else {
                    try {
                        int choice;
                        String ip = "";
                        double amount = Double.parseDouble(Amount.getText().toString());
                        String description = Description.getText().toString();
                        String selectedPaymentOption = paymentComboBox.getSelectedItem();
                        if (selectedPaymentOption == "Cash") {
                            choice = 1;
                        } else if (selectedPaymentOption == "Credit Card") {
                            choice = 2;
                            String cardNumberText = cardNumber.getText().trim();

                            String cvcText = cvc.getText().trim();
                            String expirationDateText = expirationDate.getText().trim();
                            if (cardNumberText.length() == 0 || cvcText.length() == 0 || expirationDateText.length() == 0) {
                                Dialog.show("Alert", "Please fill all the required fields", new Command("OK"));
                                return;
                            }
                            Map<String, Object> card = new HashMap<>();
                            String encodedCardNumber = encode(cardNumberText);
                            card.put("number", encodedCardNumber);
                            card.put("exp_month", 12);
                            card.put("exp_year", 2024);
                            card.put("cvc", "123");

                            Map<String, Object> params = new HashMap<>();
                            params.put("card", card);

                            Token token = Token.create(params);
                            double amountValue = Double.parseDouble(Amount.getText()) * 100; // multiply by 100 to convert to cents
                            long amountInCents = (long) amountValue;
                            ChargeCreateParams chargeParams = ChargeCreateParams.builder()
                                    .setAmount((long) amountInCents)
                                    //.setAmount((long)2000)
                                    .setCurrency("usd")
                                    .setDescription(description)
                                    .setSource(token.getId())
                                    .build();

                            Charge charge = Charge.create(chargeParams);

                        } else {
                            choice = 3;
                        }

                        Paiement p = new Paiement(amount, description, formattedDate, ip, choice);
                        System.out.println("data == " + p);
                        if (ServicePaiement.getInstance().AjoutPaiement(p)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (StripeException ex) {
                        ex.printStackTrace();
                    }

                }

            }

        });

        addAll(Amount, Description, paymentComboBox, cardNumber, cvc, expirationDate, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
    private static String encode(String input) {
  return StringEscapeUtils.escapeHtml4(input);
}

}
