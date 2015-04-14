package com.inspiracode.nowgroup.scspro.xl.controller;

import java.io.File;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inspiracode.nowgroup.scspro.xl.domain.LogMessage;
import com.inspiracode.nowgroup.scspro.xl.source.ExcelFile;

public class ImportController {
    private static final Logger log = LoggerFactory.getLogger(ImportController.class);

    @FXML
    private TextField poField;
    @FXML
    private TextField dbField;
    @FXML
    private Label messageLabel;
    @FXML
    private WebView logView;
    @FXML
    private ProgressBar progress;
    @FXML
    private Button cmdTransfer;
    

    private String logHeader = "<html><head><style>.bad{color:red;} .good{color:green;}</style></head><body>";
    private String logFooter = "</body></html>";
    private String logContent;
    private Stage myStage;
    private ExcelFile excel;

    public void handleExplorePO() {
	log.debug("Setting purchase order file chooser");
	FileChooser fileChooser = new FileChooser();
	fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Excel", "*.xls;*.xlsx;*.xlsm"),
		new FileChooser.ExtensionFilter("CSV", "*.csv"));
	fileChooser.setTitle("Orden de Compra");
	File file = fileChooser.showOpenDialog(myStage);
	poField.setText(file.getName());

	excel = new ExcelFile(file);
	log.debug(file.getAbsolutePath());
	messageLabel.setText("Validando Encabezados");
	List<LogMessage> validationErrors = excel.validateFieldNames();
	progress.setVisible(true);
	progress.setProgress(0.2);

	logContent = "";
	if (validationErrors.isEmpty()) {
	    logContent = "<b class='good'>Validaci&oacute;n de encabezados</b> La validación de encabezados sucedi&oacute; exitosamente.<br/>";
	    progress.setProgress(0.5);
	    logView.getEngine().loadContent(logHeader + logContent + logFooter);
	} else {
	    progress.setProgress(1);
	    progress.setVisible(false);
	    messageLabel.setText("La validación de encabezados ha fallado.");
	    for (LogMessage message : validationErrors) {
		logContent = "<b class='bad'>" + message.getSource() + "</b> " + message.getMessage() + "<br/>" + logContent;
		logView.getEngine().loadContent(logHeader + logContent + logFooter);
	    }
	    return;
	}
	
	messageLabel.setText("Validando Ordenes de Compra");
	validationErrors.addAll(excel.readPurchaseOrders());
	progress.setVisible(true);
	progress.setProgress(0.7);
	
	if (validationErrors.isEmpty()) {
	    logContent = "<b class='good'>Validaci&oacute;n de &oacute;rdenes de compra</b> La validación de &oacute;rdenes de compra sucedi&oacute; exitosamente.<br/>" + logContent;
	    progress.setProgress(1);
	    logView.getEngine().loadContent(logHeader + logContent + logFooter);
	} else {
	    progress.setProgress(1);
	    progress.setVisible(false);
	    messageLabel.setText("La validación de &oacute;rdenes de compra ha fallado.");
	    for (LogMessage message : validationErrors) {
		logContent = "<b class='bad'>" + message.getSource() + "</b> " + message.getMessage() + "<br/>" + logContent;
		logView.getEngine().loadContent(logHeader + logContent + logFooter);
	    }
	    return;
	}
	
	progress.setProgress(1);
	progress.setVisible(false);
	//cmdTransfer.setDisable(false);

    }

    public void handleExploreDB() {
	messageLabel.setText("Explore for DB");
    }

    public void setStage(Stage stage) {
	myStage = stage;
    }

}
