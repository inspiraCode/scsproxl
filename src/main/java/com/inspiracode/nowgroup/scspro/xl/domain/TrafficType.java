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
public class TrafficType {
    private int trafficTypeId;
    private int trafficTypeCode;
    private String trafficTypeName;

    /**
     * @return the trafficTypeCode
     */
    public int getTrafficTypeCode() {
	return trafficTypeCode;
    }

    /**
     * @param trafficTypeCode
     *            the trafficTypeCode to set
     */
    public void setTrafficTypeCode(int trafficTypeCode) {
	this.trafficTypeCode = trafficTypeCode;
    }

    /**
     * @return the trafficTypeId
     */
    public int getTrafficTypeId() {
	return trafficTypeId;
    }

    /**
     * @param trafficTypeId
     *            the trafficTypeId to set
     */
    public void setTrafficTypeId(int trafficTypeId) {
	this.trafficTypeId = trafficTypeId;
    }

    /**
     * @return the trafficTypeName
     */
    public String getTrafficTypeName() {
	return trafficTypeName;
    }

    /**
     * @param trafficTypeName
     *            the trafficTypeName to set
     */
    public void setTrafficTypeName(String trafficTypeName) {
	this.trafficTypeName = trafficTypeName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "{trafficTypeId=" + trafficTypeId + ";trafficTypeCode=" + trafficTypeCode + ";trafficTypeName=" + trafficTypeName + "}";
    }

}
