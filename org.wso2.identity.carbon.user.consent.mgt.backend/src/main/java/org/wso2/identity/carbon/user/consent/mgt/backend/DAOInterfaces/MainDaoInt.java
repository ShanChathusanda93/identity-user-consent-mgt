package org.wso2.identity.carbon.user.consent.mgt.backend.DAOInterfaces;

/*
Extending all the interfaces to one interface
*/

public interface MainDaoInt extends ConsentDaoInt, DataControllerDaoInt, PurposeDetailsDaoInt, ServicesDaoInt,
        PiiCategoryDaoInt {
}
