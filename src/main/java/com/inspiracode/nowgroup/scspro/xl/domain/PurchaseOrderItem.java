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
 * 12/04/2015 - torredie - Initial Version.
 * ====================================================================================
 * </PRE>
 * 
 * 
 * @author torredie
 * 
 */
public class PurchaseOrderItem {
    private long seqItem;
    private String storage1;
    private String storage2;
    private String itemNumber;
    private String partNumber1;
    private String partNumber2;
    private String materialType;
    private String materialClass;
    private String spanishDescription;
    private String englishDescription;
    private String measureUnit;
    private double orderQuantity;
    private double pendingQuantity;
    private double unitCost;
    private double amount;
    private double weightPounds;
    private String mark;
    private String model;
    private String serie;
    private String lot;
    private String observations;

    /**
     * @return the seqItem
     */
    public long getSeqItem() {
	return seqItem;
    }

    /**
     * @param seqItem
     *            the seqItem to set
     */
    public void setSeqItem(long seqItem) {
	this.seqItem = seqItem;
    }

    /**
     * @return the storage1
     */
    public String getStorage1() {
	return storage1;
    }

    /**
     * @param storage1
     *            the storage1 to set
     */
    public void setStorage1(String storage1) {
	this.storage1 = storage1;
    }

    /**
     * @return the storage2
     */
    public String getStorage2() {
	return storage2;
    }

    /**
     * @param storage2
     *            the storage2 to set
     */
    public void setStorage2(String storage2) {
	this.storage2 = storage2;
    }

    /**
     * @return the itemNumber
     */
    public String getItemNumber() {
	return itemNumber;
    }

    /**
     * @param itemNumber
     *            the itemNumber to set
     */
    public void setItemNumber(String itemNumber) {
	this.itemNumber = itemNumber;
    }

    /**
     * @return the partNumber1
     */
    public String getPartNumber1() {
	return partNumber1;
    }

    /**
     * @param partNumber1
     *            the partNumber1 to set
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
     * @param partNumber2
     *            the partNumber2 to set
     */
    public void setPartNumber2(String partNumber2) {
	this.partNumber2 = partNumber2;
    }

    /**
     * @return the spanishDescription
     */
    public String getSpanishDescription() {
	return spanishDescription;
    }

    /**
     * @param spanishDescription
     *            the spanishDescription to set
     */
    public void setSpanishDescription(String spanishDescription) {
	this.spanishDescription = spanishDescription;
    }

    /**
     * @return the englishDescription
     */
    public String getEnglishDescription() {
	return englishDescription;
    }

    /**
     * @param englishDescription
     *            the englishDescription to set
     */
    public void setEnglishDescription(String englishDescription) {
	this.englishDescription = englishDescription;
    }

    /**
     * @return the measureUnit
     */
    public String getMeasureUnit() {
	return measureUnit;
    }

    /**
     * @param measureUnit
     *            the measureUnit to set
     */
    public void setMeasureUnit(String measureUnit) {
	this.measureUnit = measureUnit;
    }

    /**
     * @return the orderQuantity
     */
    public double getOrderQuantity() {
	return orderQuantity;
    }

    /**
     * @param orderQuantity
     *            the orderQuantity to set
     */
    public void setOrderQuantity(double orderQuantity) {
	this.orderQuantity = orderQuantity;
    }

    /**
     * @return the pendingQuantity
     */
    public double getPendingQuantity() {
	return pendingQuantity;
    }

    /**
     * @param pendingQuantity
     *            the pendingQuantity to set
     */
    public void setPendingQuantity(double pendingQuantity) {
	this.pendingQuantity = pendingQuantity;
    }

    /**
     * @return the unitCost
     */
    public double getUnitCost() {
	return unitCost;
    }

    /**
     * @param unitCost
     *            the unitCost to set
     */
    public void setUnitCost(double unitCost) {
	this.unitCost = unitCost;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
	return amount;
    }

    /**
     * @param amount
     *            the amount to set
     */
    public void setAmount(double amount) {
	this.amount = amount;
    }

    /**
     * @return the weightPounds
     */
    public double getWeightPounds() {
	return weightPounds;
    }

    /**
     * @param weightPounds
     *            the weightPounds to set
     */
    public void setWeightPounds(double weightPounds) {
	this.weightPounds = weightPounds;
    }

    /**
     * @return the mark
     */
    public String getMark() {
	return mark;
    }

    /**
     * @param mark
     *            the mark to set
     */
    public void setMark(String mark) {
	this.mark = mark;
    }

    /**
     * @return the model
     */
    public String getModel() {
	return model;
    }

    /**
     * @param model
     *            the model to set
     */
    public void setModel(String model) {
	this.model = model;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
	return serie;
    }

    /**
     * @param serie
     *            the serie to set
     */
    public void setSerie(String serie) {
	this.serie = serie;
    }

    /**
     * @return the lot
     */
    public String getLot() {
	return lot;
    }

    /**
     * @param lot
     *            the lot to set
     */
    public void setLot(String lot) {
	this.lot = lot;
    }

    /**
     * @return the observations
     */
    public String getObservations() {
	return observations;
    }

    /**
     * @param observations
     *            the observations to set
     */
    public void setObservations(String observations) {
	this.observations = observations;
    }

    /**
     * @return the materialType
     */
    public String getMaterialType() {
	return materialType;
    }

    /**
     * @param materialType the materialType to set
     */
    public void setMaterialType(String materialType) {
	this.materialType = materialType;
    }

    /**
     * @return the materialClass
     */
    public String getMaterialClass() {
	return materialClass;
    }

    /**
     * @param materialClass the materialClass to set
     */
    public void setMaterialClass(String materialClass) {
	this.materialClass = materialClass;
    }

}