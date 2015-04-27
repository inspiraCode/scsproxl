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
package com.inspiracode.nowgroup.scspro.xl.domain;

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
public class Material {
    private long materialId;
    private Company purchaser;
    private Company seller;
    private String descripcionEsp;
    private String descriptionIng;
    private String observaciones;
    private String partNumber1;
    private String partNumber2;

    /**
     * @return the descripcionEsp
     */
    public String getDescripcionEsp() {
	return descripcionEsp;
    }

    /**
     * @param descripcionEsp
     *            the descripcionEsp to set
     */
    public void setDescripcionEsp(String descripcionEsp) {
	this.descripcionEsp = descripcionEsp;
    }

    /**
     * @return the descriptionIng
     */
    public String getDescriptionIng() {
	return descriptionIng;
    }

    /**
     * @param descriptionIng
     *            the descriptionIng to set
     */
    public void setDescriptionIng(String descriptionIng) {
	this.descriptionIng = descriptionIng;
    }

    /**
     * @return the materialId
     */
    public long getMaterialId() {
	return materialId;
    }

    /**
     * @param materialId
     *            the materialId to set
     */
    public void setMaterialId(long materialId) {
	this.materialId = materialId;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
	return observaciones;
    }

    /**
     * @param observaciones
     *            the observaciones to set
     */
    public void setObservaciones(String observaciones) {
	this.observaciones = observaciones;
    }

    /**
     * @return the purchaser
     */
    public Company getPurchaser() {
	return purchaser;
    }

    /**
     * @return the seller
     */
    public Company getSeller() {
	return seller;
    }

    /**
     * @param purchaser
     *            the purchaser to set
     */
    public void setPurchaser(Company purchaser) {
	this.purchaser = purchaser;
    }

    /**
     * @param seller
     *            the seller to set
     */
    public void setSeller(Company seller) {
	this.seller = seller;
    }
    
    /**
     * @return the partNumber1
     */
    public String getPartNumber1() {
	return partNumber1;
    }
    
    /**
     * @param partNumber1 the partNumber1 to set
     */
    public void setPartNumber1(String partNumber1) {
	this.partNumber1 = partNumber1;
    }
    
    /**
     * @return the partNumber2
     */
    public String getPartNumber2() {
	return partNumber2;
    }
    
    /**
     * @param partNumber2 the partNumber2 to set
     */
    public void setPartNumber2(String partNumber2) {
	this.partNumber2 = partNumber2;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "{materialId=" + materialId + ";purchaser=" + purchaser + ";seller=" + seller + ";descripcionEsp=" + descripcionEsp + ";descripcionIng="
		+ descriptionIng + ";partNumber1=" + partNumber1 + ";partNumber2=" + partNumber2 + "}";
    }

}
