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
public class PackageType {
    private int packageTypeId;
    private String packageTypeCode;
    private String packageTypeName;

    /**
     * @return the packageTypeCode
     */
    public String getPackageTypeCode() {
	return packageTypeCode;
    }

    /**
     * @param packageTypeCode
     *            the packageTypeCode to set
     */
    public void setPackageTypeCode(String packageTypeCode) {
	this.packageTypeCode = packageTypeCode;
    }

    /**
     * @return the packageTypeId
     */
    public int getPackageTypeId() {
	return packageTypeId;
    }

    /**
     * @param packageTypeId
     *            the packageTypeId to set
     */
    public void setPackageTypeId(int packageTypeId) {
	this.packageTypeId = packageTypeId;
    }

    /**
     * @return the packageTypeName
     */
    public String getPackageTypeName() {
	return packageTypeName;
    }

    /**
     * @param packageTypeName
     *            the packageTypeName to set
     */
    public void setPackageTypeName(String packageTypeName) {
	this.packageTypeName = packageTypeName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "{packageTypeId=" + packageTypeId + ";packageTypeCode=" + packageTypeCode + ";packageTypeName=" + packageTypeName + "}";
    }

}
