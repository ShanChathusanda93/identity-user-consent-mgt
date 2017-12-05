package org.wso2.identity.carbon.user.consent.mgt.backend.JSONParserLayer;

public class JSONObjectCreator {
    private String subject;
    private String collectionMethod;
    private String jurisdiction;
    private String version;
    private String consentTimestamp;
    private JSONDataController dataController;
    private String publicKey;
    private String policyUrl;
    private JSONService[] services;

    public JSONObjectCreator(String subject, String collectionMethod, String jurisdiction, String version, String
            consentTimestamp, JSONService[] services) {
        this.subject = subject;
        this.collectionMethod = collectionMethod;
        this.jurisdiction = jurisdiction;
        this.version = version;
        this.consentTimestamp = consentTimestamp;
        this.services = services;
    }

    public JSONObjectCreator() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCollectionMethod() {
        return collectionMethod;
    }

    public void setCollectionMethod(String collectionMethod) {
        this.collectionMethod = collectionMethod;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getConsentTimestamp() {
        return consentTimestamp;
    }

    public void setConsentTimestamp(String consentTimestamp) {
        this.consentTimestamp = consentTimestamp;
    }

    public JSONService[] getServices() {
        return services;
    }

    public void setServices(JSONService[] services) {
        this.services = services;
    }

    public JSONDataController getDataController() {
        return dataController;
    }

    public void setDataController(JSONDataController dataControllerId) {
        this.dataController = dataController;
    }

    public String getPolicyUrl() {
        return policyUrl;
    }

    public void setPolicyUrl(String policyUrl) {
        this.policyUrl = policyUrl;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
