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
package com.inspiracode.nowgroup.scspro.xl.destination;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.DataType;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;
import com.inspiracode.nowgroup.scspro.xl.destination.dao.CompanyDao;
import com.inspiracode.nowgroup.scspro.xl.destination.dao.CurrencyDao;
import com.inspiracode.nowgroup.scspro.xl.destination.dao.IncotermDao;
import com.inspiracode.nowgroup.scspro.xl.destination.dao.MaterialClassDao;
import com.inspiracode.nowgroup.scspro.xl.destination.dao.MaterialDao;
import com.inspiracode.nowgroup.scspro.xl.destination.dao.MaterialTypeDao;
import com.inspiracode.nowgroup.scspro.xl.destination.dao.POTypeDao;
import com.inspiracode.nowgroup.scspro.xl.destination.dao.PackageTypeDao;
import com.inspiracode.nowgroup.scspro.xl.destination.dao.TrafficTypeDao;
import com.inspiracode.nowgroup.scspro.xl.destination.dao.TransportModeDao;
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
 * 14/04/2015 - torredie - Initial Version.
 * ====================================================================================
 * </PRE>
 * 
 * 
 * @author torredie
 * 
 */
public class AccessDb {
    private static final Logger log = LoggerFactory.getLogger(AccessDb.class);

    private File file;

    /**
     * 
     */
    public AccessDb(File file) {
	this.file = file;
    }

    public List<LogMessage> validateDataBase() {
	List<LogMessage> result = new ArrayList<LogMessage>();

	Properties prop = new Properties();
	String propFileName = "access/column_map.properties";
	InputStream is = null;
	try {
	    is = getClass().getClassLoader().getResourceAsStream(propFileName);
	    prop.load(is);

	    Database db = DatabaseBuilder.open(file);
	    result.addAll(validateColumns(db, prop, "po.table.name", "po.table.columns"));
	    result.addAll(validateColumns(db, prop, "po.items.table.name", "po.items.table.columns"));
	    result.addAll(validateColumns(db, prop, "cat.company.table", "cat.company.columns"));
	    result.addAll(validateColumns(db, prop, "cat.company.code.table", "cat.company.code.columns"));

	    if (db.getTable("CAT_COMPANIAS_EQUIV") != null)
		result.addAll(validateColumns(db, prop, "cat.equivalent.client.table", "cat.equivalent.client.columns"));
	    else
		addEquivalenceTable(db);
	    
	    db.flush();
	    db.close();
	} catch (Exception e) {
	    log.error("Error al conectarse a la base de datos: " + e.getMessage(), e);
	    result.add(new LogMessage("Validación de Base de Datos", "Error al validar la base de datos" + e.getMessage()));
	} finally {
	    if (is != null)
		try {
		    is.close();
		} catch (Exception e) {
		    log.error("Error al desconectar la base de datos: " + e.getMessage(), e);
		}

	}
	return result;
    }

    private void addEquivalenceTable(Database db) throws IOException {
	Table tblEquiv = new TableBuilder("CAT_COMPANIAS_EQUIV")
		.addColumn(new ColumnBuilder("IDCompania", DataType.LONG))
		.addColumn(new ColumnBuilder("nombre_scs", DataType.TEXT))
		.addColumn(new ColumnBuilder("nombre_origen", DataType.TEXT)).toTable(db);
	log.warn("Added table: " + tblEquiv.getName());
    }

