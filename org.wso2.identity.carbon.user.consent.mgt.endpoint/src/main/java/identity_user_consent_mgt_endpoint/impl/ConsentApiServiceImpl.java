package identity_user_consent_mgt_endpoint.impl;

import identity_user_consent_mgt_endpoint.*;


import identity_user_consent_mgt_endpoint.dto.DataControllerInputDTO;
import identity_user_consent_mgt_endpoint.dto.PiiCategoryDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeCategoryDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeInputDTO;
import identity_user_consent_mgt_endpoint.dto.PurposeListDTO;
import identity_user_consent_mgt_endpoint.dto.ServiceInputDTO;
import identity_user_consent_mgt_endpoint.dto.ConsentReceiptDTO;
import identity_user_consent_mgt_endpoint.dto.UserConsentWebFormDTO;
import identity_user_consent_mgt_endpoint.dto.ConsentRevokeListDTO;
import org.json.simple.JSONObject;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PiiCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeDetailsDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.service.ConsentBackend;

import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsentApiServiceImpl extends ConsentApiService {
    private static ConsentBackend getConsentService() {

        return (ConsentBackend) PrivilegedCarbonContext.getThreadLocalCarbonContext()
                .getOSGiService(ConsentBackend.class, null);
    }

    @Override
    public Response consentConfigurationDataControllerPost(DataControllerInputDTO dataController) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationPersonalInfoCategoryGet() {
        List<PiiCategoryDTO> piiCategoryDTOList=new ArrayList<>();
        try {
            List<PiiCategoryDO> piiCategoryDOList=getConsentService().getPersonalIdentifyInfoCat();
            for(int i=0;i<piiCategoryDOList.size();i++){
                PiiCategoryDTO piiCategoryDTO=new PiiCategoryDTO();
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
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationPersonalInfoCategoryPut(PiiCategoryDTO piiCategory) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationPurposeGet() {
        List<PurposeDTO> purposeDTOList=new ArrayList<>();
        try {
            List<PurposeDetailsDO> purposeDetailsDOList=getConsentService().getPurposeDetailsForConf();
            for(int i=0;i<purposeDetailsDOList.size();i++){
                PurposeDTO purposeDTO=new PurposeDTO();
                purposeDTO.setPurposeId(purposeDetailsDOList.get(i).getPurposeId());
                purposeDTO.setPurpose(purposeDetailsDOList.get(i).getPurpose());

                List<PurposeCategoryDTO> purposeCategoryDTOList=new ArrayList<>();
                for(int j=0;j<purposeDetailsDOList.get(i).getPurposeCategoryDOArr().length;j++){
                    PurposeCategoryDTO purposeCategoryDTO=new PurposeCategoryDTO();
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
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationPurposePut(PurposeInputDTO purpose) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationServiceGet(ServiceInputDTO service) {
//        ConsentBackendImpl consentBackendImpl = new ConsentBackendImpl();
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentConfigurationServicePost(ServiceInputDTO service) {
        // do some magic!
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
        // do some magic!
//        ConsentBackendImpl consentBackendImpl = new ConsentBackendImpl();
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
    public Response consentSubjectNameServicesGet(String subjectName) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentSubjectNameServicesServiceIdGet(String subjectName, Integer serviceId) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentSubjectNameServicesServiceIdPurposeGet(String subjectName, Integer serviceId, Integer purposeId) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }

    @Override
    public Response consentSubjectNameThirdPartyGet(String subjectName, Integer thirdPartyId) {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
