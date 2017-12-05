package identity_user_consent_mgt_endpoint;

import identity_user_consent_mgt_endpoint.*;
import identity_user_consent_mgt_endpoint.dto.*;

import identity_user_consent_mgt_endpoint.dto.DataControllerInputDTO;
import identity_user_consent_mgt_endpoint.dto.PiiCatListDTO;
import identity_user_consent_mgt_endpoint.dto.PiiCategoryDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeListDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeInputDTO;
import identity_user_consent_mgt_endpoint.dto.ServiceInputDTO;
import identity_user_consent_mgt_endpoint.dto.ConsentReceiptDTO;
import identity_user_consent_mgt_endpoint.dto.UserConsentWebFormDTO;
import identity_user_consent_mgt_endpoint.dto.ConsentRevokeListDTO;
import identity_user_consent_mgt_endpoint.dto.ConsentDTO;
import identity_user_consent_mgt_endpoint.dto.InlineResponse200DTO;
import identity_user_consent_mgt_endpoint.dto.ServiceCRDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeDTO;
import identity_user_consent_mgt_endpoint.dto.ConsentByThirdPartyDTO;

import java.util.List;

import java.io.InputStream;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import javax.ws.rs.core.Response;

public abstract class ConsentApiService {
    public abstract Response consentConfigurationDataControllerPost(DataControllerInputDTO dataController);
    public abstract Response consentConfigurationPersonalInfoCategoryGet();
    public abstract Response consentConfigurationPersonalInfoCategoryPost(PiiCategoryDTO piiCategory);
    public abstract Response consentConfigurationPersonalInfoCategoryPut(PiiCategoryDTO piiCategory);
    public abstract Response consentConfigurationPurposeGet();
    public abstract Response consentConfigurationPurposePost(PurposeInputDTO purpose);
    public abstract Response consentConfigurationPurposePut(PurposeInputDTO purpose);
    public abstract Response consentConfigurationServiceGet(ServiceInputDTO service);
    public abstract Response consentConfigurationServicePost(ServiceInputDTO service);
    public abstract Response consentConfigurationServicePut(ServiceInputDTO service);
    public abstract Response consentReceiptPost(ConsentReceiptDTO userDetails);
    public abstract Response consentReceiptWebFormPost(UserConsentWebFormDTO userConsentWebForm);
    public abstract Response consentRevokePut(ConsentRevokeListDTO revokingConsent);
    public abstract Response consentSubjectNameGet(String subjectName);
    public abstract Response consentSubjectNameReceiptGet(String subjectName);
    public abstract Response consentSubjectNameServicesGet(String subjectName);
    public abstract Response consentSubjectNameServicesServiceIdGet(String subjectName,Integer serviceId);
    public abstract Response consentSubjectNameServicesServiceIdPurposeGet(String subjectName,Integer serviceId,Integer purposeId);
    public abstract Response consentSubjectNameThirdPartyGet(String subjectName,Integer thirdPartyId);
}

