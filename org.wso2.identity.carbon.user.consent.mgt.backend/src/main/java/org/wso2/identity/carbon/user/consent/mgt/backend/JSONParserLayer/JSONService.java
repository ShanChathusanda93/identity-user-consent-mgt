package org.wso2.identity.carbon.user.consent.mgt.backend.JSONParserLayer;

public class JSONService {
    private String serviceName;
    private JSONPurpose[] purposes;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public JSONPurpose[] getPurposes() {
        return purposes;
    }

    public void setPurposes(JSONPurpose[] purposes) {
        this.purposes = purposes;
    }
}
