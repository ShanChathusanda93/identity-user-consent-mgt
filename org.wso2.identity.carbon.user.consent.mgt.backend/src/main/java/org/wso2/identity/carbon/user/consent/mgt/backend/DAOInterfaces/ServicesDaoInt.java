package org.wso2.identity.carbon.user.consent.mgt.backend.DAOInterfaces;

import org.wso2.identity.carbon.user.consent.mgt.backend.model.ServicesDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ConsentDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;

import java.util.List;

public interface ServicesDaoInt {
    public List<ServicesDO> getServices();

    public ServicesDO getServicesByUserByServiceId(String piiPrincipalId, int serviceId) throws DataAccessException;

    public ServicesDO[] getServicePurposesByUserByServiceByPurposeId(String piiPrincipalId, int serviceId,
                                                                     int purposeId) throws DataAccessException;

    public int getServiceIdByService(String serviceName) throws DataAccessException;

    public void addServiceDetails(ServicesDO service) throws DataAccessException;

    public void updateServiceDetails(ServicesDO service) throws DataAccessException;

    public void updateUserConsentDetails(ConsentDO consent, List<ServicesDO> revockedServicesList) throws
            DataAccessException;
}
