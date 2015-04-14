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

import java.util.Date;
import java.util.List;

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
public class PurchaseOrder {

    private String poNumber;
    
    private Date poDate;
    private Date etd;
    private Date eta;
    private Date acceptanceDate;
    
    private String soNumber;
    private String incoterm;
    private String currency;
    private String paymentCondition;
    private String source;
    private String ocType;
    
    private int customsId;
    private String trafficType;
    private String transportClass;
    private String transportMode;
    private int packageQty;
    private String packageType;
    private String storage1;
    private String storage2;
    
    private List<PurchaseOrderItem> items;
    private Company purchaser;
    private Company seller;
    private Company sender;

    /**
     * @return the poNumber
     */
    public String getPoNumber() {
	return poNumber;
    }

    /**
     * @param poNumber
     *            the poNumber to set
     */
    public void setPoNumber(String poNumber) {
	this.poNumber = poNumber;
    }

    /**
     * @return the poDate
     */
    public Date getPoDate() {
	return poDate;
    }

    /**
     * @param poDate
     *            the poDate to set
     */
    public void setPoDate(Date poDate) {
	this.poDate = poDate;
    }

    /**
     * @return the soNumber
     */
    public String getSoNumber() {
	return soNumber;
    }

    /**
     * @param soNumber
     *            the soNumber to set
     */
    public void setSoNumber(String soNumber) {
	this.soNumber = soNumber;
    }

    /**
     * @return the acceptanceDate
     */
    public Date getAcceptanceDate() {
	return acceptanceDate;
    }

    /**
     * @param acceptanceDate
     *            the acceptanceDate to set
     */
    public void setAcceptanceDate(Date acceptanceDate) {
	this.acceptanceDate = acceptanceDate;
    }

    /**
     * @return the incoterm
     */
    public String getIncoterm() {
	return incoterm;
    }

    /**
     * @param incoterm
     *            the incoterm to set
     */
    public void setIncoterm(String incoterm) {
	this.incoterm = incoterm;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
	return currency;
    }

    /**
     * @param currency
     *            the currency to set
     */
    public void setCurrency(String currency) {
	this.currency = currency;
    }

    /**
     * @return the paymentCondition
     */
    public String getPaymentCondition() {
	return paymentCondition;
    }

    /**
     * @param paymentCondition
     *            the paymentCondition to set
     */
    public void setPaymentCondition(String paymentCondition) {
	this.paymentCondition = paymentCondition;
    }

    /**
     * @return the source
     */
    public String getSource() {
	return source;
    }

    /**
     * @param source
     *            the source to set
     */
    public void setSource(String source) {
	this.source = source;
    }

    /**
     * @return the ocType
     */
    public String getOcType() {
	return ocType;
    }

    /**
     * @param ocType
     *            the ocType to set
     */
    public void setOcType(String ocType) {
	this.ocType = ocType;
    }

    /**
     * @return the customsId
     */
    public int getCustomsId() {
	return customsId;
    }

    /**
     * @param customsId
     *            the customsId to set
     */
    public void setCustomsId(int customsId) {
	this.customsId = customsId;
    }

    /**
     * @return the trafficType
     */
    public String getTrafficType() {
	return trafficType;
    }

    /**
     * @param trafficType
     *            the trafficType to set
     */
    public void setTrafficType(String trafficType) {
	this.trafficType = trafficType;
    }

    /**
     * @return the transportClass
     */
    public String getTransportClass() {
	return transportClass;
    }

    /**
     * @param transportClass
     *            the transportClass to set
     */
    public void setTransportClass(String transportClass) {
	this.transportClass = transportClass;
    }

    /**
     * @return the transportMode
     */
    public String getTransportMode() {
	return transportMode;
    }

    /**
     * @param transportMode
     *            the transportMode to set
     */
    public void setTransportMode(String transportMode) {
	this.transportMode = transportMode;
    }

    /**
     * @return the packageQty
     */
    public int getPackageQty() {
	return packageQty;
    }

    /**
     * @param packageQty
     *            the packageQty to set
     */
    public void setPackageQty(int packageQty) {
	this.packageQty = packageQty;
    }

    /**
     * @return the packageType
     */
    public String getPackageType() {
	return packageType;
    }

    /**
     * @param packageType
     *            the packageType to set
     */
    public void setPackageType(String packageType) {
	this.packageType = packageType;
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
     * @return the items
     */
    public List<PurchaseOrderItem> getItems() {
	return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<PurchaseOrderItem> items) {
	this.items = items;
    }

    /**
     * @return the etd
     */
    public Date getEtd() {
	return etd;
    }

    /**
     * @param etd the etd to set
     */
    public void setEtd(Date etd) {
	this.etd = etd;
    }

    /**
     * @return the eta
     */
    public Date getEta() {
	return eta;
    }

    /**
     * @param eta the eta to set
     */
    public void setEta(Date eta) {
	this.eta = eta;
    }
    
    
    /**
     * @return the purchaser
     */
    public Company getPurchaser() {
	return purchaser;
    }

    /**
     * @param purchaser the purchaser to set
     */
    public void setPurchaser(Company purchaser) {
	this.purchaser = purchaser;
    }

    /**
     * @return the seller
     */
    public Company getSeller() {
	return seller;
    }

    /**
     * @param seller the seller to set
     */
    public void setSeller(Company seller) {
	this.seller = seller;
    }

    /**
     * @return the sender
     */
    public Company getSender() {
	return sender;
    }

    /**
     * @param sender the sender to set
     */
    public void setSender(Company sender) {
	this.sender = sender;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	PurchaseOrder other = (PurchaseOrder) obj;
	if (poNumber == null) {
	    if (other.poNumber != null)
		return false;
	} else if (!poNumber.equals(other.poNumber))
	    return false;
	if (poNumber == null) {
	    if (other.poNumber != null)
		return false;
	} else if (!poNumber.equals(other.poNumber))
	    return false;
	return true;
    }
    
    public boolean matchComplete(PurchaseOrder other) {
	if(!this.acceptanceDate.equals(other.acceptanceDate))
	    return false;
	
	if(!this.currency.equals(other.currency))
	    return false;
	
	if(this.customsId != other.customsId)
	    return false;
	
	if(!this.eta.equals(other.eta))
	    return false;
	
	if(!this.etd.equals(other.etd))
	    return false;
	
	if(!this.incoterm.equals(other.incoterm))
	    return false;
	
	if(!this.ocType.equals(other.ocType))
	    return false;
	
	if(this.packageQty!=other.packageQty)
	    return false;
	
	if(!this.packageType.equals(other.packageType))
	    return false;
	
	if(!this.paymentCondition.equals(other.paymentCondition))
	    return false;
	
	if(!this.poDate.equals(other.poDate))
	    return false;
	
	if(!this.poNumber.equals(other.poNumber))
	    return false;
	
	if(!this.purchaser.matchComplete(other.purchaser))
	    return false;
	
	if(!this.seller.matchComplete(other.seller))
	    return false;
	
	if(!this.sender.matchComplete(other.sender))
	    return false;
	
	if(!this.soNumber.equals(other.soNumber))
	    return false;
	
	if(!this.source.equals(other.source))
	    return false;
	
	if(!this.storage1.equals(other.storage1))
	    return false;
	
	if(!this.storage2.equals(other.storage2))
	    return false;
	
	if(!this.trafficType.equals(other.trafficType))
	    return false;
	
	if(!this.transportClass.equals(other.transportClass))
	    return false;
	
	if(!this.transportMode.equals(other.transportMode))
	    return false;

	return true;
    }
}
