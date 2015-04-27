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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.healthmarketscience.jackcess.Cursor;
import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;
import com.inspiracode.nowgroup.scspro.xl.domain.Company;

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

    public boolean companyExists(Company company, Integer purchaserId) throws IOException {
	if (company == null || company.getCompanyName() == null) {
	    log.warn("EMTPY COMPANY!");
	    return false;
	}

	log.debug("Locate " + company.getCompanyName());
	Table table = db.getTable("CAT_COMPANIAS_EQUIV");
	
	Cursor cursor = CursorBuilder.createCursor(table);
	Map<String, Object> filter = new HashMap<String, Object>();
	filter.put("nombre_origen", company.getCompanyName().trim());
	filter.put("IDCompania", purchaserId);
	
	boolean found = cursor.findFirstRow(filter);
	if(!found) {
	    found = companyMatchesInternal(company);
	    if(found)
		table.addRow(purchaserId, company.getCompanyName(), company.getCompanyName());
	    else
		table.addRow(purchaserId, "", company.getCompanyName());
	} else {
	    company.setCompanyName(cursor.getCurrentRowValue(table.getColumn("nombre_scs")).toString());
	    company.setCompanyId(companyIdFromName(cursor.getCurrentRowValue(table.getColumn("nombre_scs")).toString()));
	    found = company.getCompanyId()==null || company.getCompanyId()==0;
	}

	return found;
    }
    
    public boolean companyMatchesInternal(Company company) throws IOException {
	if(company == null) {
	    log.warn("searching for empty company!");
	    return false;
	}
	
	Table table = db.getTable("Companias");
	
	log.debug("searching for company: " + company);
	Cursor cursor = CursorBuilder.createCursor(table);
	Map<String, Object> rowPattern = new HashMap<String, Object>();
	rowPattern.put("Nombre", company.getCompanyName().trim());
	boolean found = cursor.findFirstRow(rowPattern);
	if(found) {
	    company.setCompanyId((Integer) cursor.getCurrentRowValue(table.getColumn("IDCompania")));
	} else {
	    log.warn("No record found in table.");
	}
	
	return found;
    }
    
    private Integer companyIdFromName(String companyName) throws IOException{
	Table table = db.getTable("Companias");
	
	Cursor cursor = CursorBuilder.createCursor(table);
	boolean found = cursor.findFirstRow(Collections.singletonMap("nombre", companyName));
	if(found)
	    return (Integer) cursor.getCurrentRowValue(table.getColumn("IDCompania"));
	else
	    return 0;
    }
}
