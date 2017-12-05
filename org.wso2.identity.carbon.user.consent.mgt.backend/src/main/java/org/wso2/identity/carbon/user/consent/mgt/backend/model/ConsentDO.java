package org.wso2.identity.carbon.user.consent.mgt.backend.model;

public class ConsentDO {
    private String version;
    private String jurisdiction;
    private String collectionMethod;
    private String SGUID;
    private String piiPrincipalId;
    private String consentTimestamp;
    private DataControllerDO dataController;

    public ConsentDO() {
    }

    public ConsentDO(String version, String jurisdiction, String collectionMethod, String SGUID, String subject,
                     String consentTimestamp, DataControllerDO dataController) {
        this.version = version;
        this.jurisdiction = jurisdiction;
        this.collectionMethod = collectionMethod;
        this.SGUID = SGUID;
        this.piiPrincipalId = subject;
        this.consentTimestamp = consentTimestamp;
        this.dataController = dataController;
    }

    public ConsentDO(String collectionMethod, String SGUID, String piiPrincipalId, String consentTimestamp, DataControllerDO dataController) {
        this.collectionMethod = collectionMethod;
        this.SGUID = SGUID;
        this.piiPrincipalId = piiPrincipalId;
        this.consentTimestamp = consentTimestamp;
        this.dataController = dataController;
    }

    public DataControllerDO getDataController() {
        return dataController;
    }

    public void setDataController(DataControllerDO dataController) {

        this.dataController = dataController;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public String getCollectionMethod() {
        return collectionMethod;
    }

    public void setCollectionMethod(String collectionMethod) {
        this.collectionMethod = collectionMethod;
    }

    public String getSGUID() {
        return SGUID;
    }

    public void setSGUID(String SGUID) {
        this.SGUID = SGUID;
    }

    public String getPiiPrincipalId() {
        return piiPrincipalId;
    }

    public void setPiiPrincipalId(String subject) {
        this.piiPrincipalId = subject;
    }

    public String getConsentTimestamp() {
        return consentTimestamp;
    }

    public void setConsentTimestamp(String consentTimestamp) {
        this.consentTimestamp = consentTimestamp;
    }

}
