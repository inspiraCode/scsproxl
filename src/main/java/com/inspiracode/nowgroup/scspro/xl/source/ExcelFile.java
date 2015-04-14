/**
 * THIS IS A COMMERCIAL PROGRAM PROVIDED FOR INSPIRACODE AND IT'S ASSOCIATES
 * BUILT BY EXTERNAL SOFTWARE PROVIDERS.
 * THE SOFTWARE COMPRISING THIS SYSTEM IS THE PROPERTY OF INSPIRACODE OR ITS
 * LICENSORS.
 *
 * ALL COPYRIGHT, PATENT, TRADE SECRET, AND OTHER INTELLECTUAL PROPERTY RIGHTS
 * IN THE SOFTWARE COMPRISING THIS SYSTEM ARE, AND SHALL REMAIN, THE VALUABLE
 * PROPERTY OF INSPIRACODE OR ITS LICENSORS.
 *
 * USE, DISCLOSURE, OR REPRODUCTION OF THIS SOFTWARE IS STRICTLY PROHIBITED,
 * EXCEPT UNDER WRITTEN LICENSE FROM INSPIRACODE OR ITS LICENSORS.
 *
 * &copy; COPYRIGHT 2015 INSPIRACODE. ALL RIGHTS RESERVED.
 */
package com.inspiracode.nowgroup.scspro.xl.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inspiracode.nowgroup.scspro.xl.domain.Company;
import com.inspiracode.nowgroup.scspro.xl.domain.LogMessage;
import com.inspiracode.nowgroup.scspro.xl.domain.PurchaseOrder;
import com.inspiracode.nowgroup.scspro.xl.domain.PurchaseOrderItem;

/**
 * USAGE HERE
 * 
 * <B>Revision History:</B>
 * 
 * <PRE>
 * ====================================================================================
 * Date-------- By---------------- Description
 * ------------ --------------------------- -------------------------------------------
 * 11/04/2015 - torredie - Initial Version.
 * ====================================================================================
 * </PRE>
 * 
 * 
 * @author torredie
 * 
 */
public class ExcelFile {
    private static final Logger log = LoggerFactory.getLogger(ExcelFile.class);

    private static final int HEADING_ROW = 0;
    private static final int HEADING_COUNT = 45;
    private File file;
    private List<PurchaseOrder> pos;

    public ExcelFile(File file) {
	this.setFile(file);
    }

    public List<LogMessage> validateFieldNames() {
	List<LogMessage> result = new ArrayList<LogMessage>();
	FileInputStream fis = null;
	Workbook wb = null;
	try {
	    fis = new FileInputStream(file);
	    // Get the workbook instance for XLS file

	    if (".xls".equals(file.getName().substring(file.getName().length() - 4))) {
		wb = new HSSFWorkbook(fis);
	    } else if ("xlsx".equals(file.getName().substring(file.getName().length() - 4))) {
		wb = new XSSFWorkbook(fis);
	    } else {
		throw new IllegalArgumentException("Received file does not have a standard excel extension.");
	    }

	    // Get each sheet from the workbook
	    for (int i = 0; i < wb.getNumberOfSheets(); i++) {
		result.addAll(validateFieldNames(wb.getSheetAt(i)));
	    }

	} catch (Exception e) {
	    log.error("Error al validar campos en excel: [" + e.getMessage() + "]", e);
	    result.add(new LogMessage("Validación de encabezados", "Error al validar campos en excel: [" + e.getMessage() + "]"));
	} finally {
	    try {
		fis.close();
	    } catch (Exception e) {
		log.error(e.getMessage(), e);
	    }
	    try {
		wb.close();
	    } catch (Exception e) {
		log.error(e.getMessage(), e);
	    }
	}

	return result;
    }

