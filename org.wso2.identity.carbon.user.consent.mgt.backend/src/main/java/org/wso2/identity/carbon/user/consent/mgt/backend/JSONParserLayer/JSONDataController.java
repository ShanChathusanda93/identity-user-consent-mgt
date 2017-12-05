package org.wso2.identity.carbon.user.consent.mgt.backend.JSONParserLayer;

public class JSONDataController {
    private String org;
    private String phone;
    private String contact;
    private String email;
    private String policyUrl;
    private JSONAddress address;

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPolicyUrl() {
        return policyUrl;
    }

    public void setPolicyUrl(String policyUrl) {
        this.policyUrl = policyUrl;
    }

    public JSONAddress getAddress() {
        return address;
    }

    public void setAddress(JSONAddress address) {
        this.address = address;
    }
}
