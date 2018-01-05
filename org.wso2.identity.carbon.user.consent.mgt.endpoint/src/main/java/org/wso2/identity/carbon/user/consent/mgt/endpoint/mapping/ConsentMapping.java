package org.wso2.identity.carbon.user.consent.mgt.endpoint.mapping;

import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.AddressCRDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ConsentByThirdPartyDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ConsentDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ConsentRevokeListDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.DataControllerInputDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PiiCategoryDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PurposeCategoryDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PurposeDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PurposeRevokeListDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.PurposeWebFormDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ServiceCRDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ServiceListDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ServiceRevokeListDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.ServiceWebFormDTO;
import org.wso2.identity.carbon.user.consent.mgt.endpoint.dto.UserConsentWebFormDTO;
import org.wso2.identity.carbon.user.consent.mgt.backend.utils.DateTimeUtil;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ConsentDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.DataControllerDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PiiCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeDetailsDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ServicesDO;

import java.util.ArrayList;
import java.util.List;

public class ConsentMapping {
    public static DataControllerDO setConsentConfigurationDataController(DataControllerInputDTO dataControllerInputDTO) {
        DataControllerDO dataControllerDO = new DataControllerDO();
        dataControllerDO.setOrgName(dataControllerInputDTO.getOrg());
        dataControllerDO.setContactName(dataControllerInputDTO.getContact());
        dataControllerDO.setStreet(dataControllerInputDTO.getAddress().getStreetAddress());
        dataControllerDO.setCountry(dataControllerInputDTO.getAddress().getAddressCountry());
        dataControllerDO.setEmail(dataControllerInputDTO.getEmail());
        dataControllerDO.setPhoneNo(dataControllerInputDTO.getPhone());
        dataControllerDO.setPublicKey(dataControllerInputDTO.getPublicKey());
        dataControllerDO.setPolicyUrl(dataControllerInputDTO.getPolicyUrl());
        return dataControllerDO;
    }

    public static List<DataControllerInputDTO> getConsentConfigurationDataController(List<DataControllerDO>
                                                                                       dataControllerList) {
        List<DataControllerInputDTO> dataControllerOutputDTOList=new ArrayList<>();
        for(DataControllerDO dataControllerDO:dataControllerList) {
            DataControllerInputDTO dataControllerInputDTO = new DataControllerInputDTO();
            dataControllerInputDTO.setId(dataControllerDO.getDataControllerId());
            dataControllerInputDTO.setOrg(dataControllerDO.getOrgName());
            dataControllerInputDTO.setContact(dataControllerDO.getContactName());

            AddressCRDTO address = new AddressCRDTO();
            address.setStreetAddress(dataControllerDO.getStreet());
            address.setAddressCountry(dataControllerDO.getCountry());
            dataControllerInputDTO.setAddress(address);
            dataControllerInputDTO.setEmail(dataControllerDO.getEmail());
            dataControllerInputDTO.setPhone(dataControllerDO.getPhoneNo());
            dataControllerInputDTO.setPublicKey(dataControllerDO.getPublicKey());
            dataControllerInputDTO.setPolicyUrl(dataControllerDO.getPolicyUrl());
            dataControllerOutputDTOList.add(dataControllerInputDTO);
        }
        return dataControllerOutputDTOList;
    }

    public static DataControllerInputDTO getConsentConfigurationDataController(DataControllerDO dataController){
        DataControllerInputDTO dataControllerOutputDTO=new DataControllerInputDTO();
        dataControllerOutputDTO.setId(dataController.getDataControllerId());
        dataControllerOutputDTO.setOrg(dataController.getOrgName());
        dataControllerOutputDTO.setContact(dataController.getContactName());

        AddressCRDTO address = new AddressCRDTO();
        address.setStreetAddress(dataController.getStreet());
        address.setAddressCountry(dataController.getCountry());
        dataControllerOutputDTO.setAddress(address);
        dataControllerOutputDTO.setEmail(dataController.getEmail());
        dataControllerOutputDTO.setPhone(dataController.getPhoneNo());
        dataControllerOutputDTO.setPublicKey(dataController.getPublicKey());
        dataControllerOutputDTO.setPolicyUrl(dataController.getPolicyUrl());
        return dataControllerOutputDTO;
    }