    private List<LogMessage> validateFieldNames(Sheet sheet) {
	List<LogMessage> result = new ArrayList<LogMessage>();
	String nombreHoja = sheet.getSheetName();
	String errDescription = "";
	Row headingRow = sheet.getRow(HEADING_ROW);

	Properties prop = new Properties();
	String propFileName = "excel/column_names.properties";

	InputStream is = null;
	try {
	    is = getClass().getClassLoader().getResourceAsStream(propFileName);
	    if (is != null) {
		prop.load(is);
		for (int i = 1; i <= HEADING_COUNT; i++) {
		    String expected = prop.getProperty(Integer.toString(i));
		    log.debug("Validando [" + Integer.toString(i) + "] validada como [" + headingRow.getCell(i - 1) + "]");
		    String current = headingRow.getCell(i - 1) == null || headingRow.getCell(i - 1).getStringCellValue() == null ? "" : headingRow.getCell(
			    i - 1).getStringCellValue();
		    if (!expected.equals(current)) {
			int charValue = 64 + i;
			boolean aValue = false;
			if (charValue > 90) {
			    charValue -= 25;
			    aValue = true;
			}

			String columnName = aValue ? "A" : "";
			columnName += Character.toString((char) charValue);

			errDescription = "La columna [" + nombreHoja + "]!" + columnName + " tiene el título [" + current + "], " + " se esperaba: ["
				+ expected + "]";
			log.debug(errDescription);
			result.add(new LogMessage("Validación de encabezados", errDescription));
		    } else {
			log.debug("columna [" + expected + "] validada");
		    }
		}
	    } else {
		errDescription = "Imposible abrir configuración de campos de excel (column_names.properties)";
		log.debug(errDescription);
		result.add(new LogMessage("Validación de encabezados", errDescription));
	    }
	} catch (Exception e) {
	    errDescription = "Error al validar campos en la hoja [" + nombreHoja + "]: [" + e.getMessage() + "]";
	    log.error(errDescription, e);
	    result.add(new LogMessage("Validación de encabezados", errDescription));
	} finally {
	    if (is != null) {
		try {
		    is.close();
		} catch (Exception e) {
		    log.error(e.getMessage(), e);
		}
	    }
	}
	return result;
    }

    public List<LogMessage> readPurchaseOrders() {

	List<LogMessage> result = new ArrayList<LogMessage>();
	FileInputStream fis = null;
	Workbook wb = null;
	try {
	    fis = new FileInputStream(file);
	    // Get the workbook instance for XLS file

	    if (".xls".equals(file.getName().substring(file.getName().length() - 4))) {
		wb = new HSSFWorkbook(fis);
	    } else if ("xlsx".equals(file.getName().substring(file.getName().length() - 4))) {
		wb = new XSSFWorkbook(fis);
	    } else {
		throw new IllegalArgumentException("Received file does not have a standard excel extension.");
	    }

	    // Get each sheet from the workbook
	    for (int i = 0; i < wb.getNumberOfSheets(); i++) {
		result.addAll(readPurchaseOrders(wb.getSheetAt(i)));
	    }

	} catch (Exception e) {
	    log.error("Error al validar campos en excel: [" + e.getMessage() + "]", e);
	    result.add(new LogMessage("Validación de ordenes de compra", "Error al validar las Ordenes de compra: [" + e.getMessage() + "]"));
	} finally {
	    try {
		fis.close();
	    } catch (Exception e) {
		log.error(e.getMessage(), e);
	    }
	    try {
		wb.close();
	    } catch (Exception e) {
		log.error(e.getMessage(), e);
	    }
	}

	return result;
    }

    private List<LogMessage> readPurchaseOrders(Sheet sheet) {
	List<LogMessage> result = new ArrayList<LogMessage>();

	pos = new ArrayList<PurchaseOrder>();
	int rowIndex = HEADING_ROW + 1;
	Row currentRow = sheet.getRow(rowIndex);
	while (null != currentRow) {
	    PurchaseOrder po = getPoFromRow(currentRow);
	    if (pos.contains(po)) {
		// validate equal
		PurchaseOrder existent = pos.get(pos.indexOf(po));
		if (po.matchComplete(existent))
		    existent.getItems().add(po.getItems().get(0));
		else {
		    String msg = String.format(
			    "La partida %d no coincide con la primer partida encontrada %d, se suponen ambas elementos de la misma orden de compra %s", po
				    .getItems().get(0).getSeqItem(), existent.getItems().get(0).getSeqItem(), existent.getPoNumber());
		    result.add(new LogMessage("Validación de Ordenes de Compra", msg));
		}
	    }
	    rowIndex++;
	    currentRow = sheet.getRow(rowIndex);
	}

	return result;
    }

