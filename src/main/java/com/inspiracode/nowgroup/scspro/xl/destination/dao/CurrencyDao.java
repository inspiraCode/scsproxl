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
import com.inspiracode.nowgroup.scspro.xl.domain.Currency;

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
public class CurrencyDao {
    private static final Logger log = LoggerFactory.getLogger(CurrencyDao.class);

    private final Database db;

    public static final Long INDETERMINADO = 28L;

    public CurrencyDao(Database db) {
	this.db = db;
    }

    public Long currencyId(Currency currency) throws IOException {
	if (currency == null) {
	    log.warn("EMTPY CURRENCY!");
	    return INDETERMINADO;
	}

	log.debug("Locate " + currency);
	Table table = db.getTable("Monedas");
	Cursor cursor = CursorBuilder.createCursor(table);
	boolean found = false;

	if (currency.getCurrencyCode() == null)
	    cursor.findFirstRow(Collections.singletonMap("ClaveMoneda", currency.getCurrencyCode()));
	else
	    cursor.findFirstRow(Collections.singletonMap("NombreMoneda", currency.getCurrencyName()));

	if (!found)
	    return INDETERMINADO;

	return (Long) cursor.getCurrentRowValue(table.getColumn("IDMoneda"));
    }

}
