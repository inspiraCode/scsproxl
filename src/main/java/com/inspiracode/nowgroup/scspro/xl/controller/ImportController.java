package com.inspiracode.nowgroup.scspro.xl.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportController
{
    private static final Logger log = LoggerFactory.getLogger(ImportController.class);

    @FXML private TextField poField;
    @FXML private TextField dbField;
    @FXML private Label messageLabel;

    /*
    public void sayHello() {

        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        StringBuilder builder = new StringBuilder();

        if (!StringUtils.isEmpty(firstName)) {
            builder.append(firstName);
        }

        if (!StringUtils.isEmpty(lastName)) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(lastName);
        }

        if (builder.length() > 0) {
            String name = builder.toString();
            log.debug("Saying hello to " + name);
            messageLabel.setText("Hello " + name);
        } else {
            log.debug("Neither first name nor last name was set, saying hello to anonymous person");
            messageLabel.setText("Hello mysterious person");
        }
    }
    */
    
    public void handleExplorePO(){
	 messageLabel.setText("Explore for PO");
    }
    
    public void handleExploreDB(){
	messageLabel.setText("Explore for DB");
    }

}
