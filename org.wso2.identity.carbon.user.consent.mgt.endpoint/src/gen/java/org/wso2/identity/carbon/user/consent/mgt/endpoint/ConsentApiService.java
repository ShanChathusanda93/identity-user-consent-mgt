package org.wso2.identity.carbon.user.consent.mgt.endpoint;

import org.wso2.identity.carbon.user.consent.mgt.endpoint.*;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.*;

import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.DataControllerInputDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PiiCategoryDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PiiCatListDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PurposeCategoryDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PurposeCategoryListDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PurposeDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PurposeListDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ServiceWebFormDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ThirdPartyDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ThirdPartyListDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ConsentReceiptDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.UserConsentWebFormDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ConsentRevokeListDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ServiceListDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ServiceCRDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ConsentByThirdPartyDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public abstract class ConsentApiService {
    public abstract Response consentConfigurationDataControllerDelete(Integer dataControllerId);
    public abstract Response consentConfigurationDataControllerGet();
    public abstract Response consentConfigurationDataControllerIdGet(Integer dataControllerId);
    public abstract Response consentConfigurationDataControllerPost(DataControllerInputDTO dataController);
    public abstract Response consentConfigurationDataControllerPut(DataControllerInputDTO dataController);
    public abstract Response consentConfigurationPersonalInfoCatIdGet(Integer categoryId);
    public abstract Response consentConfigurationPersonalInfoCategoryDelete(Integer categoryId);
    public abstract Response consentConfigurationPersonalInfoCategoryGet();
    public abstract Response consentConfigurationPersonalInfoCategoryPost(PiiCategoryDTO piiCategory);
    public abstract Response consentConfigurationPersonalInfoCategoryPut(PiiCategoryDTO piiCategory);
    public abstract Response consentConfigurationPurposeCategoryDelete(Integer purposeCategoryId);
    public abstract Response consentConfigurationPurposeCategoryGet();
    public abstract Response consentConfigurationPurposeCategoryIdGet(Integer categoryId);
    public abstract Response consentConfigurationPurposeCategoryPost(PurposeCategoryDTO purposeCategory);
    public abstract Response consentConfigurationPurposeCategoryPut(PurposeCategoryDTO purposeCategory);
    public abstract Response consentConfigurationPurposeDelete(Integer categoryId);
    public abstract Response consentConfigurationPurposeGet();
    public abstract Response consentConfigurationPurposeIdGet(Integer categoryId);
    public abstract Response consentConfigurationPurposePost(PurposeDTO purpose);
    public abstract Response consentConfigurationPurposePut(PurposeDTO purpose);
    public abstract Response consentConfigurationServiceDelete(Integer categoryId);
    public abstract Response consentConfigurationServiceGet();
    public abstract Response consentConfigurationServiceIdGet(Integer categoryId);
    public abstract Response consentConfigurationServicePost(ServiceWebFormDTO service);
    public abstract Response consentConfigurationServicePut(ServiceWebFormDTO service);
    public abstract Response consentConfigurationThirdPartyDelete(Integer categoryId);
    public abstract Response consentConfigurationThirdPartyGet();
    public abstract Response consentConfigurationThirdPartyPost(ThirdPartyDTO thirdParty);
    public abstract Response consentConfigurationThirdPartyPut(ThirdPartyDTO thirdParty);
    public abstract Response consentReceiptPost(ConsentReceiptDTO userDetails);
    public abstract Response consentReceiptWebFormPost(UserConsentWebFormDTO userConsentWebForm);
    public abstract Response consentRevokePut(ConsentRevokeListDTO revokingConsent);
    public abstract Response consentUserNameReceiptGet(String userName);
    public abstract Response consentUserNameServiceListGet(String userName);
    public abstract Response consentUserNameServicesServiceIdGet(String userName,Integer serviceId);
    public abstract Response consentUserNameServicesServiceIdPurposeGet(String userName,Integer serviceId,Integer purposeId);
    public abstract Response consentUserNameThirdPartyGet(String userName,Integer thirdPartyId);
}

