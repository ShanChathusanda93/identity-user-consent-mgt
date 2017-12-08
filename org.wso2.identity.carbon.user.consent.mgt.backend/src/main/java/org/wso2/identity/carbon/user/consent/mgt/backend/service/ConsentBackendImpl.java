package org.wso2.identity.carbon.user.consent.mgt.backend.service;

import org.json.simple.JSONObject;
import org.wso2.identity.carbon.user.consent.mgt.backend.DAO.ConsentDao;
import org.wso2.identity.carbon.user.consent.mgt.backend.JSONParserLayer.JSONParser;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PiiCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeDetailsDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ServicesDO;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ConsentBackendImpl implements ConsentBackend {
    @Override
    public void produce(String name) {
        ConsentDao consentDao = new ConsentDao();
    }

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
        ConsentDao consentDao=new ConsentDao();
        List<ServicesDO> servicesDOList=consentDao.getServicesForUserView(subjectName);
        return servicesDOList;
    }

    @Override
    public ServicesDO getServiceByUserByServiceId(String subjectName, int serviceId) throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        ServicesDO servicesDO=consentDao.getServiceByUserByServiceIdDemo(subjectName,serviceId);
        return servicesDO;
    }

    @Override
    public PurposeDetailsDO getPurposeByUserByServiceByPurposeId(String subjectName, int serviceId, int purposeId) throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        PurposeDetailsDO purposeDetailsDO=consentDao.getPurposeByUserByService(subjectName,serviceId,purposeId);
        return purposeDetailsDO;
    }

    @Override
    public List<ServicesDO> getServicesByUserByThirdParty(String subjectName, int thirdPartyId) throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        List<ServicesDO> servicesDOList=consentDao.getServiceDetailsByThirdParty(subjectName,thirdPartyId);
        return servicesDOList;
    }
}
