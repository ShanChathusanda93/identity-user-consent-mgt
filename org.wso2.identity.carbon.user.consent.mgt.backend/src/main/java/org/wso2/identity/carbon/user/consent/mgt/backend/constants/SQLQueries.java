package org.wso2.identity.carbon.user.consent.mgt.backend.constants;

public class SQLQueries {
    public static final String TRANSACTION_AND_DATA_CONTROLLER_DETAILS_QUERY = "SELECT A.COLLECTION_METHOD,A.SGUID,A" +
            ".PII_PRINCIPAL_ID,A.CONSENT_TIMESTAMP,B.DATA_CONTROLLER_ID,B.ORGANIZATION_NAME,B.CONTACT_NAME,B.STREET," +
            "B.COUNTRY,B.EMAIL,B.PHONE_NUMBER,B.PUBLIC_KEY,B.POLICY_URL FROM user_consent.TRANSACTION_DETAILS AS A," +
            "user_consent.DATA_CONTROLLER AS B WHERE A.PII_PRINCIPAL_ID=? AND A.DATA_CONTROLLER_ID=B.DATA_CONTROLLER_ID";

    public static final String SERVICES_DETAILS_BY_USER_QUERY = "SELECT A.SGUID,A.SERVICE_ID,B.SERVICE_DESCRIPTION,\n" +
            "A.PURPOSE_ID,C.PURPOSE,C.PRIMARY_PURPOSE,C.TERMINATION,C.THIRD_PARTY_DIS,C.THIRD_PARTY_ID,\n" +
            "D.THIRD_PARTY_NAME," +
            "E.PII_CAT_ID,\n" +
            "F.PII_CAT,F.SENSITIVITY\n" +
            "FROM user_consent.SERVICE_MAP_CRID AS A,\n" +
            "user_consent.SERVICES AS B,\n" +
            "user_consent.PURPOSES AS C,\n" +
            "user_consent.THIRD_PARTY AS D,\n" +
            "user_consent.PURPOSE_MAP_PII_CAT AS E,\n" +
            "user_consent.PII_CATEGORY AS F\n" +
            "WHERE SGUID=?\n" +
            "AND A.STATUS='Approved'\n" +
            "AND A.SERVICE_ID=B.SERVICE_ID\n" +
            "AND A.PURPOSE_ID=C.PURPOSE_ID\n" +
            "AND C.THIRD_PARTY_ID=D.THIRD_PARTY_ID\n" +
            "AND C.PURPOSE_ID=E.PURPOSE_ID\n" +
            "AND E.PII_CAT_ID=F.PII_CAT_ID;";

    public static final String SERVICE_DETAILS_BY_USER_AND_THIRD_PARTY_QUERY = "SELECT A.SGUID,A.SERVICE_ID,\n" +
            "B.SERVICE_DESCRIPTION,\n" +
            "A.PURPOSE_ID,\n" +
            "C.PURPOSE,C.PRIMARY_PURPOSE,C.TERMINATION,C.THIRD_PARTY_DIS,C.THIRD_PARTY_ID,\n" +
            "D.THIRD_PARTY_NAME,\n" +
            "E.PII_CAT_ID,\n" +
            "F.PII_CAT,F.SENSITIVITY\n" +
            "FROM user_consent.SERVICE_MAP_CRID AS A,\n" +
            "user_consent.SERVICES AS B,\n" +
            "user_consent.PURPOSES AS C,\n" +
            "user_consent.THIRD_PARTY AS D,\n" +
            "user_consent.PURPOSE_MAP_PII_CAT AS E,\n" +
            "user_consent.PII_CATEGORY AS F\n" +
            "WHERE SGUID=?\n" +
            "AND C.THIRD_PARTY_ID=?\n" +
            "AND A.STATUS='Approved'\n" +
            "AND A.SERVICE_ID=B.SERVICE_ID\n" +
            "AND A.PURPOSE_ID=C.PURPOSE_ID\n" +
            "AND C.THIRD_PARTY_ID=D.THIRD_PARTY_ID\n" +
            "AND C.PURPOSE_ID=E.PURPOSE_ID\n" +
            "AND E.PII_CAT_ID=F.PII_CAT_ID;";

    public static final String CONSENT_DETAILS_BY_USER_BY_THIRD_PARTY_QUERY = "SELECT DISTINCT C.PII_PRINCIPAL_ID,A" +
            ".SERVICE_ID,D.SERVICE_DESCRIPTION,B.THIRD_PARTY_ID\n" +
            "FROM SERVICE_MAP_CRID AS A,PURPOSES AS B,TRANSACTION_DETAILS AS C,SERVICES AS D\n" +
            "WHERE A.SGUID=?\n" +
            "AND A.PURPOSE_ID=B.PURPOSE_ID\n" +
            "AND A.SGUID=C.SGUID\n" +
            "AND A.SERVICE_ID=D.SERVICE_ID\n" +
            "AND B.THIRD_PARTY_ID=?;";

