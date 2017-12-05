package org.wso2.identity.carbon.user.consent.mgt.backend.service;

import org.json.simple.JSONObject;
import org.wso2.identity.carbon.user.consent.mgt.backend.DAO.ConsentDao;
import org.wso2.identity.carbon.user.consent.mgt.backend.JSONParserLayer.JSONParser;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PiiCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeDetailsDO;

import java.text.ParseException;
import java.util.List;

public class ConsentBackendImpl implements ConsentBackend {
    @Override
    public void produce(String name) {
        ConsentDao consentDao=new ConsentDao();
    }

    @Override
    public JSONObject getCreatedConsentReceipt(String subjectName) throws DataAccessException, ParseException {
        JSONParser jsonParser=new JSONParser();
        JSONObject consentReceiptObject=jsonParser.createConsentReceipt(subjectName);
        return consentReceiptObject;
    }

    @Override
    public List<PiiCategoryDO> getPersonalIdentifyInfoCat() throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        List<PiiCategoryDO> piiCategoryDOList=consentDao.getPersonalInfoCatForConfig();
        return piiCategoryDOList;
    }

    @Override
    public List<PurposeDetailsDO> getPurposeDetailsForConf() throws DataAccessException {
        ConsentDao consentDao=new ConsentDao();
        List<PurposeDetailsDO> purposeDetailsDOList=consentDao.getPurposesForConfig();
        return purposeDetailsDOList;
    }
}
