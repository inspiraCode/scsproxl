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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.healthmarketscience.jackcess.Cursor;
import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;
import com.inspiracode.nowgroup.scspro.xl.domain.Company;
import com.inspiracode.nowgroup.scspro.xl.domain.Material;

/**
 * USAGE HERE
 * 
 * <B>Revision History:</B>
 * 
 * <PRE>
 * ====================================================================================
 * Date-------- By---------------- Description
 * ------------ --------------------------- -------------------------------------------
 * 24/04/2015 - torredie - Initial Version.
 * ====================================================================================
 * </PRE>
 * 
 * 
 * @author torredie
 * 
 */
public class MaterialDao {
    private static final Logger log = LoggerFactory.getLogger(MaterialDao.class);
    private final Database db;

    public MaterialDao(Database db) {
	this.db = db;
    }

    public Integer getIdMaterial(Material material) throws IOException {
	if (material == null || material.getPurchaser().getCompanyId() == null || material.getSeller().getCompanyId() == null
		|| material.getSeller().getCompanyId() == 0) {
	    log.warn("Could not locate one of these: " + material);
	    return 0;
	}
	    

	log.debug("Locate " + material);
	List<Integer> primaryMaterials = materialIdFromPart(material.getPartNumber1());
	List<Integer> secondaryMaterials = materialIdFromPart(material.getPartNumber2());

	Integer selectedMaterialId = 0;
	for (Integer tempId : primaryMaterials) {
	    if (isMyMaterial(tempId, material.getPurchaser(), material.getSeller())) {
		selectedMaterialId = tempId;
		break;
	    }
	}

	if (selectedMaterialId == 0) {
	    for (Integer tempId : secondaryMaterials) {
		if (isMyMaterial(tempId, material.getPurchaser(), material.getSeller())) {
		    selectedMaterialId = tempId;
		    break;
		}
	    }

	    if (selectedMaterialId != 0) {
		// Add primary to same material Id.
		AddPartNumber(selectedMaterialId, material.getPartNumber2());
	    }
	} else {
	    Integer tempSecondary = 0;
	    for (Integer tempId : secondaryMaterials) {
		if (isMyMaterial(tempId, material.getPurchaser(), material.getSeller())) {
		    tempSecondary = tempId;
		    break;
		}
	    }

	    if (tempSecondary == 0) {
		// Add secondary to same material Id.
		AddPartNumber(selectedMaterialId, material.getPartNumber2());
	    }
	}

	if (selectedMaterialId == 0) {
	    // Add Material and both part numbers
	    selectedMaterialId = AddNewMaterial(material);
	    AddPartNumber(selectedMaterialId, material.getPartNumber1());
	    AddPartNumber(selectedMaterialId, material.getPartNumber2());
	}

	return selectedMaterialId;
    }

    private List<Integer> materialIdFromPart(String partNumber) throws IOException {
	List<Integer> result = new ArrayList<Integer>();
	Table table = db.getTable("Materiales-Numeros de parte");
	Cursor cursor = CursorBuilder.createCursor(table);

	boolean found = false;
	Map<String, Object> rowPattern = new HashMap<String, Object>();
	rowPattern.put("NumParte", partNumber);
	found = cursor.findFirstRow(rowPattern);

	if (found) {
	    result.add((Integer) cursor.getCurrentRowValue(table.getColumn("IDMaterial")));
	    while (found = cursor.findNextRow(rowPattern)) {
		result.add((Integer) cursor.getCurrentRowValue(table.getColumn("IDMaterial")));
	    }
	}

	return result;
    }

    private boolean isMyMaterial(Integer idMaterial, Company purchaser, Company seller) throws IOException {
	boolean mine = false;

	Table table = db.getTable("Materiales");
	Cursor cursor = CursorBuilder.createCursor(table);
	Map<String, Object> filter = new HashMap<String, Object>();
	filter.put("IDMaterial", idMaterial);

	boolean found = false;
	found = cursor.findFirstRow(filter);

	if (found) {
	    // Secure null compare
	    int myPurchaser, mySeller, theirPurchaser, theirSeller;
	    myPurchaser = purchaser == null ? 0 : purchaser.getCompanyId();
	    mySeller = seller == null ? 0 : seller.getCompanyId();

	    theirPurchaser = (Integer) cursor.getCurrentRowValue(table.getColumn("IDcomprador"));
	    theirSeller = (Integer) cursor.getCurrentRowValue(table.getColumn("IDvendedor"));

	    mine = myPurchaser == theirPurchaser && mySeller == theirSeller;
	}

	return mine;
    }

    private Integer AddNewMaterial(Material material) throws IOException {
	Table table = db.getTable("Materiales");
	Map<String, Object> rowMap = new HashMap<String, Object>();

	rowMap.put("IDComprador", material.getPurchaser().getCompanyId());
	rowMap.put("IDVendedor", material.getPurchaser().getCompanyId());
	rowMap.put("DescripcionIdiomaBase", material.getDescripcionEsp());
	rowMap.put("Descripcion1", material.getDescriptionIng());

	Object[] addedRow = table.addRow(table.asRow(rowMap));

	return (Integer) addedRow[0];
    }

    private void AddPartNumber(Integer materialId, String partNumber) throws IOException {
	Table table = db.getTable("Materiales-Numeros de parte");
	Map<String, Object> rowMap = new HashMap<String, Object>();

	rowMap.put("IDMaterial", materialId);
	rowMap.put("IDMaterialNumParteTipo", 1);
	rowMap.put("NumParte", partNumber);

	table.addRow(table.asRow(rowMap));
    }
}
