package org.wso2.identity.carbon.user.consent.mgt.backend.daoInterfaces;

import org.wso2.identity.carbon.user.consent.mgt.backend.model.PiiCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;

import java.sql.SQLException;
import java.util.List;

public interface PiiCategoryDao {
    public List<PiiCategoryDO> getSensitivePersonalInfoCategory(String SGUID) throws DataAccessException;
    public PiiCategoryDO addPiiCategory(PiiCategoryDO piiCategory) throws DataAccessException;
    public PiiCategoryDO updatePersonalInfoCat(PiiCategoryDO piiCategory) throws DataAccessException;
    public List<PiiCategoryDO> getPersonalInfoCatForConfig() throws DataAccessException;
}