    public static List<PiiCategoryDTO> getConsentConfigurationPersonalInfoCategory(List<PiiCategoryDO> piiCategoryDOS) {
        List<PiiCategoryDTO> piiCategoryDTOList = new ArrayList<>();
        for (int i = 0; i < piiCategoryDOS.size(); i++) {
            PiiCategoryDTO piiCategoryDTO = new PiiCategoryDTO();
            piiCategoryDTO.setPiiCatId(piiCategoryDOS.get(i).getPiiCatId());
            piiCategoryDTO.setPiiCat(piiCategoryDOS.get(i).getPiiCat());
            piiCategoryDTO.setDescription(piiCategoryDOS.get(i).getPiiCatDescription());
            piiCategoryDTO.setSensitivity(piiCategoryDOS.get(i).getSensitivity());
            piiCategoryDTOList.add(piiCategoryDTO);
        }
        return piiCategoryDTOList;
    }

    public static PiiCategoryDO setConsentConfigurationPersonalInfoCategory(PiiCategoryDTO piiCategory) {
        PiiCategoryDO piiCategoryDO = new PiiCategoryDO();
        piiCategoryDO.setPiiCat(piiCategory.getPiiCat());
        piiCategoryDO.setPiiCatDescription(piiCategory.getDescription());
        piiCategoryDO.setSensitivity(piiCategory.getSensitivity());
        return piiCategoryDO;
    }

    public static PiiCategoryDTO setPiiCategoryDOToPiiCategoryDTO(PiiCategoryDO piiCategory){
        PiiCategoryDTO piiCategoryDTO=new PiiCategoryDTO();
        piiCategoryDTO.setPiiCatId(piiCategory.getPiiCatId());
        piiCategoryDTO.setPiiCat(piiCategory.getPiiCat());
        piiCategoryDTO.setDescription(piiCategory.getPiiCatDescription());
        piiCategoryDTO.setSensitivity(piiCategory.getSensitivity());
        return piiCategoryDTO;
    }

    public static List<PurposeDTO> getConsentConfigurationPurpose(List<PurposeDetailsDO> purposeDetailsDOList) {
        List<PurposeDTO> purposeDTOList = new ArrayList<>();
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
        return purposeDTOList;
    }

    public static PurposeDetailsDO setPurposeDTOToPurposeDetailsDO(PurposeDTO purpose) {
        PurposeDetailsDO purposeDetailsDO = new PurposeDetailsDO();
        purposeDetailsDO.setPurpose(purpose.getPurpose());
        purposeDetailsDO.setPrimaryPurpose(String.valueOf(purpose.getPrimaryPurpose()));
        purposeDetailsDO.setTermination(String.valueOf(purpose.getTermination()));
        purposeDetailsDO.setThirdPartyDis(String.valueOf(purpose.getThirdPartyDisclosure()));
        purposeDetailsDO.setThirdPartyId(purpose.getThirdPartyId());

        PurposeCategoryDO[] purposeCategoryDOS=new PurposeCategoryDO[purpose.getPurposeCategory().size()];
        int i=0;
        for(PurposeCategoryDTO purposeCategoryDTO:purpose.getPurposeCategory()){
            purposeCategoryDOS[i]=new PurposeCategoryDO();
            purposeCategoryDOS[i].setPurposeCatId(purposeCategoryDTO.getPurposeCategoryId());
            i++;
        }
        purposeDetailsDO.setPurposeCategoryDOArr(purposeCategoryDOS);

        PiiCategoryDO[] piiCategoryDOS=new PiiCategoryDO[purpose.getPiiCategory().size()];
        i=0;
        for(PiiCategoryDTO piiCategoryDTO:purpose.getPiiCategory()){
            piiCategoryDOS[i]=new PiiCategoryDO();
            piiCategoryDOS[i].setPiiCatId(piiCategoryDTO.getPiiCatId());
            i++;
        }
        purposeDetailsDO.setpiiCategoryArr(piiCategoryDOS);
        return purposeDetailsDO;
    }

