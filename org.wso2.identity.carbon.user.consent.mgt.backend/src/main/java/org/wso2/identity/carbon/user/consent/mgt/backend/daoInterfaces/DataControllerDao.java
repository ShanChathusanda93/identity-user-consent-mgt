package org.wso2.identity.carbon.user.consent.mgt.backend.daoInterfaces;

import org.wso2.identity.carbon.user.consent.mgt.backend.model.DataControllerDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;

public interface DataControllerDao {
    public DataControllerDO addDataController(DataControllerDO dataController) throws DataAccessException;
    public int isDataControllerExists(String orgName) throws DataAccessException;
}