    public List<LogMessage> validatePO(PurchaseOrder PO) {
	List<LogMessage> result = new ArrayList<LogMessage>();

	try {
	    Database db = DatabaseBuilder.open(file);

	    // Currency
	    CurrencyDao currencyDao = new CurrencyDao(db);
	    Long currencyId = currencyDao.currencyId(PO.getCurrency());
	    PO.getCurrency().setCurrencyId(currencyId);

	    // Purchase Order Type
	    POTypeDao poTypeDao = new POTypeDao(db);
	    int poTypeId = poTypeDao.poTypeId(PO.getOcType());
	    PO.getOcType().setPoTypeId(poTypeId);

	    // Material
	    MaterialTypeDao materialTypeDao = new MaterialTypeDao(db);
	    int materialTypeId = materialTypeDao.materialTypeId(PO.getMaterialType());
	    PO.getMaterialType().setMaterialTypeId(materialTypeId);

	    MaterialClassDao materialClassDao = new MaterialClassDao(db);
	    int materialClassId = materialClassDao.materialClassId(PO.getMaterialClass());
	    PO.getMaterialClass().setMaterialClassId(materialClassId);

	    // Incoterms
	    IncotermDao iDao = new IncotermDao(db);
	    int incotermId = iDao.incotermId(PO.getIncoterm());
	    PO.getIncoterm().setIncotermsId(incotermId);

	    // PackType
	    PackageTypeDao ptDao = new PackageTypeDao(db);
	    int packageTypeId = ptDao.packageTypeId(PO.getPackageType());
	    PO.getPackageType().setPackageTypeId(packageTypeId);

	    // Traffic type
	    TrafficTypeDao ttDao = new TrafficTypeDao(db);
	    int ttId = ttDao.trafficTypeId(PO.getTrafficType());
	    PO.getPackageType().setPackageTypeId(ttId);

	    // Transport mode
	    TransportModeDao tmDao = new TransportModeDao(db);
	    int tmId = tmDao.trafficModeId(PO.getTransportMode());
	    PO.getTransportMode().setTransportModeId(tmId);

	    // payment conditions

	    // Companies
	    CompanyDao cdao = new CompanyDao(db);
	    if(PO.getPurchaser()==null)
		result.add(new LogMessage("Validación de Orden de Compra", "La orden de compra " + PO.getPoNumber() + " no tiene un comprador."));
	    
	    if (PO.getPurchaser() != null && !cdao.companyMatchesInternal(PO.getPurchaser())) {
		result.add(new LogMessage("Validación de Orden de Compra", "La orden de compra " + PO.getPoNumber() + " no tiene un comprador válido en la base de datos."));
		return result;
	    }
	    
	    Integer purchaserId = PO.getPurchaser().getCompanyId();
	    
	    if (PO.getSeller() != null && !cdao.companyExists(PO.getSeller(), purchaserId)) {
		result.add(new LogMessage("Validación de Orden de Compra", "Existen proveedores no registrados para el cliente: " + PO.getPurchaser().getCompanyName()));
	    }

	    if (PO.getSender() != null && !cdao.companyExists(PO.getSender(), purchaserId)) {
		result.add(new LogMessage("Validación de Orden de Compra", "Existen proveedores no registrados para el cliente: " + PO.getPurchaser().getCompanyName()));
	    }

	    if (PO.getFreightForwarder() != null && !cdao.companyExists(PO.getFreightForwarder(), purchaserId)) {
		result.add(new LogMessage("Validación de Orden de Compra", "Existen proveedores no registrados para el cliente: " + PO.getPurchaser().getCompanyName()));
	    }
	    
	    for(PurchaseOrderItem poItem : PO.getItems()) {
		poItem.getMaterial().setPurchaser(PO.getPurchaser());
		poItem.getMaterial().setSeller(PO.getSeller());
		result.addAll(validatePOItem(poItem, db));
	    }
	    
	    db.flush();
	    db.close();
	} catch (Exception e) {
	    log.error("Error al conectarse a la base de datos: " + e.getMessage(), e);
	    result.add(new LogMessage("Validación de Base de Datos", "Error al validar la base de datos" + e.getMessage()));
	}
	return result;
    }

    private List<LogMessage> validatePOItem(PurchaseOrderItem POItem, Database db){
	List<LogMessage> result = new ArrayList<LogMessage>();
	// Validate
	MaterialDao mdao = new MaterialDao(db);
	Integer matId;
	try {
	    matId = mdao.getIdMaterial(POItem.getMaterial());
	    if(matId == 0) {
		result.add(new LogMessage("Validación de Partida de Orden de Compra", "Número de parte no es válido en la partida: " + POItem.getSeqItem()));
		return result;
	    }
	    
	    POItem.getMaterial().setMaterialId(matId);
	} catch (IOException e) {
	    result.add(new LogMessage("Validación de Partida de Orden de Compra", "Error: " + e.getMessage()));
	}
	
	return result;
    }
    
    @SuppressWarnings("unchecked")
    private List<LogMessage> validateColumns(Database db, Properties prop, String tablePropertyName, String columnsCollectionPropertyName) throws IOException {
	List<LogMessage> result = new ArrayList<LogMessage>();

	String poTable = prop.getProperty(tablePropertyName).trim();
	log.debug("Validating po Table: [" + poTable + "]");

	Table table = db.getTable(poTable);
	List<Column> poColumns = (List<Column>) table.getColumns();
	result.addAll(validateColumns(prop, poColumns, columnsCollectionPropertyName));

	return result;
    }

    private List<LogMessage> validateColumns(Properties prop, List<Column> poColumns, String columnsCollectionPropertyName) {
	List<LogMessage> result = new ArrayList<LogMessage>();

	String[] configuredColumns = prop.getProperty(columnsCollectionPropertyName).split(",");
	for (String configuredColumn : configuredColumns) {
	    boolean found = false;
	    for (Column column : poColumns) {
		if (configuredColumn.trim().equals(column.getName())) {
		    found = true;
		    break;
		}
	    }

	    if (!found) {
		result.add(new LogMessage("Validación de Base de Datos", "La columna " + configuredColumn.trim() + " no se encuentra en la base de datos"));
	    }
	}

	return result;
    }
}
