package identity_user_consent_mgt_endpoint.impl;

import identity_user_consent_mgt_endpoint.ApiResponseMessage;
import identity_user_consent_mgt_endpoint.ConsentApiService;
import identity_user_consent_mgt_endpoint.dto.AddressCRDTO;
import identity_user_consent_mgt_endpoint.dto.ConsentByThirdPartyDTO;
import identity_user_consent_mgt_endpoint.dto.ConsentDTO;
import identity_user_consent_mgt_endpoint.dto.ConsentReceiptDTO;
import identity_user_consent_mgt_endpoint.dto.ConsentRevokeListDTO;
import identity_user_consent_mgt_endpoint.dto.DataControllerIdDTO;
import identity_user_consent_mgt_endpoint.dto.DataControllerInputDTO;
import identity_user_consent_mgt_endpoint.dto.PiiCategoryDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeCategoryDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeInputDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeWebFormDTO;
import identity_user_consent_mgt_endpoint.dto.ServiceCRDTO;
import identity_user_consent_mgt_endpoint.dto.ServiceInputDTO;
import identity_user_consent_mgt_endpoint.dto.ServiceListDTO;
import identity_user_consent_mgt_endpoint.dto.ServiceWebFormDTO;
import identity_user_consent_mgt_endpoint.dto.UserConsentWebFormDTO;
import org.json.simple.JSONObject;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.DataControllerDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PiiCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeCategoryDO;
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
        DataControllerDO dataControllerDO=new DataControllerDO();
        dataControllerDO.setOrgName(dataController.getOrg());
        dataControllerDO.setContactName(dataController.getContact());
        dataControllerDO.setStreet(dataController.getAddress().getStreetAddress());
        dataControllerDO.setCountry(dataController.getAddress().getAddressCountry());
        dataControllerDO.setEmail(dataController.getEmail());
        dataControllerDO.setPhoneNo(dataController.getPhone());
        dataControllerDO.setPublicKey(dataController.getPublicKey());
        dataControllerDO.setPolicyUrl(dataController.getPolicyUrl());

        try {
            getConsentService().setDataController(dataControllerDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationDataControllerGet(Integer dataControllerId) {
        DataControllerInputDTO dataControllerInputDTO=new DataControllerInputDTO();
        try {
            DataControllerDO dataControllerDO=getConsentService().getDataController(dataControllerId);
            dataControllerInputDTO.setId(dataControllerDO.getDataControllerId());
            dataControllerInputDTO.setOrg(dataControllerDO.getOrgName());
            dataControllerInputDTO.setContact(dataControllerDO.getContactName());
            AddressCRDTO address=new AddressCRDTO();
            address.setStreetAddress(dataControllerDO.getStreet());
            address.setAddressCountry(dataControllerDO.getCountry());
            dataControllerInputDTO.setAddress(address);
            dataControllerInputDTO.setEmail(dataControllerDO.getEmail());
            dataControllerInputDTO.setPhone(dataControllerDO.getPhoneNo());
            dataControllerInputDTO.setPublicKey(dataControllerDO.getPublicKey());
            dataControllerInputDTO.setPolicyUrl(dataControllerDO.getPolicyUrl());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(dataControllerInputDTO).build();
    }

    @Override
    public Response consentConfigurationDataControllerPut(DataControllerInputDTO dataController) {
        return null;
    }

    @Override
    public Response consentConfigurationPersonalInfoCategoryGet() {
        List<PiiCategoryDTO> piiCategoryDTOList = new ArrayList<>();
        try {
            List<PiiCategoryDO> piiCategoryDOList = getConsentService().getPersonalIdentifyInfoCat();
            for (int i = 0; i < piiCategoryDOList.size(); i++) {
                PiiCategoryDTO piiCategoryDTO = new PiiCategoryDTO();
                piiCategoryDTO.setPiiCatId(piiCategoryDOList.get(i).getPiiCatId());
                piiCategoryDTO.setPiiCat(piiCategoryDOList.get(i).getPiiCat());
                piiCategoryDTO.setDescription(piiCategoryDOList.get(i).getPiiCatDescription());
                piiCategoryDTO.setSensitivity(piiCategoryDOList.get(i).getSensitivity());
                piiCategoryDTOList.add(piiCategoryDTO);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(piiCategoryDTOList).build();
    }

    @Override
    public Response consentConfigurationPersonalInfoCategoryPost(PiiCategoryDTO piiCategory) {
        PiiCategoryDO piiCategoryDO=new PiiCategoryDO();
        piiCategoryDO.setPiiCat(piiCategory.getPiiCat());
        piiCategoryDO.setPiiCatDescription(piiCategory.getDescription());
        piiCategoryDO.setSensitivity(piiCategory.getSensitivity());
        try {
            getConsentService().setPersonalInfoCat(piiCategoryDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationPersonalInfoCategoryPut(PiiCategoryDTO piiCategory) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationPurposeGet() {
        List<PurposeDTO> purposeDTOList = new ArrayList<>();
        try {
            List<PurposeDetailsDO> purposeDetailsDOList = getConsentService().getPurposeDetailsForConf();
            for (int i = 0; i < purposeDetailsDOList.size(); i++) {
                PurposeDTO purposeDTO = new PurposeDTO();
                purposeDTO.setPurposeId(purposeDetailsDOList.get(i).getPurposeId());
                purposeDTO.setPurpose(purposeDetailsDOList.get(i).getPurpose());

                List<PurposeCategoryDTO> purposeCategoryDTOList = new ArrayList<>();
                for (int j = 0; j < purposeDetailsDOList.get(i).getPurposeCategoryDOArr().length; j++) {
                    PurposeCategoryDTO purposeCategoryDTO = new PurposeCategoryDTO();
                    purposeCategoryDTO.setPursopeId(purposeDetailsDOList.get(i).getPurposeCategoryDOArr()[j]
                            .getPurposeId());
                    purposeCategoryDTO.setPurposeCategoryId(purposeDetailsDOList.get(i)
                            .getPurposeCategoryDOArr()[j].getPurposeCatId());
                    purposeCategoryDTO.setPurposeCategoryShortCode(purposeDetailsDOList.get(i)
                            .getPurposeCategoryDOArr()[j].getPurposeCatShortCode());
                    purposeCategoryDTOList.add(purposeCategoryDTO);
                }
                purposeDTO.setPurposeCategory(purposeCategoryDTOList);
                purposeDTO.setConsentType(purposeDetailsDOList.get(i).getConsentType());

                List<PiiCategoryDTO> piiCategoryDTOList = new ArrayList<>();
                for (int j = 0; j < purposeDetailsDOList.get(i).getpiiCategoryArr().length; j++) {
                    PiiCategoryDTO piiCategoryDTO = new PiiCategoryDTO();
                    piiCategoryDTO.setPiiCatId(purposeDetailsDOList.get(i).getpiiCategoryArr()[j].getPiiCatId());
                    piiCategoryDTO.setPiiCat(purposeDetailsDOList.get(i).getpiiCategoryArr()[j].getPiiCat());
                    piiCategoryDTOList.add(piiCategoryDTO);
                }
                purposeDTO.setPiiCategory(piiCategoryDTOList);
                purposeDTO.setPrimaryPurpose(Integer.valueOf(purposeDetailsDOList.get(i).getPrimaryPurpose()));
                purposeDTO.setTermination(purposeDetailsDOList.get(i).getTermination());
                purposeDTO.setThirdPartyDisclosure(Integer.valueOf(purposeDetailsDOList.get(i).getThirdPartyDis()));
                purposeDTO.setThirdPartyName(purposeDetailsDOList.get(i).getThirdPartyName());
                purposeDTOList.add(purposeDTO);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(purposeDTOList).build();
    }

    @Override
    public Response consentConfigurationPurposePost(PurposeInputDTO purpose) {
        PurposeDetailsDO purposeDetailsDO=new PurposeDetailsDO();
        purposeDetailsDO.setPurpose(purpose.getPurpose());
        purposeDetailsDO.setPrimaryPurpose(String.valueOf(purpose.getPrimaryPurpose()));
        purposeDetailsDO.setTermination(String.valueOf(purpose.getTermination()));
        purposeDetailsDO.setThirdPartyDis(String.valueOf(purpose.getThirdPartyDisclosure()));
        purposeDetailsDO.setThirdPartyId(purpose.getThirdPartyId());
        try {
            getConsentService().setPurpose(purposeDetailsDO);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationPurposePut(PurposeInputDTO purpose) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationServiceGet() {
        List<ServiceWebFormDTO> serviceDTOList = new ArrayList<>();
        try {
            List<ServicesDO> servicesDOList = getConsentService().getServicesForConf();
            for (int i = 0; i < servicesDOList.size(); i++) {
                ServiceWebFormDTO serviceDTO = new ServiceWebFormDTO();
                serviceDTO.setServiceId(servicesDOList.get(i).getServiceId());
                serviceDTO.setServiceName(servicesDOList.get(i).getServiceDescription());

                List<PurposeWebFormDTO> purposeDTOList = new ArrayList<>();
                for (int j = 0; j < servicesDOList.get(i).getPurposeDetailsArr().length; j++) {
                    PurposeWebFormDTO purposeDTO = new PurposeWebFormDTO();
                    purposeDTO.setPurposeId(servicesDOList.get(i).getPurposeDetailsArr()[j].getPurposeId());
                    purposeDTO.setPurposeName(servicesDOList.get(i).getPurposeDetailsArr()[j].getPurpose());
                    purposeDTOList.add(purposeDTO);
                }
                serviceDTO.setPurposes(purposeDTOList);
                serviceDTOList.add(serviceDTO);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(serviceDTOList).build();
    }

    @Override
    public Response consentConfigurationServicePost(ServiceInputDTO service) {
        ServicesDO servicesDO=new ServicesDO();
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
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentReceiptPost(ConsentReceiptDTO userDetails) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentReceiptWebFormPost(UserConsentWebFormDTO userConsentWebForm) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentRevokePut(ConsentRevokeListDTO revokingConsent) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentSubjectNameGet(String subjectName) {
//        This has to be implemented
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

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
        ServiceListDTO serviceListDTO=new ServiceListDTO();
        List<ServiceCRDTO> serviceCRDTOList = new ArrayList<>();
        try {
            List<ServicesDO> servicesDOList = getConsentService().getServicesForUserView(subjectName);
            for (int i = 0; i < servicesDOList.size(); i++) {
                ServiceCRDTO serviceCRDTO = new ServiceCRDTO();
                serviceCRDTO.setServiceId(servicesDOList.get(i).getServiceId());
                serviceCRDTO.setServiceName(servicesDOList.get(i).getServiceDescription());

                List<PurposeDTO> purposeDTOList=new ArrayList<>();
                for (PurposeDetailsDO purpose:servicesDOList.get(i).getPurposeDetailsArr()){
                    PurposeDTO purposeDTO=new PurposeDTO();
                    purposeDTO.setPurposeId(purpose.getPurposeId());
                    purposeDTO.setPurpose(purpose.getPurpose());

                    List<PurposeCategoryDTO> purposeCategoryDTOList=new ArrayList<>();
                    for (PurposeCategoryDO purposeCategoryDO:purpose.getPurposeCategoryDOArr()){
                        PurposeCategoryDTO purposeCategoryDTO=new PurposeCategoryDTO();
                        purposeCategoryDTO.setPurposeCategoryId(purposeCategoryDO.getPurposeCatId());
                        purposeCategoryDTO.setPurposeCategoryShortCode(purposeCategoryDO.getPurposeCatShortCode());
                        purposeCategoryDTOList.add(purposeCategoryDTO);
                    }
                    purposeDTO.setPurposeCategory(purposeCategoryDTOList);
                    purposeDTO.setConsentType(purpose.getConsentType());

                    List<PiiCategoryDTO> piiCategoryDTOList=new ArrayList<>();
                    for(PiiCategoryDO piiCategoryDO:purpose.getpiiCategoryArr()){
                        PiiCategoryDTO piiCategoryDTO=new PiiCategoryDTO();
                        piiCategoryDTO.setPiiCatId(piiCategoryDO.getPiiCatId());
                        piiCategoryDTO.setPiiCat(piiCategoryDO.getPiiCat());
                        piiCategoryDTO.setSensitivity(piiCategoryDO.getSensitivity());
                        piiCategoryDTOList.add(piiCategoryDTO);
                    }
                    purposeDTO.setPiiCategory(piiCategoryDTOList);
                    purposeDTO.setPrimaryPurpose(Integer.valueOf(purpose.getPrimaryPurpose()));
                    purposeDTO.setTermination(purpose.getTermination());
                    purposeDTO.setThirdPartyDisclosure(Integer.valueOf(purpose.getThirdPartyDis()));
                    purposeDTO.setThirdPartyName(purpose.getThirdPartyName());
                    purposeDTOList.add(purposeDTO);
                }
                serviceCRDTO.setPurposes(purposeDTOList);
                serviceCRDTOList.add(serviceCRDTO);
            }
            serviceListDTO.setServiceList(serviceCRDTOList);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(serviceCRDTOList).build();
    }

    @Override
    public Response consentSubjectNameServicesServiceIdGet(String subjectName, Integer serviceId) {
        ServiceCRDTO serviceCRDTO = new ServiceCRDTO();
        try {
            ServicesDO servicesDO = getConsentService().getServiceByUserByServiceId(subjectName, serviceId);
            serviceCRDTO.setServiceId(servicesDO.getServiceId());
            serviceCRDTO.setServiceName(servicesDO.getServiceDescription());
            List<PurposeDTO> purposeDTOList = new ArrayList<>();
            for (int i = 0; i < servicesDO.getPurposeDetailsArr().length; i++) {
                PurposeDTO purposeDTO = new PurposeDTO();
                purposeDTO.setPurposeId(servicesDO.getPurposeDetailsArr()[i].getPurposeId());
                purposeDTO.setPurpose(servicesDO.getPurposeDetailsArr()[i].getPurpose());

                List<PurposeCategoryDTO> purposeCategoryDTOList = new ArrayList<>();
                for (PurposeCategoryDO purposeCategoryDO : servicesDO.getPurposeDetailsArr()[i].getPurposeCategoryDOArr()) {
                    PurposeCategoryDTO purposeCategoryDTO = new PurposeCategoryDTO();
                    purposeCategoryDTO.setPursopeId(purposeCategoryDO.getPurposeId());
                    purposeCategoryDTO.setPurposeCategoryId(purposeCategoryDO.getPurposeCatId());
                    purposeCategoryDTO.setPurposeCategoryShortCode(purposeCategoryDO.getPurposeCatShortCode());
                    purposeCategoryDTOList.add(purposeCategoryDTO);
                }
                purposeDTO.setPurposeCategory(purposeCategoryDTOList);
                purposeDTO.setConsentType(servicesDO.getPurposeDetailsArr()[i].getConsentType());

                List<PiiCategoryDTO> piiCategoryDTOList = new ArrayList<>();
                for (PiiCategoryDO piiCategoryDO : servicesDO.getPurposeDetailsArr()[i].getpiiCategoryArr()) {
                    PiiCategoryDTO piiCategoryDTO = new PiiCategoryDTO();
                    piiCategoryDTO.setPiiCatId(piiCategoryDO.getPiiCatId());
                    piiCategoryDTO.setPiiCat(piiCategoryDO.getPiiCat());
                    piiCategoryDTO.setSensitivity(piiCategoryDO.getSensitivity());
                    piiCategoryDTOList.add(piiCategoryDTO);
                }
                purposeDTO.setPiiCategory(piiCategoryDTOList);
                purposeDTO.setPrimaryPurpose(Integer.valueOf(servicesDO.getPurposeDetailsArr()[i].getPrimaryPurpose()));
                purposeDTO.setTermination(servicesDO.getPurposeDetailsArr()[i].getTermination());
                purposeDTO.setThirdPartyDisclosure(Integer.valueOf(servicesDO.getPurposeDetailsArr()[i].getThirdPartyDis()));
                purposeDTO.setThirdPartyName(servicesDO.getPurposeDetailsArr()[i].getThirdPartyName());
                purposeDTOList.add(purposeDTO);
            }
            serviceCRDTO.setPurposes(purposeDTOList);
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

            purposeDTO.setPurposeId(purposeDetailsDO.getPurposeId());
            purposeDTO.setPurpose(purposeDetailsDO.getPurpose());

            List<PurposeCategoryDTO> purposeCategoryDTOList = new ArrayList<>();
            for (PurposeCategoryDO purposeCategoryDO : purposeDetailsDO.getPurposeCategoryDOArr()) {
                PurposeCategoryDTO purposeCategoryDTO = new PurposeCategoryDTO();
                purposeCategoryDTO.setPursopeId(purposeCategoryDO.getPurposeId());
                purposeCategoryDTO.setPurposeCategoryId(purposeCategoryDO.getPurposeCatId());
                purposeCategoryDTO.setPurposeCategoryShortCode(purposeCategoryDO.getPurposeCatShortCode());
                purposeCategoryDTOList.add(purposeCategoryDTO);
            }
            purposeDTO.setPurposeCategory(purposeCategoryDTOList);
            purposeDTO.setConsentType(purposeDetailsDO.getConsentType());

            List<PiiCategoryDTO> piiCategoryDTOList = new ArrayList<>();
            for (PiiCategoryDO piiCategoryDO : purposeDetailsDO.getpiiCategoryArr()) {
                PiiCategoryDTO piiCategoryDTO = new PiiCategoryDTO();
                piiCategoryDTO.setPiiCatId(piiCategoryDO.getPiiCatId());
                piiCategoryDTO.setPiiCat(piiCategoryDO.getPiiCat());
                piiCategoryDTO.setSensitivity(piiCategoryDO.getSensitivity());
                piiCategoryDTOList.add(piiCategoryDTO);
            }
            purposeDTO.setPiiCategory(piiCategoryDTOList);
            purposeDTO.setPrimaryPurpose(Integer.valueOf(purposeDetailsDO.getPrimaryPurpose()));
            purposeDTO.setTermination(purposeDetailsDO.getTermination());
            purposeDTO.setThirdPartyDisclosure(Integer.valueOf(purposeDetailsDO.getThirdPartyDis()));
            purposeDTO.setThirdPartyName(purposeDetailsDO.getThirdPartyName());
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
            List<ConsentDTO> consentDTOList = new ArrayList<>();
            for (ServicesDO servicesDO : servicesDOList) {
                ConsentDTO consentDTO = new ConsentDTO();
                consentDTO.setServiceId(servicesDO.getServiceId());
                consentDTO.setService(servicesDO.getServiceDescription());

                List<PurposeDTO> purposeDTOList = new ArrayList<>();
                for (PurposeDetailsDO purpose : servicesDO.getPurposeDetailsArr()) {
                    PurposeDTO purposeDTO = new PurposeDTO();
                    purposeDTO.setPurposeId(purpose.getPurposeId());
                    purposeDTO.setPurpose(purpose.getPurpose());

                    List<PurposeCategoryDTO> purposeCategoryDTOList = new ArrayList<>();
                    for (PurposeCategoryDO purposeCategoryDO : purpose.getPurposeCategoryDOArr()) {
                        PurposeCategoryDTO purposeCategoryDTO = new PurposeCategoryDTO();
                        purposeCategoryDTO.setPurposeCategoryId(purposeCategoryDO.getPurposeCatId());
                        purposeCategoryDTO.setPurposeCategoryShortCode(purposeCategoryDO.getPurposeCatShortCode());
                        purposeCategoryDTOList.add(purposeCategoryDTO);
                    }
                    purposeDTO.setPurposeCategory(purposeCategoryDTOList);
                    purposeDTO.setConsentType(purpose.getConsentType());

                    List<PiiCategoryDTO> piiCategoryDTOList = new ArrayList<>();
                    for (PiiCategoryDO piiCategoryDO : purpose.getpiiCategoryArr()) {
                        PiiCategoryDTO piiCategoryDTO = new PiiCategoryDTO();
                        piiCategoryDTO.setPiiCatId(piiCategoryDO.getPiiCatId());
                        piiCategoryDTO.setPiiCat(piiCategoryDO.getPiiCat());
                        piiCategoryDTO.setSensitivity(piiCategoryDO.getSensitivity());
                        piiCategoryDTOList.add(piiCategoryDTO);
                    }
                    purposeDTO.setPiiCategory(piiCategoryDTOList);
                    purposeDTO.setPrimaryPurpose(Integer.valueOf(purpose.getPrimaryPurpose()));
                    purposeDTO.setTermination(purpose.getTermination());
                    purposeDTOList.add(purposeDTO);

                    consent.setThirdPartyId(purpose.getThirdPartyId());
                    consent.setThirdPartyName(purpose.getThirdPartyName());
                }
                consentDTO.setPurposes(purposeDTOList);
                consentDTOList.add(consentDTO);
            }
            consent.setServices(consentDTOList);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return Response.ok().entity(consent).build();
    }
}
