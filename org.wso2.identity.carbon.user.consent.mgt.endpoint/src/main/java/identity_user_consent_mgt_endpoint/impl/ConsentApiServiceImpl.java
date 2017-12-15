package identity_user_consent_mgt_endpoint.impl;

import identity_user_consent_mgt_endpoint.ApiResponseMessage;
import identity_user_consent_mgt_endpoint.ConsentApiService;
import identity_user_consent_mgt_endpoint.dto.ConsentByThirdPartyDTO;
import identity_user_consent_mgt_endpoint.dto.ConsentReceiptDTO;
import identity_user_consent_mgt_endpoint.dto.ConsentRevokeListDTO;
import identity_user_consent_mgt_endpoint.dto.DataControllerInputDTO;
import identity_user_consent_mgt_endpoint.dto.PiiCategoryDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeInputDTO;
import identity_user_consent_mgt_endpoint.dto.ServiceCRDTO;
import identity_user_consent_mgt_endpoint.dto.ServiceInputDTO;
import identity_user_consent_mgt_endpoint.dto.ServiceListDTO;
import identity_user_consent_mgt_endpoint.dto.ServiceWebFormDTO;
import identity_user_consent_mgt_endpoint.dto.UserConsentWebFormDTO;
import mapping.ConsentMapping;
import org.json.simple.JSONObject;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ConsentDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.DataControllerDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PiiCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeDetailsDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ServicesDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.service.ConsentBackend;