    public static final String PURPOSE_ID_BY_USER_BY_SERVICE_BY_THIRD_PARTY_QUERY = "SELECT A.SGUID,A.SERVICE_ID,A" +
            ".PURPOSE_ID,B.THIRD_PARTY_ID\n" +
            "FROM SERVICE_MAP_CRID AS A,PURPOSES AS B\n" +
            "WHERE A.SERVICE_ID=?\n" +
            "AND A.SGUID=?\n" +
            "AND B.THIRD_PARTY_ID=?\n" +
            "AND A.PURPOSE_ID=B.PURPOSE_ID;";

    public static final String USER_CONSENT_DETAILS_UPDATE_QUERY = "UPDATE user_consent.SERVICE_MAP_CRID" +
            "SET STATUS='Revoked'," +
            "COLLECTION_METHOD=?," +
            "CONSENT_TIME=?," +
            "EXACT_TERMINATION=''" +
            "CONSENT_TYPE=?" +
            "WHERE SGUID=?" +
            "AND SERVICE_ID=?" +
            "AND PURPOSE_ID=?";

    public static final String SERVICE_BY_USER_BY_SERVICE_ID_DEMO_QUERY = "SELECT A.SGUID,A.SERVICE_ID,B" +
            ".SERVICE_DESCRIPTION," +
            "A.PURPOSE_ID,A.STATUS\n" +
            "FROM SERVICE_MAP_CRID AS A,SERVICES AS B\n" +
            "WHERE A.SGUID=?\n" +
            "AND A.STATUS=\"Approved\"\n" +
            "AND A.SERVICE_ID=?\n" +
            "AND A.SERVICE_ID=B.SERVICE_ID;";

    public static final String SERVICE_PURPOSES_BY_USER_BY_SERVICE_BY_PURPOSE_QUERY="SELECT A.SGUID,A.SERVICE_ID,\n" +
            "B.SERVICE_DESCRIPTION,\n" +
            "A.PURPOSE_ID,\n" +
            "C.PURPOSE,C.PRIMARY_PURPOSE,C.TERMINATION,C.THIRD_PARTY_DIS,C.THIRD_PARTY_ID,\n" +
            "D.THIRD_PARTY_NAME,\n" +
            "E.PII_CAT_ID,\n" +
            "F.PII_CAT,F.SENSITIVITY\n" +
            "FROM user_consent.SERVICE_MAP_CRID AS A,\n" +
            "user_consent.SERVICES AS B,\n" +
            "user_consent.PURPOSES AS C,\n" +
            "user_consent.THIRD_PARTY AS D,\n" +
            "user_consent.PURPOSE_MAP_PII_CAT AS E,\n" +
            "user_consent.PII_CATEGORY AS F\n" +
            "WHERE SGUID=?\n" +
            "AND A.SERVICE_ID=?\n" +
            "AND A.PURPOSE_ID=?\n" +
            "AND A.STATUS='Approved'\n" +
            "AND A.SERVICE_ID=B.SERVICE_ID\n" +
            "AND A.PURPOSE_ID=C.PURPOSE_ID\n" +
            "AND C.THIRD_PARTY_ID=D.THIRD_PARTY_ID\n" +
            "AND C.PURPOSE_ID=E.PURPOSE_ID\n" +
            "AND E.PII_CAT_ID=F.PII_CAT_ID;";

    public static final String PURPOSE_BY_USER_BY_SERVICE_QUERY="SELECT A.SGUID,A.SERVICE_ID,B.SERVICE_DESCRIPTION,A.PURPOSE_ID,A.STATUS\n" +
            "FROM SERVICE_MAP_CRID AS A,SERVICES AS B\n" +
            "WHERE A.SGUID=?\n" +
            "AND A.STATUS=\"Approved\"\n" +
            "AND A.SERVICE_ID=?\n" +
            "AND A.PURPOSE_ID=?\n" +
            "AND A.SERVICE_ID=B.SERVICE_ID;";

    public static final String SENSITIVE_PERSONAL_INFO_CATEGORY_QUERY="SELECT C.PII_CAT_ID,C.PII_CAT,A.PURPOSE_ID ,B.PURPOSE,D.SGUID\n" +
            "FROM user_consent.PURPOSE_MAP_PII_CAT AS A," +
            "user_consent.PURPOSES AS B," +
            "user_consent.PII_CATEGORY AS C," +
            "user_consent.SERVICE_MAP_CRID AS D\n" +
            "WHERE A.PURPOSE_ID=B.PURPOSE_ID\n" +
            "AND A.PII_CAT_ID=C.PII_CAT_ID\n" +
            "AND B.PURPOSE_ID=D.PURPOSE_ID\n" +
            "AND D.SGUID=?\n" +
            "AND C.SENSITIVITY=1\n" +
            "GROUP BY A.PII_CAT_ID,B.PURPOSE_ID;";

