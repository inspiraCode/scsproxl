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
package com.inspiracode.nowgroup.scspro.xl.destination.dao;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.inspiracode.nowgroup.scspro.xl.domain.Company;
import com.inspiracode.nowgroup.scspro.xl.domain.LogMessage;

/**
 * USAGE HERE
 * 
 * <B>Revision History:</B>
 * 
 * <PRE>
 * ====================================================================================
 * Date-------- By---------------- Description
 * ------------ --------------------------- -------------------------------------------
 * 21/04/2015 - torredie - Initial Version.
 * ====================================================================================
 * </PRE>
 * 
 * 
 * @author torredie
 * 
 */
public class CompanyDao {
    private static final Logger log = LoggerFactory.getLogger(CompanyDao.class);

    private final Database db;

    public static final int TIPO_COMPRADOR = 1;
    public static final int TIPO_VENDEDOR = 2;
    public static final int TIPO_REMITENTE = 3;
    public static final int TIPO_TRANSPORTISTA = 7;

    public static final int PAIS_INDETERMINADO = 485;

    public CompanyDao(Database db) {
	this.db = db;
    }

    public boolean companyExists(Company company) throws IOException {
	if (company == null || company.getCompanyName() == null) {
	    log.warn("EMTPY COMPANY!");
	    return false;
	}

	log.debug("Locate " + company.getCompanyName());
	Table table = db.getTable("Companias");
	for (Row row : table) {
	    String companyName = String.valueOf(row.get("Nombre"));
	    if (company.getCompanyName().trim().equalsIgnoreCase(companyName.trim()))
		return true;
	}

	return false;
    }

    public LogMessage addCompany(Company company, int tipoEmpresa) throws IOException {
	if (company == null)
	    return new LogMessage("Agregar Empresa", "Empresa vacía.");

	try {
	    Table table = db.getTable("Companias");
	    Object[] addedRow = table.addRow(Column.AUTO_NUMBER, PAIS_INDETERMINADO, company.getCompanyCode(), company.getCompanyName());
	    
	    Integer id = (Integer) addedRow[0];
	    log.info("New company added with ID[" + id + "]");
	    company.setCompanyId(id.longValue());

	    Table tTipoEmpresa = db.getTable("ENLACE-Companias y tipos");
	    tTipoEmpresa.addRow(Column.AUTO_NUMBER, id, tipoEmpresa);
	} catch (Exception e) {
	    log.error(e.getMessage(), e);
	    return new LogMessage("Agregar empresa", "Error: " + e.getMessage());
	}

	return null;
    }
}
