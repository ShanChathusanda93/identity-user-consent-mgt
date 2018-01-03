package org.wso2.identity.carbon.user.consent.mgt.backend.jsonParser;

public class JSONAddress {
    private String addressCountry;
    private String streetAddress;


    public String getAddressCountry() {
        return addressCountry;
    }

    public void setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
}
