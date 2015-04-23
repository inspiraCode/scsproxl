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
public class MaterialClass {
    private int materialClassId;
    private String materialClassName;
    
    /**
     * @return the materialClassId
     */
    public int getMaterialClassId() {
	return materialClassId;
    }
    
    /**
     * @param materialClassId the materialClassId to set
     */
    public void setMaterialClassId(int materialClassId) {
	this.materialClassId = materialClassId;
    }
    
    /**
     * @return the materialClassName
     */
    public String getMaterialClassName() {
	return materialClassName;
    }
    
    /**
     * @param materialClassName the materialClassName to set
     */
    public void setMaterialClassName(String materialClassName) {
	this.materialClassName = materialClassName;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "{materialClassId=" + materialClassId + ";materialClassName=" + materialClassName + "}";
    }

}
