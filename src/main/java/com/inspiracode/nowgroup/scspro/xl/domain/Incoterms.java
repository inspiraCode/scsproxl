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
 * 23/04/2015 - torredie - Initial Version.
 * ====================================================================================
 * </PRE>
 * 
 * 
 * @author torredie
 * 
 */
public class Incoterms {
    private int incotermsId;
    private String incotermsCode;
    private String incotermsName;

    /**
     * @return the incotermsId
     */
    public int getIncotermsId() {
	return incotermsId;
    }

    /**
     * @param incotermsId
     *            the incotermsId to set
     */
    public void setIncotermsId(int incotermsId) {
	this.incotermsId = incotermsId;
    }

    /**
     * @return the incotermsCode
     */
    public String getIncotermsCode() {
	return incotermsCode;
    }

    /**
     * @param incotermsCode
     *            the incotermsCode to set
     */
    public void setIncotermsCode(String incotermsCode) {
	this.incotermsCode = incotermsCode;
    }

    /**
     * @return the incotermsName
     */
    public String getIncotermsName() {
	return incotermsName;
    }

    /**
     * @param incotermsName
     *            the incotermsName to set
     */
    public void setIncotermsName(String incotermsName) {
	this.incotermsName = incotermsName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "{incotermsId=" + incotermsId + ";incotermsCode=" + incotermsCode + ";incotermsName=" + incotermsName + "}";
    }

}
