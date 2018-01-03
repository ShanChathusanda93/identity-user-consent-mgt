package org.wso2.identity.carbon.user.consent.mgt.backend.daoInterfaces;

import org.wso2.identity.carbon.user.consent.mgt.backend.model.ConsentDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ServicesDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;

import java.util.List;

public interface ConsentDao {
    public List<ConsentDO> getUserConsent();

    public void addUserAndDataControllerDetails(ConsentDO consent) throws DataAccessException;

    public void addUserConsentDetails(ConsentDO consent, ServicesDO[] services) throws DataAccessException;

    public ConsentDO getSGUIDByUser(String piiPrincipalId) throws DataAccessException;

    public ServicesDO getServicesByUserByServiceId(String piiPrincipalId, int serviceId) throws DataAccessException;

    public int isPiiPrincipalExists(String piiPrincipalId) throws DataAccessException;

    public void updatePiiPrincipalId(ConsentDO consent) throws DataAccessException;

    public void revokeConsentByUser(String piiPrincipalId, List<ServicesDO> serviceList) throws DataAccessException;
}
