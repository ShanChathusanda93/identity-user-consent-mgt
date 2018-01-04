package org.wso2.identity.carbon.user.consent.mgt.backend.daoInterfaces;

import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeDetailsDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;

import java.util.List;

public interface PurposeDetailsDao {
//    public List<PurposeDetails> getConsentPurposes();
    public int getPurposeIdByPurpose(String purposeName) throws DataAccessException;
    public List<PurposeDetailsDO> getPurposeCategories(String SGUID) throws DataAccessException;
    public PurposeDetailsDO addPurposeDetails(PurposeDetailsDO purpose) throws DataAccessException;
    public PurposeDetailsDO updatePurposeDetails(PurposeDetailsDO purpose) throws DataAccessException;
//    public int getPurposeTerminationDays(int purposeId) throws DataAccessException;
    public List<PurposeDetailsDO> getPurposesForConfig() throws DataAccessException;
}
