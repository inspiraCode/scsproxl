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
import com.inspiracode.nowgroup.scspro.xl.domain.MaterialClass;

/**
 * USAGE HERE
 *
 * <B>Revision History:</B>
 * 
 * <PRE>
 * ====================================================================================
 * Date-------- By---------------- Description
 * ------------ --------------------------- -------------------------------------------
 * 22/04/2015 - torredie - Initial Version.
 * ====================================================================================
 * </PRE>
 * 
 * 
 * @author torredie
 *
 */
public class MaterialClassDao {
    private static final Logger log = LoggerFactory.getLogger(MaterialClassDao.class);

    private final Database db;

    public static final int INDETERMINADO = 1;

    public MaterialClassDao(Database db) {
	this.db = db;
    }

    public int materialClassId(MaterialClass materialClass) throws IOException {
	if (materialClass == null) {
	    log.warn("EMTPY MATERIAL CLASS!");
	    return INDETERMINADO;
	}

	log.debug("Locate " + materialClass);
	Table table = db.getTable("Materiales-Clases de");
	Cursor cursor = CursorBuilder.createCursor(table);
	boolean found = false;

	cursor.findFirstRow(Collections.singletonMap("ClaseMaterial", materialClass.getMaterialClassName()));

	if (!found)
	    return INDETERMINADO;

	return (Integer) cursor.getCurrentRowValue(table.getColumn("IDClaseMaterial"));
    }

}
