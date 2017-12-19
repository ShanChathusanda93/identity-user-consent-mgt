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
    @Override
    public JSONObject getCreatedConsentReceipt(String subjectName) throws DataAccessException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject consentReceiptObject = jsonParser.createConsentReceipt(subjectName);
        return consentReceiptObject;
    }

    @Override
    public List<PiiCategoryDO> getPersonalIdentifyInfoCat() throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        List<PiiCategoryDO> piiCategoryDOList = consentDao.getPersonalInfoCatForConfig();
        return piiCategoryDOList;
    }

    @Override
    public List<PurposeDetailsDO> getPurposeDetailsForConf() throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        List<PurposeDetailsDO> purposeDetailsDOList = consentDao.getPurposesForConfig();
        return purposeDetailsDOList;
    }

    @Override
    public List<ServicesDO> getServicesForConf() throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        List<ServicesDO> servicesDOList = consentDao.getServicesForConf();
        return servicesDOList;
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
    public void setPersonalInfoCat(PiiCategoryDO piiCategoryDO) throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        consentDao.addPiiCategory(piiCategoryDO);
    }

    @Override
    public void setPurpose(PurposeDetailsDO purpose) throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        consentDao.addPurposeDetails(purpose);
    }

    @Override
    public void setService(ServicesDO service) throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        consentDao.addServiceDetails(service);
    }

    @Override
    public String getSubjectName(String subjectName) throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        String subject = consentDao.getUserNameFromSGUID(subjectName);
        return subject;
    }

    @Override
    public void setConsentDetailsForUser(ConsentDO consentDO,ServicesDO[] services) throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        consentDao.addUserAndDataControllerDetails(consentDO);
        consentDao.addUserConsentDetails(consentDO,services);
    }

    @Override
    public void updateDataController(DataControllerDO dataControllerDO) throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        consentDao.updateDataController(dataControllerDO);
    }

    @Override
    public void updatePersonallyIdentifiableInfoCat(PiiCategoryDO piiCategoryDO) throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        consentDao.updatePersonallyIdentifiableInfoCat(piiCategoryDO);
    }

    @Override
    public void updatePurpose(PurposeDetailsDO purpose) throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        consentDao.updatePurposeDetails(purpose);
    }

    @Override
    public void updateService(ServicesDO service) throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        consentDao.updateServiceDetails(service);
    }

    @Override
    public void revokeConsent(String subjectName, List<ServicesDO> servicesList) throws DataAccessException {
//        Complete the impl
    }

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
}
