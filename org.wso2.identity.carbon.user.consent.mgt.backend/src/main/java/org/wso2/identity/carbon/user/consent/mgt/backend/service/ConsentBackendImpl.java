package org.wso2.identity.carbon.user.consent.mgt.backend.service;

import org.json.simple.JSONObject;
import org.wso2.identity.carbon.user.consent.mgt.backend.DAO.ConsentDao;
import org.wso2.identity.carbon.user.consent.mgt.backend.JSONParserLayer.JSONParser;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ConsentDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.DataControllerDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PiiCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeDetailsDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ServicesDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ThirdPartyDO;

import java.text.ParseException;
import java.util.List;

public class ConsentBackendImpl implements ConsentBackend {
    ConsentDao consentDao=new ConsentDao();

    @Override
    public JSONObject getCreatedConsentReceipt(String subjectName) throws DataAccessException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject consentReceiptObject = jsonParser.createConsentReceipt(subjectName);
        return consentReceiptObject;
    }

    @Override
    public List<ServicesDO> getServicesForUserView(String subjectName) throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        List<ServicesDO> servicesDOList = consentDao.getServicesForUserView(subjectName);
        return servicesDOList;
    }

    @Override
    public ServicesDO getServiceByUserByServiceId(String subjectName, int serviceId) throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        ServicesDO servicesDO = consentDao.getServiceByUserByServiceIdDemo(subjectName, serviceId);
        return servicesDO;
    }

    @Override
    public PurposeDetailsDO getPurposeByUserByServiceByPurposeId(String subjectName, int serviceId, int purposeId) throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        PurposeDetailsDO purposeDetailsDO = consentDao.getPurposeByUserByService(subjectName, serviceId, purposeId);
        return purposeDetailsDO;
    }

    @Override
    public List<ServicesDO> getServicesByUserByThirdParty(String subjectName, int thirdPartyId) throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        List<ServicesDO> servicesDOList = consentDao.getServiceDetailsByThirdParty(subjectName, thirdPartyId);
        return servicesDOList;
    }

    //-- Data Controller Configurations
    @Override
    public void setDataController(DataControllerDO dataControllerDO) throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        consentDao.addDataController(dataControllerDO);
//        consentDao.getDataController(1)
    }

    @Override
    public DataControllerDO getDataController(int dataControllerId) throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        DataControllerDO dataControllerDO = consentDao.getDataController(dataControllerId);
        return dataControllerDO;
    }

    @Override
    public void updateDataController(DataControllerDO dataControllerDO) throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        consentDao.updateDataController(dataControllerDO);
    }

    @Override
    public void deleteDataController(int dataControllerId) throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        consentDao.deleteDataController(dataControllerId);
    }

    //-- Personally Identifiable Info Category Configuration
    @Override
    public void setPersonalInfoCat(PiiCategoryDO piiCategoryDO) throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        consentDao.addPiiCategory(piiCategoryDO);
    }

    @Override
    public List<PiiCategoryDO> getPersonalIdentifyInfoCat() throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        List<PiiCategoryDO> piiCategoryDOList = consentDao.getPersonalInfoCatForConfig();
        return piiCategoryDOList;
    }

    @Override
    public void updatePersonallyIdentifiableInfoCat(PiiCategoryDO piiCategoryDO) throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        consentDao.updatePersonallyIdentifiableInfoCat(piiCategoryDO);
    }

    @Override
    public PiiCategoryDO deletePersonalInfoCat(int categoryId) throws DataAccessException {
        PiiCategoryDO piiCategory=consentDao.deletePersonalInfoCat(categoryId);
        return piiCategory;
    }

    //- Purpose Configuration
    @Override
    public void setPurpose(PurposeDetailsDO purpose) throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        consentDao.addPurposeDetails(purpose);
    }

    @Override
    public List<PurposeDetailsDO> getPurposeDetailsForConf() throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        List<PurposeDetailsDO> purposeDetailsDOList = consentDao.getPurposesForConfig();
        return purposeDetailsDOList;
    }

    @Override
    public void updatePurpose(PurposeDetailsDO purpose) throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        consentDao.updatePurposeDetails(purpose);
    }

    @Override
    public PurposeDetailsDO deletePurpose(int purposeId) throws DataAccessException {
        PurposeDetailsDO purpose=consentDao.deletePurpose(purposeId);
        return purpose;
    }

    //-- Service Configuration
    @Override
    public void setService(ServicesDO service) throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        consentDao.addServiceDetails(service);
    }

    @Override
    public List<ServicesDO> getServicesForConf() throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        List<ServicesDO> servicesDOList = consentDao.getServicesForConf();
        return servicesDOList;
    }

    @Override
    public void updateService(ServicesDO service) throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        consentDao.updateServiceDetails(service);
    }

    @Override
    public ServicesDO deleteService(int serviceId) throws DataAccessException {
        ServicesDO service=consentDao.deleteService(serviceId);
        return service;
    }

    //-- Purpose Category Configuration
    @Override
    public List<PurposeCategoryDO> getPurposeCategories() throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        List<PurposeCategoryDO> purposeCategoryList=consentDao.getPurposeCategories();
        return purposeCategoryList;
    }

    @Override
    public void setPurposeCategory(PurposeCategoryDO purposeCategory) throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        consentDao.addPurposeCategory(purposeCategory);
    }

    @Override
    public void updatePurposeCategory(PurposeCategoryDO purposeCategory) throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        consentDao.updatePurposeCategory(purposeCategory);
    }

    @Override
    public PurposeCategoryDO deletePurposeCategory(int categoryId) throws DataAccessException {
        PurposeCategoryDO purposeCategory=consentDao.deletePurposeCategory(categoryId);
        return purposeCategory;
    }

    //-- Third Party Configuration
    @Override
    public List<ThirdPartyDO> getThirdParties() throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        List<ThirdPartyDO> thirdPartyList=consentDao.getThirdPartyDetailsForConf();
        return thirdPartyList;
    }

    @Override
    public void setThirdParty(ThirdPartyDO thirdParty) throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        consentDao.addThirdParty(thirdParty);
    }

    @Override
    public void updateThirdParty(ThirdPartyDO thirdParty) throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        consentDao.updateThirdParty(thirdParty);
    }

    @Override
    public ThirdPartyDO deleteThirdParty(int thirdPartyId) throws DataAccessException {
        ThirdPartyDO thirdParty=consentDao.deleteThirdParty(thirdPartyId);
        return thirdParty;
    }

    @Override
    public ConsentDO getSubjectName(String subjectName) throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        ConsentDO consent = consentDao.getUserNameFromSGUID(subjectName);
        return consent;
    }

    @Override
    public void setConsentDetailsForUser(ConsentDO consentDO,ServicesDO[] services) throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        consentDao.addUserAndDataControllerDetails(consentDO);
        consentDao.addUserConsentDetails(consentDO,services);
    }

    @Override
    public void revokeConsent(String subjectName, List<ServicesDO> servicesList) throws DataAccessException {
//        Complete the impl
    }
}
