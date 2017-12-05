package org.wso2.identity.carbon.user.consent.mgt.backend.model;

public class PiiCategoryDO {
    private int purposeId;
    private int piiCatId;
    private String piiCat;
    private String piiCatDescription;
    private int sensitivity;


    public PiiCategoryDO() {
    }

    public PiiCategoryDO(int piiCatId, String piiCat, int sensitivity) {
        this.piiCatId = piiCatId;
        this.piiCat = piiCat;
        this.sensitivity = sensitivity;
    }

    public PiiCategoryDO(int piiCatId, String piiCat, String piiCatDescription, int sensitivity) {
        this.piiCatId = piiCatId;
        this.piiCat = piiCat;
        this.piiCatDescription = piiCatDescription;
        this.sensitivity = sensitivity;
    }

    public int getPurposeId() {
        return purposeId;
    }

    public void setPurposeId(int purposeId) {
        this.purposeId = purposeId;
    }

    public int getPiiCatId() {
        return piiCatId;
    }

    public void setPiiCatId(int piiCatId) {
        this.piiCatId = piiCatId;
    }

    public String getPiiCat() {
        return piiCat;
    }

    public void setPiiCat(String piiCat) {
        this.piiCat = piiCat;
    }

    public int getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(int sensitivity) {
        this.sensitivity = sensitivity;
    }

    public String getPiiCatDescription() {
        return piiCatDescription;
    }

    public void setPiiCatDescription(String piiCatDescription) {
        this.piiCatDescription = piiCatDescription;
    }
}
