package org.wso2.identity.carbon.user.consent.mgt.backend.constants;

public class SQLQueries {
    public static final String servicesForConfQuery = "SELECT * FROM SERVICES;";
    public static final String purposeDetailsForServiceConfQuery = "SELECT A.* ,B.PURPOSE FROM SERVICE_MAP_PURPOSE AS " +
                                                                    "A, PURPOSES AS B WHERE SERVICE_ID=?\n" +
                                                                    "AND A.PURPOSE_ID=B.PURPOSE_ID;";
//    public static final String servicesForUserView=

}