    private PurchaseOrder getPoFromRow(Row row) {
	PurchaseOrder po = new PurchaseOrder();

	Company purchaser = new Company();
	purchaser.setCompanyId(getCellAsString(row.getCell(1)));
	purchaser.setCompanyName(row.getCell(2).getStringCellValue());
	po.setPurchaser(purchaser);

	Company seller = new Company();
	seller.setCompanyId(getCellAsString(row.getCell(3)));
	seller.setCompanyName(row.getCell(4).getStringCellValue());
	po.setSeller(seller);

	Company sender = new Company();
	sender.setCompanyId(row.getCell(5).getStringCellValue());
	sender.setCompanyName(row.getCell(6).getStringCellValue());
	po.setSender(sender);

	// row.getCell(8).getCellType()

	po.setPoNumber(getCellAsString(row.getCell(7)));
	log.debug("PO Number: " + po.getPoNumber());
	po.setPoDate(row.getCell(8).getDateCellValue());
	po.setEtd(row.getCell(9).getDateCellValue());
	po.setEta(row.getCell(10).getDateCellValue());

	po.setSoNumber(row.getCell(11).getStringCellValue());
	po.setAcceptanceDate(row.getCell(12).getDateCellValue());

	po.setIncoterm(row.getCell(13).getStringCellValue());
	po.setCurrency(row.getCell(14).getStringCellValue());
	po.setPaymentCondition(row.getCell(15).getStringCellValue());
	po.setSource(row.getCell(16).getStringCellValue());
	po.setOcType(row.getCell(17).getStringCellValue());

	if (!"".equals(getCellAsString(row.getCell(20))))
	    po.setCustomsId(Integer.parseInt(getCellAsString(row.getCell(20))));

	po.setTrafficType(row.getCell(21).getStringCellValue());
	po.setTransportClass(row.getCell(22).getStringCellValue());
	po.setTransportMode(row.getCell(24).getStringCellValue());
	po.setPackageQty((int) Math.ceil(row.getCell(25).getNumericCellValue()));
	po.setPackageType(getCellAsString(row.getCell(26)));

	PurchaseOrderItem item = new PurchaseOrderItem();
	item.setSeqItem((int) Math.ceil(row.getCell(0).getNumericCellValue()));
	item.setMaterialType(row.getCell(18).getStringCellValue());
	item.setMaterialClass(row.getCell(19).getStringCellValue());

	item.setStorage1(getCellAsString(row.getCell(27)));
	item.setStorage2(getCellAsString(row.getCell(28)));
	item.setItemNumber(getCellAsString(row.getCell(29)));
	item.setPartNumber1(getCellAsString(row.getCell(30)));
	item.setPartNumber2(getCellAsString(row.getCell(31)));
	item.setSpanishDescription(row.getCell(32).getStringCellValue());
	item.setEnglishDescription(row.getCell(33).getStringCellValue());
	item.setMeasureUnit(getCellAsString(row.getCell(34)));
	item.setOrderQuantity(row.getCell(35).getNumericCellValue());
	item.setPendingQuantity(row.getCell(36).getNumericCellValue());
	item.setUnitCost(row.getCell(37).getNumericCellValue());
	item.setAmount(row.getCell(38).getNumericCellValue());
	item.setWeightPounds(row.getCell(39).getNumericCellValue());
	item.setMark(getCellAsString(row.getCell(40)));
	item.setModel(getCellAsString(row.getCell(41)));
	item.setSerie(getCellAsString(row.getCell(42)));
	item.setLot(getCellAsString(row.getCell(43)));
	item.setObservations(getCellAsString(row.getCell(44)));

	po.setItems(new ArrayList<PurchaseOrderItem>());
	po.getItems().add(item);

	return po;
    }

    private String getCellAsString(Cell cell) {
	if (cell == null)
	    return "";

	switch (cell.getCellType()) {
	case Cell.CELL_TYPE_NUMERIC:
	    int intValue = (int) Math.ceil(cell.getNumericCellValue());
	    return String.valueOf(intValue);
	case Cell.CELL_TYPE_STRING:
	    return cell.getStringCellValue();
	default:
	    return "";
	}
    }

    public boolean validate() {
	return true;
    }

    public boolean localSave() {
	return true;
    }

    /**
     * @return the pos
     */
    public List<PurchaseOrder> getPos() {
	return pos;
    }

    /**
     * @param pos
     *            the pos to set
     */
    public void setPos(List<PurchaseOrder> pos) {
	this.pos = pos;
    }

    /**
     * @return the fileName
     */
    public File getFile() {
	return file;
    }

    /**
     * @param fileName
     *            the fileName to set
     */
    public void setFile(File file) {
	this.file = file;
    }

}
