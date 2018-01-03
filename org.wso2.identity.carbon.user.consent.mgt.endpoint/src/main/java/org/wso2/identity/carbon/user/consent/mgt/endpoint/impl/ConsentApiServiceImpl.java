package org.wso2.identity.carbon.user.consent.mgt.endpoint.impl;

import org.wso2.identity.carbon.user.consent.mgt.endpoint.ApiResponseMessage;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.ConsentApiService;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ConsentByThirdPartyDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ConsentReceiptDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ConsentRevokeListDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.DataControllerInputDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PiiCategoryDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PurposeCategoryDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PurposeDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ServiceCRDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ServiceListDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ServiceWebFormDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ThirdPartyDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.UserConsentWebFormDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.mapping.ConsentMapping;
import org.json.simple.JSONObject;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ConsentDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.DataControllerDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PiiCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeDetailsDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ServicesDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ThirdPartyDO;
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
        DataControllerInputDTO dataControllerDTO=new DataControllerInputDTO();
        try {
            getConsentService().setDataController(dataControllerDO);
            dataControllerDO=getConsentService().getDataControllerById(dataController.getId());
            dataControllerDTO=ConsentMapping.getConsentConfigurationDataController(dataControllerDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(dataControllerDTO).build();
    }

    @Override
    public Response consentConfigurationDataControllerGet() {
        List<DataControllerInputDTO> dataControllerInputDTOList = new ArrayList<>();
        try {
            List<DataControllerDO> dataControllerList = getConsentService().getDataControllerList();
            dataControllerInputDTOList = ConsentMapping.getConsentConfigurationDataController(dataControllerList);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(dataControllerInputDTOList).build();
    }

    @Override
    public Response consentConfigurationDataControllerPut(DataControllerInputDTO dataController) {
        DataControllerDO dataControllerDO = ConsentMapping.updateDataController(dataController);
        DataControllerInputDTO dataControllerOutDTO=new DataControllerInputDTO();
        try {
            getConsentService().updateDataController(dataControllerDO);
            dataControllerDO=getConsentService().getDataControllerById(dataController.getId());
            dataControllerOutDTO=ConsentMapping.getConsentConfigurationDataController(dataControllerDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(dataControllerOutDTO).build();
    }

    @Override
    public Response consentConfigurationDataControllerDelete(Integer dataControllerId) {
        try {
            getConsentService().deleteDataController(dataControllerId);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationDataControllerIdGet(Integer dataControllerId) {
        DataControllerInputDTO dataControllerOutputDTO=new DataControllerInputDTO();
        try {
            DataControllerDO dataController=getConsentService().getDataControllerById(dataControllerId);
            dataControllerOutputDTO=ConsentMapping.getConsentConfigurationDataController(dataController);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(dataControllerOutputDTO).build();
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
        PiiCategoryDTO piiCategoryDTO=new PiiCategoryDTO();
        try {
            PiiCategoryDO piiCategoryOut=getConsentService().setPersonalInfoCat(piiCategoryDO);
            piiCategoryDTO=ConsentMapping.setPiiCategoryDOToPiiCategoryDTO(piiCategoryOut);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(piiCategoryDTO).build();
    }

    @Override
    public Response consentConfigurationPersonalInfoCategoryPut(PiiCategoryDTO piiCategory) {
        PiiCategoryDO piiCategoryDO = ConsentMapping.updatePersonallyIdentifiableInfoCat(piiCategory);
        PiiCategoryDTO piiCategoryDTO=new PiiCategoryDTO();
        try {
            PiiCategoryDO piiCategoryDOUpdated=getConsentService().updatePersonalInfoCat(piiCategoryDO);
            piiCategoryDTO=ConsentMapping.setPiiCategoryDOToPiiCategoryDTO(piiCategoryDOUpdated);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(piiCategoryDTO).build();
    }

    @Override
    public Response consentConfigurationPersonalInfoCategoryDelete(Integer categoryId) {
        PiiCategoryDTO piiCategoryDTO = new PiiCategoryDTO();
        try {
            PiiCategoryDO piiCategory = getConsentService().deletePersonalInfoCat(categoryId);
            piiCategoryDTO.setPiiCat(piiCategory.getPiiCat());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(piiCategoryDTO).build();
    }

    @Override
    public Response consentConfigurationPersonalInfoCatIdGet(Integer categoryId) {
        PiiCategoryDTO piiCategoryDTO=new PiiCategoryDTO();
        try {
            PiiCategoryDO piiCategory=getConsentService().getPersonalInfoCatById(categoryId);
            piiCategoryDTO=ConsentMapping.setPiiCategoryDOToPiiCategoryDTO(piiCategory);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(piiCategoryDTO).build();
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
    public Response consentConfigurationPurposePost(PurposeDTO purpose) {
        PurposeDetailsDO purposeDetailsDO = ConsentMapping.setConsentConfigurationPurpose(purpose);
        try {
            getConsentService().setPurpose(purposeDetailsDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationPurposePut(PurposeDTO purpose) {
        PurposeDetailsDO purposeDO = ConsentMapping.updatePurpose(purpose);
        try {
            getConsentService().updatePurpose(purposeDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationPurposeDelete(Integer id) {
        PurposeDTO purposeDTO = new PurposeDTO();
        try {
            PurposeDetailsDO purpose = getConsentService().deletePurpose(id);
            purposeDTO.setPurpose(purpose.getPurpose());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(purposeDTO).build();
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
    public Response consentConfigurationServicePost(ServiceWebFormDTO service) {
        ServicesDO servicesDO = new ServicesDO();
        servicesDO.setServiceDescription(service.getServiceName());
        PurposeDetailsDO[] purposeDOArr = new PurposeDetailsDO[service.getPurposes().size()];
        for (int i = 0; i < service.getPurposes().size(); i++) {
            purposeDOArr[i] = new PurposeDetailsDO();
            purposeDOArr[i].setPurposeId(service.getPurposes().get(i).getPurposeId());
        }
        servicesDO.setPurposeDetails(purposeDOArr);
        try {
            getConsentService().setService(servicesDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationServicePut(ServiceWebFormDTO service) {
        ServicesDO servicesDO = new ServicesDO();
        servicesDO.setServiceId(service.getServiceId());
        servicesDO.setServiceDescription(service.getServiceName());
        PurposeDetailsDO[] purposeDOS = new PurposeDetailsDO[service.getPurposes().size()];
        for (int i = 0; i < service.getPurposes().size(); i++) {
            purposeDOS[i] = new PurposeDetailsDO();
            purposeDOS[i].setPurposeId(service.getPurposes().get(i).getPurposeId());
        }
        servicesDO.setPurposeDetails(purposeDOS);
        try {
            getConsentService().updateService(servicesDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationServiceDelete(Integer id) {
        ServiceWebFormDTO serviceDTO = new ServiceWebFormDTO();
        try {
            ServicesDO service = getConsentService().deleteService(id);
            serviceDTO.setServiceName(service.getServiceDescription());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(serviceDTO).build();
    }

    @Override
    public Response consentReceiptPost(ConsentReceiptDTO userDetails) {
//        This has to be discussed. I already developed the JSON parse to do this.
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentReceiptWebFormPost(UserConsentWebFormDTO userConsentWebForm) {
        ConsentDO consentDO = ConsentMapping.setUserAndDataController(userConsentWebForm);
        ServicesDO[] servicesDOS = ConsentMapping.setUserConsents(userConsentWebForm);
        try {
            getConsentService().setConsentDetailsForUser(consentDO, servicesDOS);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentRevokePut(ConsentRevokeListDTO revokingConsent) {
        List<ServicesDO> servicesList = ConsentMapping.revokeConsent(revokingConsent);
        try {
            getConsentService().revokeConsent(revokingConsent.getSubjectName(), servicesList);
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
    public Response consentUserNameReceiptGet(String subjectName) {
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
    public Response consentUserNameServiceListGet(String subjectName) {
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
    public Response consentUserNameServicesServiceIdGet(String subjectName, Integer serviceId) {
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
    public Response consentUserNameServicesServiceIdPurposeGet(String subjectName, Integer serviceId, Integer
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
    public Response consentUserNameThirdPartyGet(String subjectName, Integer thirdPartyId) {
        ConsentByThirdPartyDTO consent = new ConsentByThirdPartyDTO();
        try {
            List<ServicesDO> servicesDOList = getConsentService().getServicesByUserByThirdParty(subjectName, thirdPartyId);
            consent = ConsentMapping.getConsentSubjectNameThirdParty(servicesDOList);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(consent).build();
    }

    @Override
    public Response consentConfigurationPurposeCategoryGet() {
        List<PurposeCategoryDTO> purposeCategoryDTOList = new ArrayList<>();
        try {
            List<PurposeCategoryDO> purposeCategoryList = getConsentService().getPurposeCategories();
            purposeCategoryDTOList = ConsentMapping.getPurposeCategories(purposeCategoryList);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(purposeCategoryDTOList).build();
    }

    @Override
    public Response consentConfigurationPurposeCategoryPost(PurposeCategoryDTO purposeCategory) {
        PurposeCategoryDO purposeCategoryDO = ConsentMapping.setPurposeCategory(purposeCategory);
        try {
            getConsentService().setPurposeCategory(purposeCategoryDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationPurposeCategoryPut(PurposeCategoryDTO purposeCategory) {
        PurposeCategoryDO purposeCategoryDO = ConsentMapping.updatePurposeCategory(purposeCategory);
        try {
            getConsentService().updatePurposeCategory(purposeCategoryDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationPurposeCategoryDelete(Integer purposeCategoryId) {
        PurposeCategoryDTO purposeCategoryDTO = new PurposeCategoryDTO();
        try {
            PurposeCategoryDO purposeCategory = getConsentService().deletePurposeCategory(purposeCategoryId);
            purposeCategoryDTO.setPurposeCategoryShortCode(purposeCategory.getPurposeCatShortCode());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(purposeCategoryDTO).build();
    }

    @Override
    public Response consentConfigurationThirdPartyGet() {
        List<ThirdPartyDTO> thirdPartyDTOList = new ArrayList<>();
        try {
            List<ThirdPartyDO> thirdPartyList = getConsentService().getThirdParties();
            for (ThirdPartyDO thirdParty : thirdPartyList) {
                ThirdPartyDTO thirdPartyDTO = new ThirdPartyDTO();
                thirdPartyDTO.setThirdPartyId(thirdParty.getThirdPartyId());
                thirdPartyDTO.setThirdPartyName(thirdParty.getThirdPartyName());
                thirdPartyDTOList.add(thirdPartyDTO);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(thirdPartyDTOList).build();
    }

    @Override
    public Response consentConfigurationThirdPartyPost(ThirdPartyDTO thirdParty) {
        ThirdPartyDO thirdPartyDO = new ThirdPartyDO();
        thirdPartyDO.setThirdPartyName(thirdParty.getThirdPartyName());
        try {
            getConsentService().setThirdParty(thirdPartyDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationThirdPartyPut(ThirdPartyDTO thirdParty) {
        ThirdPartyDO thirdPartyDO = new ThirdPartyDO();
        thirdPartyDO.setThirdPartyId(thirdParty.getThirdPartyId());
        thirdPartyDO.setThirdPartyName(thirdParty.getThirdPartyName());
        try {
            getConsentService().updateThirdParty(thirdPartyDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationThirdPartyDelete(Integer id) {
        ThirdPartyDTO thirdPartyDTO = new ThirdPartyDTO();
        try {
            ThirdPartyDO thirdParty = getConsentService().deleteThirdParty(id);
            thirdPartyDTO.setThirdPartyName(thirdParty.getThirdPartyName());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(thirdPartyDTO).build();
    }
}