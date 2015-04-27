package com.inspiracode.nowgroup.scspro.xl.controller;

import java.io.File;
import java.util.ArrayList;
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

import com.inspiracode.nowgroup.scspro.xl.destination.AccessDb;
import com.inspiracode.nowgroup.scspro.xl.domain.LogMessage;
import com.inspiracode.nowgroup.scspro.xl.domain.PurchaseOrder;
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
    private String logContent = "";
    private int validatedFiles = 0;
    private Stage myStage;
    private ExcelFile excel;
    private AccessDb access;

    public void handleExplorePO() {
	log.info("Setting purchase order file chooser");
	FileChooser fileChooser = new FileChooser();
	fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/documents"));
	fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Excel", "*.xls;*.xlsx;*.xlsm"),
		new FileChooser.ExtensionFilter("CSV", "*.csv"));
	fileChooser.setTitle("Orden de Compra");
	File file = fileChooser.showOpenDialog(myStage);
	poField.setText(file.getName());

	excel = new ExcelFile(file);
	log.info(file.getAbsolutePath());
	messageLabel.setText("Validando Encabezados");
	List<LogMessage> validationErrors = excel.validateFieldNames();
	progress.setVisible(true);
	progress.setProgress(0.2);

	if (validationErrors.isEmpty()) {
	    logContent = "<b class='good'>Validaci&oacute;n de encabezados</b> La validación de encabezados sucedi&oacute; exitosamente.<br/>" + logContent;
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
	    logContent = "<b class='good'>Validaci&oacute;n de &oacute;rdenes de compra</b> La validación de &oacute;rdenes de compra sucedi&oacute; exitosamente.<br/>"
		    + logContent;
	    progress.setProgress(1);
	    logView.getEngine().loadContent(logHeader + logContent + logFooter);
	} else {
	    progress.setProgress(1);
	    progress.setVisible(false);
	    messageLabel.setText("La validación de órdenes de compra ha fallado.");
	    for (LogMessage message : validationErrors) {
		logContent = "<b class='bad'>" + message.getSource() + "</b> " + message.getMessage() + "<br/>" + logContent;
		logView.getEngine().loadContent(logHeader + logContent + logFooter);
	    }
	    return;
	}

	progress.setProgress(1);
	progress.setVisible(false);

	validatedFiles++;
	if (validatedFiles >= 2) {
	    cmdTransfer.setDisable(false);
	    validatedFiles = 0;
	}

    }

    public void handleExploreDB() {
	// Conectar la BD
	log.info("Setting database  file chooser");
	FileChooser fileChooser = new FileChooser();
	fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Access", "*.mdb;*.accdb"));
	fileChooser.setTitle("Base de datos");
	File file = fileChooser.showOpenDialog(myStage);
	dbField.setText(file.getName());

	access = new AccessDb(file);
	List<LogMessage> validationErrors = access.validateDataBase();

	// Validar tablas a utilizar
	progress.setVisible(true);
	progress.setProgress(0.2);

	if (validationErrors.isEmpty()) {
	    log.info("Validación de base de datos exitosa.");
	    logContent = "<b class='good'>Validaci&oacute;n de base de datos</b> La validación de base de datos sucedi&oacute; exitosamente.<br/>" + logContent;
	    progress.setProgress(0.5);
	    logView.getEngine().loadContent(logHeader + logContent + logFooter);
	} else {
	    messageLabel.setText("La validación de base de datos ha fallado.");
	    for (LogMessage message : validationErrors) {
		log.error(message.getSource() + ":" + message.getMessage());
		logContent = "<b class='bad'>" + message.getSource() + "</b> " + message.getMessage() + "<br/>" + logContent;
		logView.getEngine().loadContent(logHeader + logContent + logFooter);
	    }
	}

	progress.setProgress(1);
	progress.setVisible(false);

	validatedFiles++;
	if (validatedFiles >= 2) {
	    cmdTransfer.setDisable(false);
	    validatedFiles = 0;
	}
    }

    public void handleTransfer() {
	log.info("Transfering data from excel to access");
	messageLabel.setText("Transfiriendo datos...");
	progress.setVisible(true);
	progress.setProgress(0.0d);

	List<LogMessage> validationErrors = new ArrayList<LogMessage>();

	int poCount = excel.getPos().size();
	log.debug("Trasfer " + poCount + " Purchase Orders.");
	int poIndex = 0;
	for (PurchaseOrder po : excel.getPos()) {
	    log.debug("Validating PO: " + po);
	    validationErrors.addAll(access.validatePO(po));

	    if (!validationErrors.isEmpty()) {
		for (LogMessage message : validationErrors) {
		    log.error(message.getSource() + ":" + message.getMessage());
		    logContent = "<b class='bad'>" + message.getSource() + "</b> " + message.getMessage() + "<br/>" + logContent;
		    logView.getEngine().loadContent(logHeader + logContent + logFooter);
		}
	    } else {
		log.info("Validación de OC:" + po.getPoNumber() + " exitosa");
		// TODO: Upload PO
	    }

	    progress.setProgress(poIndex / poCount);
	    poIndex++;
	}
	if (validationErrors.isEmpty()) {
	    log.info("Datos transferidos exitosamente.");
	    messageLabel.setText("Datos Transferidos");
	    logContent = "<H1>DATOS TRANSFERIDOS</H1><br/><hr/>" + logContent;
	    logView.getEngine().loadContent(logHeader + logContent + logFooter);
	}

	excel = null;
	access = null;
	cmdTransfer.setDisable(true);
	progress.setVisible(false);

	logContent = "";
	poField.setText("");
	dbField.setText("");
    }

    public void setStage(Stage stage) {
	myStage = stage;
    }

}
