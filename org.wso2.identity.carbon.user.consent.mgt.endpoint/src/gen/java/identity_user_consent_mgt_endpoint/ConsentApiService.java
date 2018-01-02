package identity_user_consent_mgt_endpoint;

import identity_user_consent_mgt_endpoint.*;
import identity_user_consent_mgt_endpoint.dto.*;

import identity_user_consent_mgt_endpoint.dto.DataControllerInputDTO;
import identity_user_consent_mgt_endpoint.dto.PiiCategoryDTO;
import identity_user_consent_mgt_endpoint.dto.PiiCatListDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeCategoryDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeCategoryListDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeListDTO;
import identity_user_consent_mgt_endpoint.dto.ServiceWebFormDTO;
import identity_user_consent_mgt_endpoint.dto.ThirdPartyDTO;
import identity_user_consent_mgt_endpoint.dto.ThirdPartyListDTO;
import identity_user_consent_mgt_endpoint.dto.ConsentReceiptDTO;
import identity_user_consent_mgt_endpoint.dto.UserConsentWebFormDTO;
import identity_user_consent_mgt_endpoint.dto.ConsentRevokeListDTO;
import identity_user_consent_mgt_endpoint.dto.ServiceListDTO;
import identity_user_consent_mgt_endpoint.dto.ServiceCRDTO;
import identity_user_consent_mgt_endpoint.dto.ConsentByThirdPartyDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public abstract class ConsentApiService {
    public abstract Response consentConfigurationDataControllerDelete(Integer dataControllerId);
    public abstract Response consentConfigurationDataControllerGet(String organizationName);
    public abstract Response consentConfigurationDataControllerPost(DataControllerInputDTO dataController);
    public abstract Response consentConfigurationDataControllerPut(DataControllerInputDTO dataController);
    public abstract Response consentConfigurationPersonalInfoCategoryDelete(Integer categoryId);
    public abstract Response consentConfigurationPersonalInfoCategoryGet();
    public abstract Response consentConfigurationPersonalInfoCategoryPost(PiiCategoryDTO piiCategory);
    public abstract Response consentConfigurationPersonalInfoCategoryPut(PiiCategoryDTO piiCategory);
    public abstract Response consentConfigurationPurposeCategoryDelete(Integer purposeCategoryId);
    public abstract Response consentConfigurationPurposeCategoryGet();
    public abstract Response consentConfigurationPurposeCategoryPost(PurposeCategoryDTO purposeCategory);
    public abstract Response consentConfigurationPurposeCategoryPut(PurposeCategoryDTO purposeCategory);
    public abstract Response consentConfigurationPurposeDelete(Integer categoryId);
    public abstract Response consentConfigurationPurposeGet();
    public abstract Response consentConfigurationPurposePost(PurposeDTO purpose);
    public abstract Response consentConfigurationPurposePut(PurposeDTO purpose);
    public abstract Response consentConfigurationServiceDelete(Integer categoryId);
    public abstract Response consentConfigurationServiceGet();
    public abstract Response consentConfigurationServicePost(ServiceWebFormDTO service);
    public abstract Response consentConfigurationServicePut(ServiceWebFormDTO service);
    public abstract Response consentConfigurationThirdPartyDelete(Integer categoryId);
    public abstract Response consentConfigurationThirdPartyGet();
    public abstract Response consentConfigurationThirdPartyPost(ThirdPartyDTO thirdParty);
    public abstract Response consentConfigurationThirdPartyPut(ThirdPartyDTO thirdParty);
    public abstract Response consentReceiptPost(ConsentReceiptDTO userDetails);
    public abstract Response consentReceiptWebFormPost(UserConsentWebFormDTO userConsentWebForm);
    public abstract Response consentRevokePut(ConsentRevokeListDTO revokingConsent);
    public abstract Response consentSubjectNameReceiptGet(String subjectName);
    public abstract Response consentSubjectNameServiceListGet(String subjectName);
    public abstract Response consentSubjectNameServicesServiceIdGet(String subjectName,Integer serviceId);
    public abstract Response consentSubjectNameServicesServiceIdPurposeGet(String subjectName,Integer serviceId,Integer purposeId);
    public abstract Response consentSubjectNameThirdPartyGet(String subjectName,Integer thirdPartyId);
}

