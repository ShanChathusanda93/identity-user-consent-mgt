package org.wso2.identity.carbon.user.consent.mgt.backend.DAOInterfaces;

import org.wso2.identity.carbon.user.consent.mgt.backend.model.PiiCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;

import java.util.List;

public interface PiiCategoryDaoInt {
    public List<PiiCategoryDO> getSensitivePersonalInfoCategory(String SGUID) throws DataAccessException;
    public void addPiiCategory(PiiCategoryDO piiCategory) throws DataAccessException;
    public void updatePersonallyIdentifiableInfoCat(PiiCategoryDO piiCategory) throws DataAccessException;
    public List<PiiCategoryDO> getPersonalInfoCatForConfig() throws DataAccessException;
}