    public static final String PURPOSE_CATEGORIES_QUERY="SELECT A.PURPOSE_ID,C.PURPOSE,A.PURPOSE_CAT_ID,B.PURPOSE_CAT_SHORT_CODE\n" +
            "FROM user_consent.PURPOSE_MAP_PURPOSE_CAT AS A,\n" +
            "user_consent.PURPOSE_CATEGORY AS B,\n" +
            "user_consent.PURPOSES AS C,\n" +
            "user_consent.SERVICE_MAP_CRID AS D\n" +
            "WHERE A.PURPOSE_CAT_ID=B.PURPOSE_CAT_ID\n" +
            "AND A.PURPOSE_ID=C.PURPOSE_ID\n" +
            "AND A.PURPOSE_ID=D.PURPOSE_ID\n" +
            "AND D.SGUID=?\n" +
            "GROUP BY A.PURPOSE_ID,A.PURPOSE_CAT_ID;";

    public static final String PERSONALLY_IDENTIFIABLE_INFO_CAT_UPDATE_QUERY="UPDATE user_consent.PII_CATEGORY\n" +
            "SET PII_CAT=?, PII_CAT_DESCRIPTION=?, SENSITIVITY=?" +
            "WHERE PII_CAT_ID=?;";

    public static final String PURPOSE_DETAILS_UPDATE_QUERY="UPDATE user_consent.PURPOSES" +
            "SET PURPOSE=?,PRIMARY_PURPOSE=?,TERMINATION=?,THIRD_PARTY_DIS=?,THIRD_PARTY_ID=?" +
            "WHERE PURPOSE_ID=?";

    public static final String CONSENT_BY_USER_REVOKE_QUERY="UPDATE user_consent.SERVICE_MAP_CRID" +
            "SET STATUS='Revoked',CONSENT_TIME=?" +
            "WHERE SGUID=?" +
            "AND SERVICE_ID=?" +
            "AND PURPOSE_ID=?";

    public static final String PURPOSE_CATS_FOR_PURPOSE_CONF_QUERY="SELECT A.*, B.PURPOSE_CAT_SHORT_CODE\n" +
            "FROM user_consent.PURPOSE_MAP_PURPOSE_CAT AS A,\n" +
            "user_consent.PURPOSE_CATEGORY AS B\n" +
            "WHERE A.PURPOSE_CAT_ID=B.PURPOSE_CAT_ID\n" +
            "AND A.PURPOSE_ID=?;";

    public static final String PERSONALLY_IDENTIFIABLR_CAT_FOR_PURPOSE_CONF_QUERY="SELECT A.*,B.PII_CAT\n" +
            "FROM PURPOSE_MAP_PII_CAT AS A,PII_CATEGORY AS B\n" +
            "WHERE A.PII_CAT_ID=B.PII_CAT_ID\n" +
            "AND A.PURPOSE_ID=?;";

    public static final String SERVICES_FOR_CONF_QUERY = "SELECT * FROM SERVICES;";

    public static final String PURPOSE_DETAILS_FOR_SERVICE_CONF_QUERY = "SELECT A.* ,B.PURPOSE FROM " +
            "SERVICE_MAP_PURPOSE ASA, PURPOSES AS B WHERE SERVICE_ID=? AND A.PURPOSE_ID=B.PURPOSE_ID;";

    public static final String SERVICES_FOR_USER_VIEW_QUERY="SELECT DISTINCT A.SERVICE_ID,C.SERVICE_DESCRIPTION,B.PII_PRINCIPAL_ID,B.SGUID\n" +
            "FROM SERVICE_MAP_CRID AS A,TRANSACTION_DETAILS AS B,SERVICES AS C\n" +
            "WHERE A.SGUID=B.SGUID\n" +
            "AND B.PII_PRINCIPAL_ID=?\n" +
            "AND A.SERVICE_ID=C.SERVICE_ID;";

    public static final String PURPOSE_ID_BY_USER_BY_SERVICE_QUERY="SELECT A.SGUID,A.SERVICE_ID,A.PURPOSE_ID\n" +
            "FROM SERVICE_MAP_CRID AS A,PURPOSES AS B\n" +
            "WHERE A.SERVICE_ID=?\n" +
            "AND A.SGUID=?\n" +
            "AND A.PURPOSE_ID=B.PURPOSE_ID;";

    public static final String DATA_CONTROLLER_UPDATE_QUERY="UPDATE DATA_CONTROLLER\n" +
            "SET ORGANIZATION_NAME=?,\n" +
            "CONTACT_NAME=?\n" +
            "STREET=?\n" +
            "COUNTRY=?\n" +
            "EMAIL=?\n" +
            "PHONE_NUMBER=?\n" +
            "PUBLIC_KEY=?\n" +
            "POLICY_URL=?\n" +
            "WHERE DATA_CONTROLLER_ID=?;";
}
