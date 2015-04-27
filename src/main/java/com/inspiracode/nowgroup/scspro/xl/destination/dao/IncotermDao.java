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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.healthmarketscience.jackcess.Cursor;
import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;
import com.inspiracode.nowgroup.scspro.xl.domain.Incoterms;

/**
 * USAGE HERE
 *
 * <B>Revision History:</B>
 * 
 * <PRE>
 * ====================================================================================
 * Date-------- By---------------- Description
 * ------------ --------------------------- -------------------------------------------
 * 23/04/2015 - torredie - Initial Version.
 * ====================================================================================
 * </PRE>
 * 
 * 
 * @author torredie
 *
 */
public class IncotermDao {
    private static final Logger log = LoggerFactory.getLogger(IncotermDao.class);
    private final Database db;
    public static final int INDETERMINADO = 12;

    public IncotermDao(Database db) {
	this.db = db;
    }

    public int incotermId(Incoterms incoterm) throws IOException {
	if(incoterm == null){
	    log.warn("EMPTY INCOTERM!");
	    return INDETERMINADO;
	}
	
	log.debug("Locate " + incoterm);
	Table table = db.getTable("Incoterms");
	Cursor cursor = CursorBuilder.createCursor(table);
	boolean found = false;

	if (incoterm.getIncotermsCode() == null)
	    found = cursor.findFirstRow(Collections.singletonMap("Clave", incoterm.getIncotermsCode()));
	else
	    found = cursor.findFirstRow(Collections.singletonMap("Incoterm", incoterm.getIncotermsName()));

	if (!found)
	    return INDETERMINADO;

	return (Integer) cursor.getCurrentRowValue(table.getColumn("IDIncoterm"));
	
    }
}
