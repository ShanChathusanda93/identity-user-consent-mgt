package org.wso2.identity.carbon.user.consent.mgt.backend.service;

import org.json.simple.JSONObject;
import org.wso2.identity.carbon.user.consent.mgt.backend.dao.ConsentDao;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;
import org.wso2.identity.carbon.user.consent.mgt.backend.jsonParser.JSONParser;
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
    ConsentDao consentDao = new ConsentDao();

    @Override
    public JSONObject getCreatedConsentReceipt(String subjectName) throws DataAccessException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject consentReceiptObject = jsonParser.createConsentReceipt(subjectName);
        return consentReceiptObject;
    }

    @Override
    public List<ServicesDO> getServicesForUserView(String subjectName) throws DataAccessException {
        List<ServicesDO> servicesDOList = consentDao.getServicesForUserView(subjectName);
        return servicesDOList;
    }

    @Override
    public ServicesDO getServiceByUserByServiceId(String subjectName, int serviceId) throws DataAccessException {
        ServicesDO servicesDO = consentDao.getServiceByUserByServiceIdDemo(subjectName, serviceId);
        return servicesDO;
    }

    @Override
    public PurposeDetailsDO getPurposeByUserByServiceByPurposeId(String subjectName, int serviceId, int purposeId) throws DataAccessException {
        PurposeDetailsDO purposeDetailsDO = consentDao.getPurposeByUserByService(subjectName, serviceId, purposeId);
        return purposeDetailsDO;
    }

    @Override
    public List<ServicesDO> getServicesByUserByThirdParty(String subjectName, int thirdPartyId) throws DataAccessException {
        List<ServicesDO> servicesDOList = consentDao.getServiceDetailsByThirdParty(subjectName, thirdPartyId);
        return servicesDOList;
    }

    //-- Data Controller Configurations
    @Override
    public DataControllerDO setDataController(DataControllerDO dataControllerDO) throws DataAccessException {
        DataControllerDO addedDataControllerDO = consentDao.addDataController(dataControllerDO);
        return addedDataControllerDO;
    }

    @Override
    public List<DataControllerDO> getDataControllerList() throws DataAccessException {
        List<DataControllerDO> dataControllerList = consentDao.getDataControllerList();
        return dataControllerList;
    }

    @Override
    public DataControllerDO getDataControllerById(int id) throws DataAccessException {
        DataControllerDO dataController = consentDao.getDataController(id);
        return dataController;
    }

    @Override
    public DataControllerDO updateDataController(DataControllerDO dataControllerDO) throws DataAccessException {
        DataControllerDO updatedDataControllerDO = consentDao.updateDataController(dataControllerDO);
        return updatedDataControllerDO;
    }

    @Override
    public DataControllerDO deleteDataController(int dataControllerId) throws DataAccessException {
        DataControllerDO deletedDataControllerDO = consentDao.deleteDataController(dataControllerId);
        return deletedDataControllerDO;
    }

    //-- Personally Identifiable Info Category Configuration
    @Override
    public PiiCategoryDO setPersonalInfoCat(PiiCategoryDO piiCategoryDO) throws DataAccessException {
        PiiCategoryDO piiCategory = consentDao.addPiiCategory(piiCategoryDO);
        return piiCategory;
    }

    @Override
    public List<PiiCategoryDO> getPersonalIdentifyInfoCat() throws DataAccessException {
        List<PiiCategoryDO> piiCategoryDOList = consentDao.getPersonalInfoCatForConfig();
        return piiCategoryDOList;
    }

    @Override
    public PiiCategoryDO updatePersonalInfoCat(PiiCategoryDO piiCategoryDO) throws DataAccessException {
        PiiCategoryDO piiCategory = consentDao.updatePersonalInfoCat(piiCategoryDO);
        return piiCategory;
    }

    @Override
    public PiiCategoryDO deletePersonalInfoCat(int categoryId) throws DataAccessException {
        PiiCategoryDO piiCategory = consentDao.deletePersonalInfoCat(categoryId);
        return piiCategory;
    }

    @Override
    public PiiCategoryDO getPersonalInfoCatById(int categoryId) throws DataAccessException {
        PiiCategoryDO piiCategory = consentDao.getPersonalInfoCatById(categoryId);
        return piiCategory;
    }

    //- Purpose Configuration
    @Override
    public PurposeDetailsDO setPurpose(PurposeDetailsDO purpose) throws DataAccessException {
        PurposeDetailsDO purposeDetails = consentDao.addPurposeDetails(purpose);
        return purposeDetails;
    }

    @Override
    public List<PurposeDetailsDO> getPurposeDetailsForConf() throws DataAccessException {
        List<PurposeDetailsDO> purposeDetailsDOList = consentDao.getPurposesForConfig();
        return purposeDetailsDOList;
    }

    @Override
    public PurposeDetailsDO updatePurpose(PurposeDetailsDO purpose) throws DataAccessException {
        PurposeDetailsDO purposeDetailsDO = consentDao.updatePurposeDetails(purpose);
        return purposeDetailsDO;
    }

    @Override
    public PurposeDetailsDO deletePurpose(int purposeId) throws DataAccessException {
        PurposeDetailsDO purpose = consentDao.deletePurpose(purposeId);
        return purpose;
    }

    @Override
    public PurposeDetailsDO getPurposeDetailsById(int id) throws DataAccessException {
        PurposeDetailsDO purpose = consentDao.getPurposeDetailsById(id);
        return purpose;
    }

    //-- Service Configuration
    @Override
    public ServicesDO setService(ServicesDO service) throws DataAccessException {
        ServicesDO servicesDO = consentDao.addServiceDetails(service);
        return servicesDO;
    }

    @Override
    public List<ServicesDO> getServicesForConf() throws DataAccessException {
        List<ServicesDO> servicesDOList = consentDao.getServicesForConf();
        return servicesDOList;
    }

    @Override
    public ServicesDO updateService(ServicesDO service) throws DataAccessException {
        ServicesDO servicesDO = consentDao.updateServiceDetails(service);
        return servicesDO;
    }

    @Override
    public ServicesDO deleteService(int serviceId) throws DataAccessException {
        ServicesDO service = consentDao.deleteService(serviceId);
        return service;
    }

    @Override
    public ServicesDO getServiceById(int id) throws DataAccessException {
        ServicesDO servicesDO = consentDao.getServiceById(id);
        return servicesDO;
    }

    //-- Purpose Category Configuration
    @Override
    public List<PurposeCategoryDO> getPurposeCategories() throws DataAccessException {
        List<PurposeCategoryDO> purposeCategoryList = consentDao.getPurposeCategories();
        return purposeCategoryList;
    }

    @Override
    public PurposeCategoryDO setPurposeCategory(PurposeCategoryDO purposeCategory) throws DataAccessException {
        PurposeCategoryDO addedPurposeCatDO = consentDao.addPurposeCategory(purposeCategory);
        return addedPurposeCatDO;
    }

    @Override
    public PurposeCategoryDO updatePurposeCategory(PurposeCategoryDO purposeCategory) throws DataAccessException {
        PurposeCategoryDO updatedPurposeCatDO=consentDao.updatePurposeCategory(purposeCategory);
        return updatedPurposeCatDO;
    }

    @Override
    public PurposeCategoryDO deletePurposeCategory(int categoryId) throws DataAccessException {
        PurposeCategoryDO purposeCategory = consentDao.deletePurposeCategory(categoryId);
        return purposeCategory;
    }

    @Override
    public PurposeCategoryDO getPurposeCategoryById(int id) throws DataAccessException {
        PurposeCategoryDO purposeCategoryDO=consentDao.getPurposeCategoryById(id);
        return purposeCategoryDO;
    }

    //-- Third Party Configuration
    @Override
    public List<ThirdPartyDO> getThirdParties() throws DataAccessException {
        List<ThirdPartyDO> thirdPartyList = consentDao.getThirdPartyDetailsForConf();
        return thirdPartyList;
    }

    @Override
    public void setThirdParty(ThirdPartyDO thirdParty) throws DataAccessException {
        consentDao.addThirdParty(thirdParty);
    }

    @Override
    public void updateThirdParty(ThirdPartyDO thirdParty) throws DataAccessException {
        consentDao.updateThirdParty(thirdParty);
    }

    @Override
    public ThirdPartyDO deleteThirdParty(int thirdPartyId) throws DataAccessException {
        ThirdPartyDO thirdParty = consentDao.deleteThirdParty(thirdPartyId);
        return thirdParty;
    }

    @Override
    public ConsentDO getSubjectName(String subjectName) throws DataAccessException {
        ConsentDO consent = consentDao.getUserNameFromSGUID(subjectName);
        return consent;
    }

    @Override
    public void setConsentDetailsForUser(ConsentDO consentDO, ServicesDO[] services) throws DataAccessException {
        consentDao.addUserAndDataControllerDetails(consentDO);
        consentDao.addUserConsentDetails(consentDO, services);
    }

    @Override
    public void revokeConsent(String subjectName, List<ServicesDO> servicesList) throws DataAccessException {
//        Complete the impl
    }
}
