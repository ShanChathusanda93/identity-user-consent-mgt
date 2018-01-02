package org.wso2.identity.carbon.user.consent.mgt.backend.service;

import org.json.simple.JSONObject;
import org.wso2.identity.carbon.user.consent.mgt.backend.DAO.ConsentDao;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ConsentDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.DataControllerDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PiiCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeDetailsDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ServicesDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ThirdPartyDO;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.util.List;

public interface ConsentBackend {
    public JSONObject getCreatedConsentReceipt(String subjectName) throws DataAccessException, ParseException;
    public List<ServicesDO> getServicesForUserView(String subjectName) throws DataAccessException;
    public ServicesDO getServiceByUserByServiceId(String subjectName,int serviceId) throws DataAccessException;
    public PurposeDetailsDO getPurposeByUserByServiceByPurposeId(String subjectName,int serviceId,int purposeId)
            throws DataAccessException;
    public List<ServicesDO> getServicesByUserByThirdParty(String subjectName,int thirdPartyId) throws
            DataAccessException;
    public ConsentDO getSubjectName(String subjectName) throws DataAccessException;
    public void setConsentDetailsForUser(ConsentDO consentDO,ServicesDO[] services) throws DataAccessException;
    public void revokeConsent(String subjectName,List<ServicesDO> servicesList) throws DataAccessException;
    public void setDataController(DataControllerDO dataControllerDO) throws DataAccessException;
    public DataControllerDO getDataController(String organizationName) throws DataAccessException;
    public void updateDataController(DataControllerDO dataControllerDO) throws DataAccessException;
    public void deleteDataController(int dataControllerId) throws DataAccessException;
    public void setPersonalInfoCat(PiiCategoryDO piiCategoryDO) throws DataAccessException;
    public List<PiiCategoryDO> getPersonalIdentifyInfoCat() throws DataAccessException;
    public void updatePersonallyIdentifiableInfoCat(PiiCategoryDO piiCategoryDO) throws DataAccessException;
    public PiiCategoryDO deletePersonalInfoCat(int categoryId) throws DataAccessException;
    public void setPurpose(PurposeDetailsDO purpose) throws DataAccessException;
    public List<PurposeDetailsDO> getPurposeDetailsForConf() throws DataAccessException;
    public void updatePurpose(PurposeDetailsDO purpose) throws DataAccessException;
    public PurposeDetailsDO deletePurpose(int purposeId) throws DataAccessException;
    public void setService(ServicesDO service) throws DataAccessException;
    public List<ServicesDO> getServicesForConf() throws DataAccessException;
    public void updateService(ServicesDO services) throws DataAccessException;
    public ServicesDO deleteService(int serviceId) throws DataAccessException;
    public void setPurposeCategory(PurposeCategoryDO purposeCategory) throws DataAccessException;
    public List<PurposeCategoryDO> getPurposeCategories() throws DataAccessException;
    public void updatePurposeCategory(PurposeCategoryDO purposeCategory) throws DataAccessException;
    public PurposeCategoryDO deletePurposeCategory(int categoryId) throws DataAccessException;
    public void setThirdParty(ThirdPartyDO thirdParty) throws DataAccessException;
    public List<ThirdPartyDO> getThirdParties() throws DataAccessException;
    public void updateThirdParty(ThirdPartyDO thirdParty) throws DataAccessException;
    public ThirdPartyDO deleteThirdParty(int thirdPartyId) throws DataAccessException;
}
