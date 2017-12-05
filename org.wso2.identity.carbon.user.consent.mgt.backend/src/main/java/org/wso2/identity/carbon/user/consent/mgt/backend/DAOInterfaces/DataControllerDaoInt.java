package org.wso2.identity.carbon.user.consent.mgt.backend.DAOInterfaces;

import org.wso2.identity.carbon.user.consent.mgt.backend.model.DataControllerDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;

import java.util.List;

public interface DataControllerDaoInt {
    public List<DataControllerDO> getDataController();

    public void addDataController(DataControllerDO dataController) throws DataAccessException;

    public int isDataControllerExists(String orgName) throws DataAccessException;
}
