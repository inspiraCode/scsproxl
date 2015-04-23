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
public class MaterialType {
    private int materialTypeId;
    private String materialTypeName;

    /**
     * @return the materialTypeId
     */
    public int getMaterialTypeId() {
	return materialTypeId;
    }

    /**
     * @param materialTypeId
     *            the materialTypeId to set
     */
    public void setMaterialTypeId(int materialTypeId) {
	this.materialTypeId = materialTypeId;
    }

    /**
     * @return the materialTypeName
     */
    public String getMaterialTypeName() {
	return materialTypeName;
    }

    /**
     * @param materialTypeName
     *            the materialTypeName to set
     */
    public void setMaterialTypeName(String materialTypeName) {
	this.materialTypeName = materialTypeName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "{materialTypeId=" + materialTypeId + ";materialTypeName=" + materialTypeName + "}";
    }

}