    public static PurposeDTO setPurposeDetailsDOToPurposeDTO(PurposeDetailsDO purpose){
        PurposeDTO purposeDTO=new PurposeDTO();
        purposeDTO.setPurposeId(purpose.getPurposeId());
        purposeDTO.setPurpose(purpose.getPurpose());
        purposeDTO.setPrimaryPurpose(Integer.valueOf(purpose.getPrimaryPurpose()));
        purposeDTO.setTermination(purpose.getTermination());
        purposeDTO.setThirdPartyDisclosure(Integer.valueOf(purpose.getThirdPartyDis()));
        purposeDTO.setThirdPartyId(purpose.getThirdPartyId());
        purposeDTO.setThirdPartyName(purpose.getThirdPartyName());

        List<PurposeCategoryDTO> purposeCategoryDTOList=new ArrayList<>();
        for(PurposeCategoryDO purposeCategoryDO:purpose.getPurposeCategoryDOArr()){
            PurposeCategoryDTO purposeCategoryDTO=new PurposeCategoryDTO();
            purposeCategoryDTO.setPurposeCategoryId(purposeCategoryDO.getPurposeCatId());
            purposeCategoryDTO.setPurposeCategoryShortCode(purposeCategoryDO.getPurposeCatShortCode());
            purposeCategoryDTOList.add(purposeCategoryDTO);
        }
        purposeDTO.setPurposeCategory(purposeCategoryDTOList);

        List<PiiCategoryDTO> piiCategoryDTOList=new ArrayList<>();
        for(PiiCategoryDO piiCategoryDO:purpose.getpiiCategoryArr()){
            PiiCategoryDTO piiCategoryDTO=new PiiCategoryDTO();
            piiCategoryDTO.setPiiCatId(piiCategoryDO.getPiiCatId());
            piiCategoryDTO.setPiiCat(piiCategoryDO.getPiiCat());
            piiCategoryDTOList.add(piiCategoryDTO);
        }
        purposeDTO.setPiiCategory(piiCategoryDTOList);
        return purposeDTO;
    }

    public static List<ServiceWebFormDTO> setServicesDOListToServiceWebFormDTOList(List<ServicesDO> servicesDOList) {
        List<ServiceWebFormDTO> serviceDTOList = new ArrayList<>();
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
        return serviceDTOList;
    }

    public static ServiceWebFormDTO setServiceDOToServiceWebFormDTO(ServicesDO servicesDO){
        ServiceWebFormDTO serviceDTO=new ServiceWebFormDTO();
        serviceDTO.setServiceId(servicesDO.getServiceId());
        serviceDTO.setServiceName(servicesDO.getServiceDescription());

        List<PurposeWebFormDTO> purposeDTOList=new ArrayList<>();
        for(PurposeDetailsDO purposeDO : servicesDO.getPurposeDetailsArr()){
            PurposeWebFormDTO purposeDTO=new PurposeWebFormDTO();
            purposeDTO.setPurposeId(purposeDO.getPurposeId());
            purposeDTO.setPurposeName(purposeDO.getPurpose());
            purposeDTOList.add(purposeDTO);
        }
        serviceDTO.setPurposes(purposeDTOList);
        return serviceDTO;
    }