import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ConsentApiServiceImpl extends ConsentApiService {
    private static ConsentBackend getConsentService() {
        return (ConsentBackend) PrivilegedCarbonContext.getThreadLocalCarbonContext()
                .getOSGiService(ConsentBackend.class, null);
    }

    @Override
    public Response consentConfigurationDataControllerPost(DataControllerInputDTO dataController) {
        DataControllerDO dataControllerDO = ConsentMapping.setConsentConfigurationDataController(dataController);
        try {
            getConsentService().setDataController(dataControllerDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationDataControllerGet(Integer dataControllerId) {
        DataControllerInputDTO dataControllerInputDTO = null;
        try {
            DataControllerDO dataControllerDO = getConsentService().getDataController(dataControllerId);
            dataControllerInputDTO = ConsentMapping.getConsentConfigurationDataController
                    (dataControllerDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(dataControllerInputDTO).build();
    }

    @Override
    public Response consentConfigurationDataControllerPut(DataControllerInputDTO dataController) {
        DataControllerDO dataControllerDO=ConsentMapping.updateDataController(dataController);
        try {
            getConsentService().updateDataController(dataControllerDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationPersonalInfoCategoryGet() {
        List<PiiCategoryDTO> piiCategoryDTOList = new ArrayList<>();
        try {
            List<PiiCategoryDO> piiCategoryDOList = getConsentService().getPersonalIdentifyInfoCat();
            piiCategoryDTOList = ConsentMapping.getConsentConfigurationPersonalInfoCategory(piiCategoryDOList);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(piiCategoryDTOList).build();
    }

    @Override
    public Response consentConfigurationPersonalInfoCategoryPost(PiiCategoryDTO piiCategory) {
        PiiCategoryDO piiCategoryDO = ConsentMapping.setConsentConfigurationPersonalInfoCategory(piiCategory);
        try {
            getConsentService().setPersonalInfoCat(piiCategoryDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationPersonalInfoCategoryPut(PiiCategoryDTO piiCategory) {
        PiiCategoryDO piiCategoryDO=ConsentMapping.updatePersonallyIdentifiableInfoCat(piiCategory);
        try {
            getConsentService().updatePersonallyIdentifiableInfoCat(piiCategoryDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationPurposeGet() {
        List<PurposeDTO> purposeDTOList = new ArrayList<>();
        try {
            List<PurposeDetailsDO> purposeDetailsDOList = getConsentService().getPurposeDetailsForConf();
            purposeDTOList = ConsentMapping.getConsentConfigurationPurpose(purposeDetailsDOList);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(purposeDTOList).build();
    }

    @Override
    public Response consentConfigurationPurposePost(PurposeInputDTO purpose) {
        PurposeDetailsDO purposeDetailsDO = ConsentMapping.setConsentConfigurationPurpose(purpose);
        try {
            getConsentService().setPurpose(purposeDetailsDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationPurposePut(PurposeInputDTO purpose) {
        PurposeDetailsDO purposeDO =ConsentMapping.updatePurpose(purpose);
        try {
            getConsentService().updatePurpose(purposeDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationServiceGet() {
        List<ServiceWebFormDTO> serviceDTOList = new ArrayList<>();
        try {
            List<ServicesDO> servicesDOList = getConsentService().getServicesForConf();
            serviceDTOList = ConsentMapping.getConsentConfigurationService(servicesDOList);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(serviceDTOList).build();
    }

    @Override
    public Response consentConfigurationServicePost(ServiceInputDTO service) {
        ServicesDO servicesDO = new ServicesDO();
        servicesDO.setServiceDescription(service.getServiceName());
        try {
            getConsentService().setService(servicesDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationServicePut(ServiceInputDTO service) {
        ServicesDO servicesDO=new ServicesDO();
        servicesDO.setServiceDescription(service.getServiceName());
        try {
            getConsentService().updateService(servicesDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentReceiptPost(ConsentReceiptDTO userDetails) {
//        This has to be discussed. I already developed the JSON parse to do this.
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentReceiptWebFormPost(UserConsentWebFormDTO userConsentWebForm) {
        ConsentDO consentDO=ConsentMapping.setUserAndDataController(userConsentWebForm);
        ServicesDO[] servicesDOS=ConsentMapping.setUserConsents(userConsentWebForm);
        try {
            getConsentService().setConsentDetailsForUser(consentDO,servicesDOS);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentRevokePut(ConsentRevokeListDTO revokingConsent) {
        List<ServicesDO> servicesList =ConsentMapping.revokeConsent(revokingConsent);
        try {
            getConsentService().revokeConsent(revokingConsent.getSubjectName(),servicesList);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

//    @Override
//    public Response consentSubjectNameGet(String subjectName) {
//        This is already implemented as consentSubjectNameServiceListGet() this method has to be deleted.
//        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
//    }

    @Override
    public Response consentSubjectNameReceiptGet(String subjectName) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = getConsentService().getCreatedConsentReceipt(subjectName);
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(jsonObject).build();
    }

    @Override
    public Response consentSubjectNameServiceListGet(String subjectName) {
        ServiceListDTO serviceListDTO = new ServiceListDTO();
        try {
            List<ServicesDO> servicesDOList = getConsentService().getServicesForUserView(subjectName);
            serviceListDTO = ConsentMapping.getConsentSubjectNameServiceList(servicesDOList);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(serviceListDTO).build();
    }

    @Override
    public Response consentSubjectNameServicesServiceIdGet(String subjectName, Integer serviceId) {
        ServiceCRDTO serviceCRDTO = new ServiceCRDTO();
        try {
            ServicesDO servicesDO = getConsentService().getServiceByUserByServiceId(subjectName, serviceId);
            serviceCRDTO = ConsentMapping.getConsentSubjectNameServicesServiceId(servicesDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(serviceCRDTO).build();
    }

    @Override
    public Response consentSubjectNameServicesServiceIdPurposeGet(String subjectName, Integer serviceId, Integer
            purposeId) {
        PurposeDTO purposeDTO = new PurposeDTO();
        try {
            PurposeDetailsDO purposeDetailsDO = getConsentService().getPurposeByUserByServiceByPurposeId(subjectName,
                    serviceId, purposeId);
            purposeDTO = ConsentMapping.getConsentSubjectNameServicesServiceIdPurpose(purposeDetailsDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(purposeDTO).build();
    }

    @Override
    public Response consentSubjectNameThirdPartyGet(String subjectName, Integer thirdPartyId) {
        ConsentByThirdPartyDTO consent = new ConsentByThirdPartyDTO();
        try {
            List<ServicesDO> servicesDOList = getConsentService().getServicesByUserByThirdParty(subjectName, thirdPartyId);
            consent = ConsentMapping.getConsentSubjectNameThirdParty(servicesDOList);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(consent).build();
    }
}
