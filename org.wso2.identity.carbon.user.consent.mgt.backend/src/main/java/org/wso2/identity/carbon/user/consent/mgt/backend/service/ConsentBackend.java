package org.wso2.identity.carbon.user.consent.mgt.backend.service;

import org.json.simple.JSONObject;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PiiCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeDetailsDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ServicesDO;

import java.text.ParseException;
import java.util.List;

public interface ConsentBackend {
    public void produce(String name);
    public JSONObject getCreatedConsentReceipt(String subjectName) throws DataAccessException, ParseException;
    public List<PiiCategoryDO> getPersonalIdentifyInfoCat() throws DataAccessException;
    public List<PurposeDetailsDO> getPurposeDetailsForConf() throws DataAccessException;
    public List<ServicesDO> getServicesForConf() throws DataAccessException;
    public List<ServicesDO> getServicesForUserView(String subjectName) throws DataAccessException;
    public ServicesDO getServiceByUserByServiceId(String subjectName,int serviceId) throws DataAccessException;
    public PurposeDetailsDO getPurposeByUserByServiceByPurposeId(String subjectName,int serviceId,int purposeId)
            throws DataAccessException;
    public List<ServicesDO> getServicesByUserByThirdParty(String subjectName,int thirdPartyId) throws
            DataAccessException;
}