    public static ServiceListDTO getConsentSubjectNameServiceList(List<ServicesDO> servicesDOList) {
        ServiceListDTO serviceListDTO = new ServiceListDTO();
        List<ServiceCRDTO> serviceCRDTOList = new ArrayList<>();
        for (int i = 0; i < servicesDOList.size(); i++) {
            ServiceCRDTO serviceCRDTO = new ServiceCRDTO();
            serviceCRDTO.setServiceId(servicesDOList.get(i).getServiceId());
            serviceCRDTO.setServiceName(servicesDOList.get(i).getServiceDescription());

            List<PurposeDTO> purposeDTOList = new ArrayList<>();
            for (PurposeDetailsDO purpose : servicesDOList.get(i).getPurposeDetailsArr()) {
                PurposeDTO purposeDTO = new PurposeDTO();
                purposeDTO.setPurposeId(purpose.getPurposeId());
                purposeDTO.setPurpose(purpose.getPurpose());

                List<PurposeCategoryDTO> purposeCategoryDTOList = new ArrayList<>();
                for (PurposeCategoryDO purposeCategoryDO : purpose.getPurposeCategoryDOArr()) {
                    purposeCategoryDTOList.add(setPurposeCategoryDTO(purposeCategoryDO));
                }
                purposeDTO.setPurposeCategory(purposeCategoryDTOList);
                purposeDTO.setConsentType(purpose.getConsentType());

                List<PiiCategoryDTO> piiCategoryDTOList = new ArrayList<>();
                for (PiiCategoryDO piiCategoryDO : purpose.getpiiCategoryArr()) {
                    piiCategoryDTOList.add(setPiiCategoryDTO(piiCategoryDO));
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
        return serviceListDTO;
    }

    public static ServiceCRDTO getConsentSubjectNameServicesServiceId(ServicesDO servicesDO) {
        ServiceCRDTO serviceCRDTO = new ServiceCRDTO();
        serviceCRDTO.setServiceId(servicesDO.getServiceId());
        serviceCRDTO.setServiceName(servicesDO.getServiceDescription());
        List<PurposeDTO> purposeDTOList = new ArrayList<>();
        for (int i = 0; i < servicesDO.getPurposeDetailsArr().length; i++) {
            PurposeDTO purposeDTO = new PurposeDTO();
            purposeDTO.setPurposeId(servicesDO.getPurposeDetailsArr()[i].getPurposeId());
            purposeDTO.setPurpose(servicesDO.getPurposeDetailsArr()[i].getPurpose());

            List<PurposeCategoryDTO> purposeCategoryDTOList = new ArrayList<>();
            for (PurposeCategoryDO purposeCategoryDO : servicesDO.getPurposeDetailsArr()[i].getPurposeCategoryDOArr()) {
                purposeCategoryDTOList.add(setPurposeCategoryDTO(purposeCategoryDO));
            }
            purposeDTO.setPurposeCategory(purposeCategoryDTOList);
            purposeDTO.setConsentType(servicesDO.getPurposeDetailsArr()[i].getConsentType());

            List<PiiCategoryDTO> piiCategoryDTOList = new ArrayList<>();
            for (PiiCategoryDO piiCategoryDO : servicesDO.getPurposeDetailsArr()[i].getpiiCategoryArr()) {
                piiCategoryDTOList.add(setPiiCategoryDTO(piiCategoryDO));
            }
            purposeDTO.setPiiCategory(piiCategoryDTOList);
            purposeDTO.setPrimaryPurpose(Integer.valueOf(servicesDO.getPurposeDetailsArr()[i].getPrimaryPurpose()));
            purposeDTO.setTermination(servicesDO.getPurposeDetailsArr()[i].getTermination());
            purposeDTO.setThirdPartyDisclosure(Integer.valueOf(servicesDO.getPurposeDetailsArr()[i].getThirdPartyDis()));
            purposeDTO.setThirdPartyName(servicesDO.getPurposeDetailsArr()[i].getThirdPartyName());
            purposeDTOList.add(purposeDTO);
        }
        serviceCRDTO.setPurposes(purposeDTOList);
        return serviceCRDTO;
    }

    public static PurposeDTO getConsentSubjectNameServicesServiceIdPurpose(PurposeDetailsDO purposeDetailsDO) {
        PurposeDTO purposeDTO = new PurposeDTO();
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
            piiCategoryDTOList.add(setPiiCategoryDTO(piiCategoryDO));
        }
        purposeDTO.setPiiCategory(piiCategoryDTOList);
        purposeDTO.setPrimaryPurpose(Integer.valueOf(purposeDetailsDO.getPrimaryPurpose()));
        purposeDTO.setTermination(purposeDetailsDO.getTermination());
        purposeDTO.setThirdPartyDisclosure(Integer.valueOf(purposeDetailsDO.getThirdPartyDis()));
        purposeDTO.setThirdPartyName(purposeDetailsDO.getThirdPartyName());
        return purposeDTO;
    }

    public static ConsentByThirdPartyDTO getConsentSubjectNameThirdParty(List<ServicesDO> servicesDOList) {
        ConsentByThirdPartyDTO consent = new ConsentByThirdPartyDTO();
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
        return consent;
    }

    private static PiiCategoryDTO setPiiCategoryDTO(PiiCategoryDO piiCategoryDO) {
        PiiCategoryDTO piiCategoryDTO = new PiiCategoryDTO();
        piiCategoryDTO.setPiiCatId(piiCategoryDO.getPiiCatId());
        piiCategoryDTO.setPiiCat(piiCategoryDO.getPiiCat());
        piiCategoryDTO.setSensitivity(piiCategoryDO.getSensitivity());
        return piiCategoryDTO;
    }

    private static PurposeCategoryDTO setPurposeCategoryDTO(PurposeCategoryDO purposeCategoryDO) {
        PurposeCategoryDTO purposeCategoryDTO = new PurposeCategoryDTO();
        purposeCategoryDTO.setPurposeCategoryId(purposeCategoryDO.getPurposeCatId());
        purposeCategoryDTO.setPurposeCategoryShortCode(purposeCategoryDO.getPurposeCatShortCode());
        return purposeCategoryDTO;
    }

    public static ConsentDO setUserAndDataController(UserConsentWebFormDTO userConsentWebForm){
        ConsentDO consentDO=new ConsentDO();
        consentDO.setCollectionMethod(userConsentWebForm.getCollectionMethod());
        consentDO.setSGUID(userConsentWebForm.getSguid());
        consentDO.setPiiPrincipalId(userConsentWebForm.getSubjectName());
        consentDO.setConsentTimestamp("No Timestamp");
        DataControllerDO dataControllerDO=new DataControllerDO();
        dataControllerDO.setDataControllerId(userConsentWebForm.getDataControllerId());
        consentDO.setDataController(dataControllerDO);
        return consentDO;
    }

    public static ServicesDO[] setUserConsents(UserConsentWebFormDTO userConsentWebFormDTO){
        List<ServicesDO> servicesDOList=new ArrayList<>();
        for(ServiceWebFormDTO serviceWebFormDTO:userConsentWebFormDTO.getServices()){
            ServicesDO servicesDO=new ServicesDO();
            servicesDO.setServiceId(serviceWebFormDTO.getServiceId());
            servicesDO.setServiceDescription(serviceWebFormDTO.getServiceName());

            List<PurposeDetailsDO> purposeDetailsDOList=new ArrayList<>();
            for(PurposeWebFormDTO purposeWebFormDTO:serviceWebFormDTO.getPurposes()){
                PurposeDetailsDO purposeDetailsDO=new PurposeDetailsDO();
                purposeDetailsDO.setPurposeId(purposeWebFormDTO.getPurposeId());
                purposeDetailsDO.setPurpose(purposeWebFormDTO.getPurposeName());
                purposeDetailsDO.setTermination(DateTimeUtil.getCurrentDateTime());
                purposeDetailsDO.setCollectionMethod("web");
                purposeDetailsDOList.add(purposeDetailsDO);
            }
            servicesDO.setPurposeDetails(purposeDetailsDOList.toArray(new PurposeDetailsDO[0]));
            servicesDOList.add(servicesDO);
        }
        return servicesDOList.toArray(new ServicesDO[0]);
    }

    public static DataControllerDO updateDataController(DataControllerInputDTO dataController){
        DataControllerDO dataControllerDO=new DataControllerDO();
        dataControllerDO.setDataControllerId(dataController.getId());
        dataControllerDO.setOrgName(dataController.getOrg());
        dataControllerDO.setContactName(dataController.getContact());
        dataControllerDO.setStreet(dataController.getAddress().getStreetAddress());
        dataControllerDO.setCountry(dataController.getAddress().getAddressCountry());
        dataControllerDO.setEmail(dataController.getEmail());
        dataControllerDO.setPhoneNo(dataController.getPhone());
        dataControllerDO.setPublicKey(dataController.getPublicKey());
        dataControllerDO.setPolicyUrl(dataController.getPolicyUrl());
        return dataControllerDO;
    }

    public static PiiCategoryDO updatePersonallyIdentifiableInfoCat(PiiCategoryDTO piiCategoryDTO){
        PiiCategoryDO piiCategoryDO=new PiiCategoryDO();
        piiCategoryDO.setPiiCatId(piiCategoryDTO.getPiiCatId());
        piiCategoryDO.setPiiCat(piiCategoryDTO.getPiiCat());
        piiCategoryDO.setPiiCatDescription(piiCategoryDTO.getDescription());
        piiCategoryDO.setSensitivity(piiCategoryDTO.getSensitivity());
        return piiCategoryDO;
    }

    public static PurposeDetailsDO updatePurpose(PurposeDTO purposeDTO){
        PurposeDetailsDO purpose=new PurposeDetailsDO();
        purpose.setPurposeId(purposeDTO.getPurposeId());
        purpose.setPurpose(purposeDTO.getPurpose());
        purpose.setPrimaryPurpose(String.valueOf(purposeDTO.getPrimaryPurpose()));
        purpose.setTermination(String.valueOf(purposeDTO.getTermination()));
        purpose.setThirdPartyDis(String.valueOf(purposeDTO.getThirdPartyDisclosure()));
        purpose.setThirdPartyId(purposeDTO.getThirdPartyId());
        return purpose;
    }

    public static List<ServicesDO> revokeConsent(ConsentRevokeListDTO revokingConsent){
        List<ServicesDO> servicesList=new ArrayList<>();
        for(ServiceRevokeListDTO serviceRevokeDTO:revokingConsent.getServices()){
            ServicesDO services=new ServicesDO();
            services.setServiceId(serviceRevokeDTO.getServiceId());

            List<PurposeDetailsDO> purposeList=new ArrayList<>();
            for(PurposeRevokeListDTO purposeRevokeDTO:serviceRevokeDTO.getPurposes()){
                PurposeDetailsDO purpose=new PurposeDetailsDO();
                purpose.setPurposeId(purposeRevokeDTO.getPurposeId());
                purposeList.add(purpose);
            }
            services.setPurposeDetails(purposeList.toArray(new PurposeDetailsDO[0]));
            servicesList.add(services);
        }
        return servicesList;
    }

    public static List<PurposeCategoryDTO> getPurposeCategories(List<PurposeCategoryDO> purposeCategoryList){
        List<PurposeCategoryDTO> purposeCategoryDTOList=new ArrayList<>();
        for(PurposeCategoryDO purposeCategory:purposeCategoryList){
            PurposeCategoryDTO purposeCategoryDTO=new PurposeCategoryDTO();
            purposeCategoryDTO.setPurposeCategoryId(purposeCategory.getPurposeCatId());
            purposeCategoryDTO.setPurposeCategoryShortCode(purposeCategory.getPurposeCatShortCode());
            purposeCategoryDTO.setDescription(purposeCategory.getPurposeCatDes());
            purposeCategoryDTOList.add(purposeCategoryDTO);
        }
        return purposeCategoryDTOList;
    }

    public static PurposeCategoryDO setPurposeCategory(PurposeCategoryDTO purposeCategoryDTO){
        PurposeCategoryDO purposeCategory =new PurposeCategoryDO();
        purposeCategory.setPurposeCatShortCode(purposeCategoryDTO.getPurposeCategoryShortCode());
        purposeCategory.setPurposeCatDes(purposeCategoryDTO.getDescription());
        return purposeCategory;
    }

    public static PurposeCategoryDO updatePurposeCategory(PurposeCategoryDTO purposeCategoryDTO){
        PurposeCategoryDO purposeCategory=new PurposeCategoryDO();
        purposeCategory.setPurposeCatId(purposeCategoryDTO.getPurposeCategoryId());
        purposeCategory.setPurposeCatShortCode(purposeCategoryDTO.getPurposeCategoryShortCode());
        purposeCategory.setPurposeCatDes(purposeCategoryDTO.getDescription());
        return purposeCategory;
    }

    public static PurposeCategoryDTO setPurposeCategoryDOToPurposeCategoryDTO(PurposeCategoryDO purposeCategoryDO){
        PurposeCategoryDTO purposeCategoryDTO=new PurposeCategoryDTO();
        purposeCategoryDTO.setPurposeCategoryId(purposeCategoryDO.getPurposeCatId());
        purposeCategoryDTO.setPurposeCategoryShortCode(purposeCategoryDO.getPurposeCatShortCode());
        purposeCategoryDTO.setDescription(purposeCategoryDO.getPurposeCatDes());
        return purposeCategoryDTO;
    }
}
