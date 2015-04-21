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
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;
import com.inspiracode.nowgroup.scspro.xl.domain.LogMessage;
import com.inspiracode.nowgroup.scspro.xl.source.ExcelFile;

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
    private static final Logger log = LoggerFactory.getLogger(ExcelFile.class);

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
