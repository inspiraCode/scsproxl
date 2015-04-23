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
 * 22/04/2015 - torredie - Initial Version.
 * ====================================================================================
 * </PRE>
 * 
 * 
 * @author torredie
 * 
 */
public class Currency {
    private Long currencyId;
    private String currencyCode;
    private String currencyName;

    /**
     * @return the currencyId
     */
    public Long getCurrencyId() {
	return currencyId;
    }

    /**
     * @param currencyId
     *            the currencyId to set
     */
    public void setCurrencyId(Long currencyId) {
	this.currencyId = currencyId;
    }

    /**
     * @return the currencyCode
     */
    public String getCurrencyCode() {
	return currencyCode;
    }

    /**
     * @param currencyCode
     *            the currencyCode to set
     */
    public void setCurrencyCode(String currencyCode) {
	this.currencyCode = currencyCode;
    }

    /**
     * @return the currencyName
     */
    public String getCurrencyName() {
	return currencyName;
    }

    /**
     * @param currencyName
     *            the currencyName to set
     */
    public void setCurrencyName(String currencyName) {
	this.currencyName = currencyName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "{currencyId=" + currencyId + ";currencyCode=" + currencyCode + ";currencyName=" + currencyName + "}";
    }

}
