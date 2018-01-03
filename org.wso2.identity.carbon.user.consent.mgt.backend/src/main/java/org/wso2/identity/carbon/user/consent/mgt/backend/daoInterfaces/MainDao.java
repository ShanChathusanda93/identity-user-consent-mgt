package org.wso2.identity.carbon.user.consent.mgt.backend.daoInterfaces;

/*
Extending all the interfaces to one interface
*/

public interface MainDao extends ConsentDao, DataControllerDao, PurposeDetailsDao, ServicesDao,
        PiiCategoryDao {
}
