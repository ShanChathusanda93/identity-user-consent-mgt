package org.wso2.identity.carbon.user.consent.mgt.backend.model;

public class ThirdPartyDO {
    private int thirdPartyId;
    private String thirdPartyName;

    public ThirdPartyDO() {
    }

    public int getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(int thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    public String getThirdPartyName() {
        return thirdPartyName;
    }

    public void setThirdPartyName(String thirdPartyName) {
        this.thirdPartyName = thirdPartyName;
    }
}
