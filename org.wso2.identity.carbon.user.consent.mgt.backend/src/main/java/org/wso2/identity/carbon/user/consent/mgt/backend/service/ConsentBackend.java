package org.wso2.identity.carbon.user.consent.mgt.backend.service;

import org.json.simple.JSONObject;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PiiCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeDetailsDO;

import java.text.ParseException;
import java.util.List;

public interface ConsentBackend {
    public void produce(String name);
    public JSONObject getCreatedConsentReceipt(String subjectName) throws DataAccessException, ParseException;
    public List<PiiCategoryDO> getPersonalIdentifyInfoCat() throws DataAccessException;
    public List<PurposeDetailsDO> getPurposeDetailsForConf() throws DataAccessException;
}
