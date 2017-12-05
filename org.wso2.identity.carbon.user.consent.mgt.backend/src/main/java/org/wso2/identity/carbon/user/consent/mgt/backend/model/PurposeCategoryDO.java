package org.wso2.identity.carbon.user.consent.mgt.backend.model;

public class PurposeCategoryDO {
    private int purposeId;
    private int purposeCatId;
    private String purposeCatShortCode;
    private String purposeCatDes;

    public PurposeCategoryDO() { }

    public PurposeCategoryDO(int purposeCatId, String purposeCatShortCode, String purposeCatDes) {
        this.purposeCatId = purposeCatId;
        this.purposeCatShortCode = purposeCatShortCode;
        this.purposeCatDes = purposeCatDes;
    }

    public int getPurposeCatId() {
        return purposeCatId;
    }

    public void setPurposeCatId(int purposeCatId) {
        this.purposeCatId = purposeCatId;
    }

    public String getPurposeCatShortCode() {
        return purposeCatShortCode;
    }

    public void setPurposeCatShortCode(String purposeCatShortCode) {
        this.purposeCatShortCode = purposeCatShortCode;
    }

    public String getPurposeCatDes() {
        return purposeCatDes;
    }

    public void setPurposeCatDes(String purposeCatDes) {
        this.purposeCatDes = purposeCatDes;
    }

    public int getPurposeId() {
        return purposeId;
    }

    public void setPurposeId(int purposeId) {
        this.purposeId = purposeId;
    }
}
