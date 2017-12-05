package org.wso2.identity.carbon.user.consent.mgt.backend.model;

public class ServicesDO {
    private int serviceId;
    private String serviceDescription;
    private PurposeDetailsDO purposeDetails;
    private PurposeDetailsDO[] purposeDetailsArr;

    public ServicesDO() {
    }

    public ServicesDO(int serviceId, String serviceDescription, PurposeDetailsDO[] purposeDetails) {
        this.serviceId = serviceId;
        this.serviceDescription = serviceDescription;
        this.purposeDetailsArr = purposeDetails;
    }

    public ServicesDO(int serviceId, String serviceDescription, PurposeDetailsDO purposeDetails) {
        this.serviceId = serviceId;
        this.serviceDescription = serviceDescription;
        this.purposeDetails = purposeDetails;
    }

    public ServicesDO(int serviceId, String serviceDescription) {
        this.serviceId = serviceId;
        this.serviceDescription = serviceDescription;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public void setPurposeDetails(PurposeDetailsDO purposeDetails) {
        this.purposeDetails = purposeDetails;
    }

    public PurposeDetailsDO getPurposeDetails() {
        return this.purposeDetails;
    }

    public void setPurposeDetails(PurposeDetailsDO[] purposeDetails) {
        this.purposeDetailsArr = purposeDetails;

    }

    public PurposeDetailsDO[] getPurposeDetailsArr() {
        return purposeDetailsArr;
    }
}
