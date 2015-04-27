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
 * 13/04/2015 - torredie - Initial Version.
 * ====================================================================================
 * </PRE>
 * 
 * 
 * @author torredie
 * 
 */
public class Company {
    private Integer companyId;
    private String companyName;
    private String companyCode;

    /**
     * @return the companyId
     */
    public Integer getCompanyId() {
	return companyId;
    }

    /**
     * @param companyId
     *            the companyId to set
     */
    public void setCompanyId(Integer companyId) {
	this.companyId = companyId;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
	return companyName;
    }

    /**
     * @param companyName
     *            the companyName to set
     */
    public void setCompanyName(String companyName) {
	this.companyName = companyName;
    }
    
    public boolean matchComplete(Company other) {
	return this.toString().equals(other.toString());
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "{companyId=" + companyId + ", companyCode=" + companyCode + ", companyName=" + companyName + "}";
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
	Company other = (Company) obj;
	if (companyId == null) {
	    if (other.companyId != null)
		return false;
	} else if (!companyId.equals(other.companyId))
	    return false;
	if (companyId == null) {
	    if (other.companyId != null)
		return false;
	} else if (!companyId.equals(other.companyId))
	    return false;
	return true;
    }

    /**
     * @return the companyCode
     */
    public String getCompanyCode() {
	return companyCode;
    }

    /**
     * @param companyCode the companyCode to set
     */
    public void setCompanyCode(String companyCode) {
	this.companyCode = companyCode;
    }

}
