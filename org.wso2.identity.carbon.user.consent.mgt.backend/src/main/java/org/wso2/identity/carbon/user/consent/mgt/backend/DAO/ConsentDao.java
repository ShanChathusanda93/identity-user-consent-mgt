package org.wso2.identity.carbon.user.consent.mgt.backend.DAO;

import jdk.nashorn.internal.ir.WhileNode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.identity.carbon.user.consent.mgt.backend.DAOInterfaces.MainDaoInt;
import org.wso2.identity.carbon.user.consent.mgt.backend.constants.SQLQueries;
import org.wso2.identity.carbon.user.consent.mgt.backend.dbconnect.DBConnect;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ConsentDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.DataControllerDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PiiCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeDetailsDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ServicesDO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This is the consentDao class
 */
public class ConsentDao extends DBConnect implements MainDaoInt {
    private static final Log log = LogFactory.getLog(ConsentDao.class);
    DBConnect dbConnect = new DBConnect();
    List<ConsentDO> consents;
    List<ServicesDO> serviceList;
    List<DataControllerDO> dataControllerList;

    public ConsentDao() {
    }

    /**
     * This constructor gets the user and data controller details from the database.
     *
     * @param piiPrincipalId
     * @throws DataAccessException
     */
    public ConsentDao(String piiPrincipalId) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection con = dbConnect.getConnection();
            String piiPrincipal = piiPrincipalId;
            String query = "SELECT A.COLLECTION_METHOD,A.SGUID,A.PII_PRINCIPAL_ID,A.CONSENT_TIMESTAMP," +
                    "B.DATA_CONTROLLER_ID,B.ORGANIZATION_NAME,B.CONTACT_NAME,B.STREET,B.COUNTRY,B.EMAIL,B.PHONE_NUMBER," +
                    "B.PUBLIC_KEY,B.POLICY_URL FROM user_consent.TRANSACTION_DETAILS AS A," +
                    "user_consent.DATA_CONTROLLER AS B WHERE A.PII_PRINCIPAL_ID=? " +
                    "AND A.DATA_CONTROLLER_ID=B.DATA_CONTROLLER_ID";
            try {
                ConsentDO consentDO;
                DataControllerDO dataController;
                consents = new ArrayList<ConsentDO>();

                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, piiPrincipal);

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    dataController = new DataControllerDO(rs.getInt(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11), rs.getString(12), rs.getString(13));
                    consentDO = new ConsentDO(rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), dataController);
                    consents.add(consentDO);
                }
            } catch (SQLException e) {
                if (log.isDebugEnabled()) {
                    log.debug("Error occurred while getting the user details : " + e);
                }
                throw new DataAccessException("Error occurred while getting the user details", e);
            }
        } else {
            if (log.isDebugEnabled()) {
                log.debug("Can't establish a connection with the database");
            }
            log.info("Log update - Can't establish a connection with the database");
        }
    }

    /**
     * This constructor gets the services and purposes for one user
     *
     * @param SGUID
     * @param dummy
     * @throws DataAccessException
     */
    public ConsentDao(String SGUID, boolean dummy) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection con = dbConnect.getConnection();
            String sguid = SGUID;

            String query = "SELECT A.SGUID,A.SERVICE_ID,\n" +
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
                    "AND A.STATUS='Approved'\n" +
                    "AND A.SERVICE_ID=B.SERVICE_ID\n" +
                    "AND A.PURPOSE_ID=C.PURPOSE_ID\n" +
                    "AND C.THIRD_PARTY_ID=D.THIRD_PARTY_ID\n" +
                    "AND C.PURPOSE_ID=E.PURPOSE_ID\n" +
                    "AND E.PII_CAT_ID=F.PII_CAT_ID;";

            try {
                ServicesDO services;
                serviceList = new ArrayList<ServicesDO>();

                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, sguid);
                ResultSet rs = preparedStatement.executeQuery();

                PurposeDetailsDO purpose;
                PiiCategoryDO piiCategory;

                while (rs.next()) {
                    piiCategory = new PiiCategoryDO(rs.getInt("PII_CAT_ID"), rs.getString("PII_CAT"),
                            rs.getInt("SENSITIVITY"));
                    purpose = new PurposeDetailsDO(rs.getString(1), rs.getInt(4), rs.getString(5), rs.getString(6), rs
                            .getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), piiCategory);
                    services = new ServicesDO(rs.getInt(2), rs.getString(3), purpose);
                    serviceList.add(services);
                }
            } catch (SQLException e) {
                if (log.isDebugEnabled()) {
                    log.debug("Error occurred while getting the org.wso2.identity.carbon.user.consent.mgt.backend.constants.service details to the user : " + sguid + " - " + e);
                }
                throw new DataAccessException("Error occurred while getting the org.wso2.identity.carbon.user.consent.mgt.backend.constants.service details to the user : " + sguid,
                        e);
            }
        } else {
            if (log.isDebugEnabled()) {
                log.debug(
                        "Log update - Can't establish a connection with the database to get the org.wso2.identity.carbon.user.consent.mgt.backend.constants.service details to user : "
                                + SGUID);
            }
            log.info("Log update - Can't establish a connection with the database to get the org.wso2.identity.carbon.user.consent.mgt.backend.constants.service details to user : "
                    + SGUID);
        }
    }

    /**
     * This constructor gets the services and purposes by third party for one user
     *
     * @param SGUID
     * @param thirdPartyId
     * @throws DataAccessException
     */
    public ConsentDao(String SGUID, String thirdPartyId) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection con = dbConnect.getConnection();

            String query = "SELECT A.SGUID,A.SERVICE_ID,\n" +
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
            try {
                ServicesDO services;
                PiiCategoryDO piiCategory;
                serviceList = new ArrayList<ServicesDO>();

                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, SGUID);
                preparedStatement.setString(2, thirdPartyId);
                ResultSet rs = preparedStatement.executeQuery();

                rs.last();
                PurposeDetailsDO[] purposeDetails = new PurposeDetailsDO[rs.getRow()];
                rs.beforeFirst();

                int i = 0;
                while (rs.next()) {
                    piiCategory = new PiiCategoryDO(rs.getInt("PII_CAT_ID"), rs.getString("PII_CAT"),
                            rs.getInt("SENSITIVITY"));
                    purposeDetails[i] = new PurposeDetailsDO(rs.getString(1), rs.getInt(4), rs.getString(5),
                            rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9),
                            rs.getString(10), piiCategory);
                    services = new ServicesDO(rs.getInt(2), rs.getString(3), purposeDetails[i]);
                    serviceList.add(services);
                    i++;
                }
            } catch (SQLException e) {
                if (log.isDebugEnabled()) {
                    log.debug("Error occurred while getting the details to user :" + SGUID + "by third party.\n" + e);
                }
                throw new DataAccessException(
                        "Error occurred while getting the details to user :" + SGUID + "by third party", e);
            }
        } else {
            if (log.isDebugEnabled()) {
                System.out.println("Error occurred while getting the details to user :" + SGUID + "by third party.");
            }
            log.info("Error occurred while getting the details to user : " + SGUID + "by third party.");
        }
    }

    public List<ServicesDO> getServiceDetailsByThirdParty(String subjectName,int thirdPartyId){
        ConsentDao consentDao=new ConsentDao();
        ConsentDO user=consentDao.getSGUIDByUser(subjectName);
        if(dbConnect.connect()){
            Connection connection=dbConnect.getConnection();

            String query="SELECT DISTINCT C.PII_PRINCIPAL_ID,A.SERVICE_ID,D.SERVICE_DESCRIPTION,B.THIRD_PARTY_ID\n" +
                    "FROM SERVICE_MAP_CRID AS A,PURPOSES AS B,TRANSACTION_DETAILS AS C,SERVICES AS D\n" +
                    "WHERE A.SGUID=?\n" +
                    "AND A.PURPOSE_ID=B.PURPOSE_ID\n" +
                    "AND A.SGUID=C.SGUID\n" +
                    "AND A.SERVICE_ID=D.SERVICE_ID\n" +
                    "AND B.THIRD_PARTY_ID=?;";

            try {
                PreparedStatement preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1,user.getSGUID());
                preparedStatement.setInt(2,thirdPartyId);
                ResultSet resultSet=preparedStatement.executeQuery();

                List<ServicesDO> servicesDOList=new ArrayList<>();
                while (resultSet.next()){
                    ServicesDO servicesDO=new ServicesDO();
                    servicesDO.setServiceId(resultSet.getInt(2));
                    servicesDO.setServiceDescription(resultSet.getString(3));
                    List<Integer> purposeIdList=getPurposeIdByUserByServiceByThirdParty(user.getSGUID(),resultSet
                            .getInt(2),thirdPartyId);
                    List<PurposeDetailsDO> purposeDetailsDOList=new ArrayList<>();
                    for(int i=0;i<purposeIdList.size();i++){
                        PurposeDetailsDO purposeDetailsDO=getPurposeDetailsByPurposeId(purposeIdList.get(i));
                        purposeDetailsDOList.add(purposeDetailsDO);
                    }
                    servicesDO.setPurposeDetails(purposeDetailsDOList.toArray(new PurposeDetailsDO[0]));
                    servicesDOList.add(servicesDO);
                }
                return servicesDOList;
            } catch (SQLException e) {
                System.out.println("err");
            }
        }
        return null;
    }

    private List<Integer> getPurposeIdByUserByServiceByThirdParty(String sguid, int serviceId, int thirdPartyId){
        if(dbConnect.connect()){
            Connection connection=dbConnect.getConnection();

            String query="SELECT A.SGUID,A.SERVICE_ID,A.PURPOSE_ID,B.THIRD_PARTY_ID\n" +
                    "FROM SERVICE_MAP_CRID AS A,PURPOSES AS B\n" +
                    "WHERE A.SERVICE_ID=?\n" +
                    "AND A.SGUID=?\n" +
                    "AND B.THIRD_PARTY_ID=?\n" +
                    "AND A.PURPOSE_ID=B.PURPOSE_ID;";

            try {
                PreparedStatement preparedStatement=connection.prepareStatement(query);
                preparedStatement.setInt(1,serviceId);
                preparedStatement.setString(2,sguid);
                preparedStatement.setInt(3,thirdPartyId);
                ResultSet resultSet=preparedStatement.executeQuery();
                List<Integer> purposeIdList=new ArrayList<>();
                while (resultSet.next()){
                    int i=resultSet.getInt(3);
                    purposeIdList.add(i);
                }
                return purposeIdList;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * This method gets the SGUID for one user
     *
     * @param piiPrincipalId
     * @return
     */
    @Override
    public ConsentDO getSGUIDByUser(String piiPrincipalId) {
        if (dbConnect.connect()) {
            Connection con = dbConnect.getConnection();

            String query = "SELECT A.SGUID,A.PII_PRINCIPAL_ID FROM TRANSACTION_DETAILS AS A WHERE A" +
                    ".PII_PRINCIPAL_ID=?;";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, piiPrincipalId);
                ResultSet resultSet = preparedStatement.executeQuery();

                ConsentDO consentDO = new ConsentDO();
                resultSet.next();//--Taking the cursor to the first result row
                consentDO.setSGUID(resultSet.getString(1));
                return consentDO;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * This method adds an user's consent details to the database
     *
     * @param consentDO
     * @throws DataAccessException
     */
    @Override
    public void addUserAndDataControllerDetails(ConsentDO consentDO) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection con = dbConnect.getConnection();

            ConsentDO consentInput = consentDO;

            String query = "INSERT INTO TRANSACTION_DETAILS\n" +
                    "VALUES (?,?,?,?,?);";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, consentInput.getCollectionMethod());
                preparedStatement.setString(2, consentInput.getSGUID());
                preparedStatement.setString(3, consentInput.getPiiPrincipalId());
                preparedStatement.setString(4, consentInput.getConsentTimestamp());
                preparedStatement.setInt(5, consentInput.getDataController().getDataControllerId());
                preparedStatement.executeUpdate();

                log.info("Successfully added the pii principal to the database");
            } catch (SQLException e) {
                if (log.isDebugEnabled()) {
                    log.debug("Error occurred while inserting user to the database");
                }
                throw new DataAccessException("Error occurred while inserting user to the database", e);
            }
        }
    }

    /**
     * This method adds services and purposes that the user gives permission
     *
     * @param consentDO
     * @param services
     * @throws DataAccessException
     */
    @Override
    public void addUserConsentDetails(ConsentDO consentDO, ServicesDO[] services) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            for (int j = 0; j < services.length; j++) {
                for (int i = 0; i < services[j].getPurposeDetailsArr().length; i++) {
                    String query = "INSERT INTO SERVICE_MAP_CRID (SGUID,SERVICE_ID,PURPOSE_ID,EXACT_TERMINATION," +
                            "CONSENT_TIME,COLLECTION_METHOD,STATUS) VALUES (?,?,?,?,?,?,?);";
                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, consentDO.getSGUID());
                        preparedStatement.setInt(2, services[j].getServiceId());
                        preparedStatement.setInt(3, services[j].getPurposeDetailsArr()[i].getPurposeId());
                        preparedStatement.setString(4, services[j].getPurposeDetailsArr()[i].getExactTermination());
                        preparedStatement.setString(5, services[j].getPurposeDetailsArr()[i].getTimestamp());
                        preparedStatement.setString(6, services[j].getPurposeDetailsArr()[i]
                                .getCollectionMethod());
                        preparedStatement.setString(7, "Approved");
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        if (log.isDebugEnabled()) {
                            log.debug("Error occurred while inserting " + consentDO.getPiiPrincipalId()
                                    + " user consents to the database");
                        }
                        deletePiiPrincipal(consentDO);
                        throw new DataAccessException("Error occurred while inserting " + consentDO.getPiiPrincipalId()
                                + " user consents to the database", e);
                    }
                }
            }
            log.info("Successfully added the user consents of the " + consentDO.getPiiPrincipalId()
                    + " to the database");
        }
    }

    private void deletePiiPrincipal(ConsentDO consentDO) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "DELETE FROM user_consent.TRANSACTION_DETAILS\n" +
                    "WHERE SGUID=?;";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, consentDO.getSGUID());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                log.error("Error occurred while deleting the subject from the database", e);
                throw new DataAccessException("Error occurred while deleting the subject from the database");
            }

        }
    }

    /**
     * This method updates the user consent to the database for onr user
     *
     * @param consentDO
     * @param revokedServicesList
     * @throws DataAccessException
     */
    @Override
    public void updateUserConsentDetails(ConsentDO consentDO, List<ServicesDO> revokedServicesList) throws
            DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "UPDATE user_consent.SERVICE_MAP_CRID" +
                    "SET STATUS='Revoked'," +
                    "COLLECTION_METHOD=?," +
                    "CONSENT_TIME=?," +
                    "EXACT_TERMINATION=''" +
                    "CONSENT_TYPE=?" +
                    "WHERE SGUID=?" +
                    "AND SERVICE_ID=?" +
                    "AND PURPOSE_ID=?";
            for (ServicesDO service : serviceList) {
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, service.getPurposeDetails().getCollectionMethod());
                    preparedStatement.setString(2, service.getPurposeDetails().getTimestamp());
                    preparedStatement.setString(3, service.getPurposeDetails().getConsentType());
                    preparedStatement.setString(4, consentDO.getSGUID());
                    preparedStatement.setInt(5, service.getServiceId());
                    preparedStatement.setInt(6, service.getPurposeDetails().getPurposeId());
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    log.error("Error occurred while updating the consent", e);
                    throw new DataAccessException("Error occurred while updating the consent.");
                }
            }
        }
    }

    /**
     * This method gets the services by org.wso2.identity.carbon.user.consent.mgt.backend.constants.service id for one user
     *
     * @param piiPrincipalId
     * @param serviceId
     * @return
     * @throws DataAccessException
     */
    @Override
    public ServicesDO getServicesByUserByServiceId(String piiPrincipalId, int serviceId) throws DataAccessException {
        /*if (dbConnect.connect()) {
            Connection con = dbConnect.getConnection();

            ConsentDao consentDao = new ConsentDao();
            ConsentDO consentInput = consentDao.getSGUIDByUser(piiPrincipalId);

            String query = "SELECT A.SGUID,A.SERVICE_ID,\n" +
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
                    "AND A.STATUS='Approved'\n" +
                    "AND A.SERVICE_ID=B.SERVICE_ID\n" +
                    "AND A.PURPOSE_ID=C.PURPOSE_ID\n" +
                    "AND C.THIRD_PARTY_ID=D.THIRD_PARTY_ID\n" +
                    "AND C.PURPOSE_ID=E.PURPOSE_ID\n" +
                    "AND E.PII_CAT_ID=F.PII_CAT_ID;";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, consentInput.getSGUID());
                preparedStatement.setInt(2, serviceId);
                ResultSet resultSet = preparedStatement.executeQuery();

                int i = 0;
                resultSet.last();
                PurposeDetailsDO[] purposes = new PurposeDetailsDO[resultSet.getRow()];
                resultSet.beforeFirst();

                ServicesDO service;
                PiiCategoryDO piiCategory;

                while (resultSet.next()) {
                    piiCategory = new PiiCategoryDO(resultSet.getInt("PII_CAT_ID"), resultSet.getString("PII_CAT"),
                            resultSet.getInt("SENSITIVITY"));
                    purposes[i] = new PurposeDetailsDO(resultSet.getString(1), resultSet.getInt(4), resultSet.getString(5),
                            resultSet.getString(6), resultSet.getString(7), resultSet.getString(8),
                            resultSet.getInt(9), resultSet.getString(10),
                            piiCategory);
                    i++;
                }
                resultSet.first();//--Move the cursor to the first record of the resultset--//
                service = new ServicesDO(resultSet.getInt(2), resultSet.getString(3), purposes);
                return service;
            } catch (SQLException e) {
                if (log.isDebugEnabled()) {
                    log.debug("Error occurred while getting org.wso2.identity.carbon.user.consent.mgt.backend.constants.service : " + serviceId + " to user : "
                            + piiPrincipalId);
                }
                throw new DataAccessException(
                        "Error occurred while getting org.wso2.identity.carbon.user.consent.mgt.backend.constants.service : " + serviceId + " to user : " + piiPrincipalId);
            }
        }*/
        return null;
    }

    public ServicesDO getServiceByUserByServiceIdDemo(String subjectName, int serviceId) {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            ConsentDao consentDao = new ConsentDao();
            ConsentDO consentDO = consentDao.getSGUIDByUser(subjectName);

            String query = "SELECT A.SGUID,A.SERVICE_ID,B.SERVICE_DESCRIPTION,A.PURPOSE_ID,A.STATUS\n" +
                    "FROM SERVICE_MAP_CRID AS A,SERVICES AS B\n" +
                    "WHERE A.SGUID=?\n" +
                    "AND A.STATUS=\"Approved\"\n" +
                    "AND A.SERVICE_ID=?\n" +
                    "AND A.SERVICE_ID=B.SERVICE_ID;";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, consentDO.getSGUID());
                preparedStatement.setInt(2, serviceId);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<PurposeDetailsDO> purposeDetailsDOList = new ArrayList<>();
                while (resultSet.next()) {
                    PurposeDetailsDO purposeDetailsDO=getPurposeDetailsByPurposeId(resultSet.getInt(4));
                    purposeDetailsDOList.add(purposeDetailsDO);
                }
                resultSet.first();
                ServicesDO servicesDO = new ServicesDO();
                servicesDO.setServiceId(resultSet.getInt(2));
                servicesDO.setServiceDescription(resultSet.getString(3));
                servicesDO.setPurposeDetails(purposeDetailsDOList.toArray(new PurposeDetailsDO[0]));
                return servicesDO;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private PurposeDetailsDO getPurposeDetailsByPurposeId(int purposeId) {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "SELECT A.*,B.THIRD_PARTY_NAME FROM PURPOSES AS A,THIRD_PARTY AS B WHERE PURPOSE_ID=? AND " +
                    "A.THIRD_PARTY_ID=B.THIRD_PARTY_ID;";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, purposeId);
                ResultSet resultSet = preparedStatement.executeQuery();

                PurposeDetailsDO purposeDetailsDO = new PurposeDetailsDO();
                resultSet.first();
                purposeDetailsDO.setPurposeId(resultSet.getInt(1));
                purposeDetailsDO.setPurpose(resultSet.getString(2));
                purposeDetailsDO.setPrimaryPurpose(String.valueOf(resultSet.getInt(4)));
                purposeDetailsDO.setTermination(String.valueOf(resultSet.getInt(5)));
                purposeDetailsDO.setThirdPartyDis(String.valueOf(resultSet.getInt(6)));
                purposeDetailsDO.setThirdPartyId(resultSet.getInt(7));
                purposeDetailsDO.setthirdPartyName(resultSet.getString(8));

                PurposeCategoryDO[] purposeCategoryDOArr = getPurposeCatsForPurposeConf(resultSet.getInt(1)).toArray
                        (new PurposeCategoryDO[0]);
                purposeDetailsDO.setPurposeCategoryDOArr(purposeCategoryDOArr);

                PiiCategoryDO[] piiCategoryDOArr= getPersonalIdentifyCatForPurposeConf(resultSet.getInt(1)).toArray
                        (new PiiCategoryDO[0]);
                purposeDetailsDO.setpiiCategoryArr(piiCategoryDOArr);
                return purposeDetailsDO;

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /*private List<PurposeCategoryDO> getPurposeCategoriesByPurposeId(int purposeId) {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "SELECT A.*,B.PURPOSE_CAT_SHORT_CODE\n" +
                    "FROM PURPOSE_MAP_PURPOSE_CAT AS A,PURPOSE_CATEGORY AS B\n" +
                    "WHERE PURPOSE_ID=?\n" +
                    "AND A.PURPOSE_CAT_ID=B.PURPOSE_CAT_ID;";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, purposeId);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<PurposeCategoryDO> purposeCategoryDOList = new ArrayList<>();
                while (resultSet.next()) {
                    PurposeCategoryDO purposeCategoryDO = new PurposeCategoryDO();
                    purposeCategoryDO.setPurposeId(resultSet.getInt(1));
                    purposeCategoryDO.setPurposeCatId(resultSet.getInt(2));
                    purposeCategoryDO.setPurposeCatShortCode(resultSet.getString(3));
                    purposeCategoryDOList.add(purposeCategoryDO);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/

   /* *
     * This method gets services and purposes by org.wso2.identity.carbon.user.consent.mgt.backend.constants.service id and purpose id for one user
     *
     * @param
     * @param serviceId
     * @param purposeId
     * @return
     * @throws DataAccessException*/
    @Override
    public ServicesDO[] getServicePurposesByUserByServiceByPurposeId(String piiPrincipalId, int serviceId,
                                                                     int purposeId) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection con = dbConnect.getConnection();

            ConsentDao consentDao = new ConsentDao();
            ConsentDO consentInput = consentDao.getSGUIDByUser(piiPrincipalId);

            String query = "SELECT A.SGUID,A.SERVICE_ID,\n" +
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
            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, consentInput.getSGUID());
                preparedStatement.setInt(2, serviceId);
                preparedStatement.setInt(3, purposeId);
                ResultSet resultSet = preparedStatement.executeQuery();

                resultSet.last();
                ServicesDO[] services = new ServicesDO[resultSet.getRow()];
                resultSet.beforeFirst();

                PurposeDetailsDO purpose;
                PiiCategoryDO piiCategory;
                int i = 0;

                while (resultSet.next()) {
                    piiCategory = new PiiCategoryDO(resultSet.getInt("PII_CAT_ID"), resultSet.getString("PII_CAT"),
                            resultSet.getInt("SENSITIVITY"));
                    purpose = new PurposeDetailsDO(resultSet.getString(1), resultSet.getInt(4), resultSet.getString(5),
                            resultSet.getString(6), resultSet.getString(7), resultSet.getString(8),
                            resultSet.getInt(9), resultSet.getString(10),
                            piiCategory);
                    services[i] = new ServicesDO(resultSet.getInt("SERVICE_ID"),
                            resultSet.getString("SERVICE_DESCRIPTION"), purpose);
                    i++;
                }
                return services;
            } catch (SQLException e) {
                if (log.isDebugEnabled()) {
                    log.debug("Error occurred while getting the purpose : " + purposeId + " to user : "
                            + piiPrincipalId);
                }
                throw new DataAccessException(
                        "Error occurred while getting the purpose : " + purposeId + " to user : " + piiPrincipalId,
                        e);
            }
        }
        return null;
    }

    public PurposeDetailsDO getPurposeByUserByService(String subjectName,int serviceId,int purposeId) throws DataAccessException{
        if(dbConnect.connect()){
            Connection connection=dbConnect.getConnection();

            ConsentDao consentDao = new ConsentDao();
            ConsentDO consentDO = consentDao.getSGUIDByUser(subjectName);

            String query="SELECT A.SGUID,A.SERVICE_ID,B.SERVICE_DESCRIPTION,A.PURPOSE_ID,A.STATUS\n" +
                    "FROM SERVICE_MAP_CRID AS A,SERVICES AS B\n" +
                    "WHERE A.SGUID=?\n" +
                    "AND A.STATUS=\"Approved\"\n" +
                    "AND A.SERVICE_ID=?\n" +
                    "AND A.PURPOSE_ID=?\n" +
                    "AND A.SERVICE_ID=B.SERVICE_ID;";

            try {
                PreparedStatement preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1,consentDO.getSGUID());
                preparedStatement.setInt(2,serviceId);
                preparedStatement.setInt(3,purposeId);
                ResultSet resultSet=preparedStatement.executeQuery();
                resultSet.first();
                PurposeDetailsDO purposeDetailsDO=getPurposeDetailsByPurposeId(resultSet.getInt(4));
                return purposeDetailsDO;
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while getting the purpose details",e);
            }
        }
        return null;
    }

    /**
     * This method is checking whether the subject id(piiPrincipalId) is already in the database or not
     *
     * @param piiPrincipalId
     * @return
     * @throws DataAccessException
     */
    @Override
    public int isPiiPrincipalExists(String piiPrincipalId) throws DataAccessException {
        int i = 0;
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "SELECT COUNT(PII_PRINCIPAL_ID) as count FROM user_consent.TRANSACTION_DETAILS WHERE " +
                    "PII_PRINCIPAL_ID=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, piiPrincipalId);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                i = resultSet.getByte("count");
            } catch (SQLException e) {
                if (log.isDebugEnabled()) {
                    log.debug("Error occurred while checking " + piiPrincipalId + " is in the data source or not in " +
                            "method isPiiPrincipalExists()");
                }
                throw new DataAccessException("Error occurred while checking the Personal Identifiable Info Principal" +
                        " is exists or not");
            }
        }
        return i;
    }

    /**
     * This method checks whether the data controller is already in the database or not
     *
     * @param orgName
     * @return
     * @throws DataAccessException
     */
    @Override
    public int isDataControllerExists(String orgName) throws DataAccessException {
        int dataControllerId = 0;
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "SELECT DATA_CONTROLLER_ID FROM user_consent.DATA_CONTROLLER WHERE ORGANIZATION_NAME=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, orgName);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    dataControllerId = 0;
                } else {
                    dataControllerId = resultSet.getByte("DATA_CONTROLLER_ID");
                }
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while checking the data controller is available or not");
            }
        }
        return dataControllerId;
    }

    /**
     * This method gets the org.wso2.identity.carbon.user.consent.mgt.backend.constants.service id by org.wso2.identity.carbon.user.consent.mgt.backend.constants.service name
     *
     * @param serviceName
     * @return
     * @throws DataAccessException
     */
    @Override
    public int getServiceIdByService(String serviceName) throws DataAccessException {
        int serviceId = 0;

        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "SELECT SERVICE_ID FROM user_consent.SERVICES WHERE SERVICE_DESCRIPTION=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, serviceName);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    serviceId = 0;
                } else {
                    serviceId = resultSet.getInt("SERVICE_ID");
                }

            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while getting the service name");
            }
        }
        return serviceId;
    }

    /**
     * This method gets the purpose id by purpose name
     *
     * @param purposeName
     * @return
     * @throws DataAccessException
     */
    @Override
    public int getPurposeIdByPurpose(String purposeName) throws DataAccessException {
        int purposeId = 0;

        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "SELECT PURPOSE_ID FROM user_consent.PURPOSES WHERE PURPOSE=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, purposeName);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    purposeId = 0;
                } else {
                    purposeId = resultSet.getInt("PURPOSE_ID");
                }
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while getting the purpose id by purpose");
            }
        }
        return purposeId;
    }

    /**
     * This method adds the data controller to the database
     *
     * @param dataController
     * @throws DataAccessException
     */
    @Override
    public void addDataController(DataControllerDO dataController) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "INSERT INTO user_consent.DATA_CONTROLLER(ORGANIZATION_NAME,CONTACT_NAME,STREET,COUNTRY,EMAIL," +
                    "PHONE_NUMBER,PUBLIC_KEY,POLICY_URL) VALUES(?,?,?,?,?,?,?,?)";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, dataController.getOrgName());
                preparedStatement.setString(2, dataController.getContactName());
                preparedStatement.setString(3, dataController.getStreet());
                preparedStatement.setString(4, dataController.getCountry());
                preparedStatement.setString(5, dataController.getEmail());
                preparedStatement.setString(6, dataController.getPhoneNo());
                preparedStatement.setString(7, dataController.getPublicKey());
                preparedStatement.setString(8, dataController.getPolicyUrl());
                preparedStatement.executeUpdate();
                if (log.isDebugEnabled()) {
                    log.debug("Data Controller : " + dataController.getOrgName() + " successfully added to the data " +
                            "store");
                }
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while adding the data controller to the data source");
            }
        }
    }

    /**
     * This method gets the sensitive personal info category for one user
     *
     * @param SGUID
     * @return
     * @throws DataAccessException
     */
    @Override
    public List<PiiCategoryDO> getSensitivePersonalInfoCategory(String SGUID) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "SELECT C.PII_CAT_ID,C.PII_CAT,A.PURPOSE_ID ,B.PURPOSE,D.SGUID\n" +
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

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, SGUID);
                ResultSet resultSet = preparedStatement.executeQuery();
                PiiCategoryDO piiCategory;
                List<PiiCategoryDO> piiCategoryList = new ArrayList<>();
                int temp = 0;
                while (resultSet.next()) {
                    if (resultSet.getRow() == 0) {
                        temp = resultSet.getInt(1);
                        piiCategory = new PiiCategoryDO();
                        piiCategory.setPiiCatId(temp);
                        piiCategory.setPiiCat(resultSet.getString(2));
                        piiCategoryList.add(piiCategory);
                    } else {
                        if (temp != resultSet.getInt(1)) {
                            temp = resultSet.getInt(1);
                            piiCategory = new PiiCategoryDO();
                            piiCategory.setPiiCatId(temp);
                            piiCategory.setPiiCat(resultSet.getString(2));
                            piiCategoryList.add(piiCategory);
                        }
                    }
                }
                return piiCategoryList;
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while getting the sensitive personal information " +
                        "categories");
            }
        }
        return null;
    }

    /**
     * This method gets purpose categories for one user
     *
     * @param SGUID
     * @return
     * @throws DataAccessException
     */
    @Override
    public List<PurposeDetailsDO> getPurposeCategories(String SGUID) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "SELECT A.PURPOSE_ID,C.PURPOSE,A.PURPOSE_CAT_ID,B.PURPOSE_CAT_SHORT_CODE\n" +
                    "FROM user_consent.PURPOSE_MAP_PURPOSE_CAT AS A,\n" +
                    "user_consent.PURPOSE_CATEGORY AS B,\n" +
                    "user_consent.PURPOSES AS C,\n" +
                    "user_consent.SERVICE_MAP_CRID AS D\n" +
                    "WHERE A.PURPOSE_CAT_ID=B.PURPOSE_CAT_ID\n" +
                    "AND A.PURPOSE_ID=C.PURPOSE_ID\n" +
                    "AND A.PURPOSE_ID=D.PURPOSE_ID\n" +
                    "AND D.SGUID=?\n" +
                    "GROUP BY A.PURPOSE_ID,A.PURPOSE_CAT_ID;";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, SGUID);
                ResultSet resultSet = preparedStatement.executeQuery();

                List<PurposeDetailsDO> purposeCategoryDetailsList = new ArrayList<>();
                PurposeDetailsDO purposeCategoryDetails;
                while (resultSet.next()) {
                    purposeCategoryDetails = new PurposeDetailsDO();
                    purposeCategoryDetails.setPurposeId(resultSet.getInt(1));
                    purposeCategoryDetails.setPurpose(resultSet.getString(2));
                    purposeCategoryDetails.setPurposeCatId(resultSet.getInt(3));
                    purposeCategoryDetails.setPurposeCatShortCode(resultSet.getString(4));
                    purposeCategoryDetailsList.add(purposeCategoryDetails);
                }
                return purposeCategoryDetailsList;
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while getting purpose categories");
            }
        }
        return null;
    }

    /**
     * This method adds the personally identifiable info categories to the database
     *
     * @param piiCategory
     * @throws DataAccessException
     * @override PiiCategory org.wso2.identity.carbon.user.consent.mgt.backend.constants.DAO to add Personally Identifiable Info Categories
     */
    @Override
    public void addPiiCategory(PiiCategoryDO piiCategory) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "INSERT INTO user_consent.PII_CATEGORY(PII_CAT,PII_CAT_DESCRIPTION,SENSITIVITY)\n" +
                    "VALUES(?,?,?);";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, piiCategory.getPiiCat());
                preparedStatement.setString(2, piiCategory.getPiiCatDescription());
                preparedStatement.setInt(3, piiCategory.getSensitivity());
                preparedStatement.executeUpdate();
                if (log.isDebugEnabled()) {
                    log.debug("Personally Identifiable Information Category was successfully added to the database.");
                }
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while adding personally identifiable info categories to" +
                        " the data source.");
            }
        }
    }

    /**
     * This method updates the personally identifiable info categories to the database
     *
     * @param piiCategory
     * @throws DataAccessException
     * @override PiiCategory org.wso2.identity.carbon.user.consent.mgt.backend.constants.DAO to update Personally Identifiable Info Categories
     */
    @Override
    public void updatePersonallyIdentifiableInfoCat(PiiCategoryDO piiCategory) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "UPDATE user_consent.PII_CATEGORY\n" +
                    "SET PII_CAT=?, PII_CAT_DESCRIPTION=?, SENSITIVITY=?" +
                    "WHERE PII_CAT_ID=?;";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, piiCategory.getPiiCat());
                preparedStatement.setString(2, piiCategory.getPiiCatDescription());
                preparedStatement.setInt(3, piiCategory.getSensitivity());
                preparedStatement.setInt(4, piiCategory.getPiiCatId());
                preparedStatement.executeUpdate();

                if (log.isDebugEnabled()) {
                    log.debug("Personally Identifiable Information Category : " + piiCategory.getPiiCat() + " was " +
                            "successfully updated");
                }
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while updating the Personally Identifiable Information " +
                        "Category");
            }
        }
    }

    /**
     * This method adds purpose details to the database
     *
     * @param purpose
     * @throws DataAccessException
     * @override PurposeDetails org.wso2.identity.carbon.user.consent.mgt.backend.constants.DAO to add purpose details to the database
     */
    @Override
    public void addPurposeDetails(PurposeDetailsDO purpose) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "INSERT INTO user_consent.PURPOSES(PURPOSE,PRIMARY_PURPOSE,TERMINATION,THIRD_PARTY_DIS,THIRD_PARTY_ID)" +
                    "VALUES(?,?,?,?,?)";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, purpose.getPurpose());
                preparedStatement.setString(2, purpose.getPrimaryPurpose());
                preparedStatement.setString(3, purpose.getTermination());
                preparedStatement.setString(4, purpose.getThirdPartyDis());
                preparedStatement.setInt(5, purpose.getThirdPartyId());
                preparedStatement.executeUpdate();

                if (log.isDebugEnabled()) {
                    log.debug("Purpose : " + purpose.getPurpose() + " was successfully added to the data store");
                }

            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while adding Purpose to " + purpose.getPurpose() + " the " +
                        "database");
            }
        }
    }

    /**
     * This method updates the purpose details to the database
     *
     * @param purpose
     * @throws DataAccessException
     * @override PurposeDetails org.wso2.identity.carbon.user.consent.mgt.backend.constants.DAO to update purposes to the database
     */
    @Override
    public void updatePurposeDetails(PurposeDetailsDO purpose) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "UPDATE user_consent.PURPOSES" +
                    "SET PURPOSE=?,PRIMARY_PURPOSE=?,TERMINATION=?,THIRD_PARTY_DIS=?,THIRD_PARTY_ID=?" +
                    "WHERE PURPOSE_ID=?";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, purpose.getPurpose());
                preparedStatement.setString(2, purpose.getPrimaryPurpose());
                preparedStatement.setString(3, purpose.getTermination());
                preparedStatement.setString(4, purpose.getThirdPartyDis());
                preparedStatement.setInt(5, purpose.getThirdPartyId());
                preparedStatement.setInt(6, purpose.getPurposeId());
                preparedStatement.executeUpdate();

                if (log.isDebugEnabled()) {
                    log.debug("Purpose : " + purpose.getPurpose() + " was successfully updated to the database");
                }
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while updating the purpose : " + purpose.getPurpose() + " " +
                        "to the data store");
            }
        }
    }

    /**
     * This method adds services to the database
     *
     * @param service
     * @throws DataAccessException
     * @override ServicesDAO to add org.wso2.identity.carbon.user.consent.mgt.backend.constants.service details to the data store
     */
    @Override
    public void addServiceDetails(ServicesDO service) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "INSERT INTO user_consent.SERVICES(SERVICE_DESCRIPTION)" +
                    "VALUES(?);";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, service.getServiceDescription());
                preparedStatement.executeUpdate();

                if (log.isDebugEnabled()) {
                    log.debug("Service : " + service.getServiceDescription() + " successfully added to the data store");
                }
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while adding the service : " + service.getServiceDescription() +
                        " to the data store");
            }
        }
    }

    /**
     * This method updates the services to the database
     *
     * @param service
     * @throws DataAccessException
     * @override ServicesDao to update the Services
     */
    @Override
    public void updateServiceDetails(ServicesDO service) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "UPDATE user_consent.SERVICES" +
                    "SET SERVICE_DESCRIPTION=?;";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, service.getServiceDescription());
                preparedStatement.executeUpdate();

                if (log.isDebugEnabled()) {
                    log.debug("Service : " + service.getServiceDescription() + " was successfully updated to the data " +
                            "store");
                }
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while updating the service : " + service.getServiceDescription());
            }
        }
    }

    /**
     * This method updates the subject id(piiPrincipalId) to the database
     *
     * @param consentDO
     * @throws DataAccessException
     * @override ConsentDao to update principal ID to the data store
     */
    @Override
    public void updatePiiPrincipalId(ConsentDO consentDO) throws DataAccessException {
        if (log.isDebugEnabled()) {
            Connection connection = dbConnect.getConnection();

            String query = "UPDATE user_consent.TRANSACTION_DETAILS" +
                    "SET PII_PRINCIPAL_ID=?" +
                    "WHERE SGUID=?;";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, consentDO.getPiiPrincipalId());
                preparedStatement.setString(2, consentDO.getSGUID());
                preparedStatement.executeUpdate();

                if (log.isDebugEnabled()) {
                    log.debug("ID was successfully updated to the data store.");
                }
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while updating the ID to thr data store.");
            }
        }
    }

    /**
     * This method revokes consent for one user
     *
     * @param piiPrincipalId
     * @param serviceList
     * @throws DataAccessException
     */
    @Override
    public void revokeConsentByUser(String piiPrincipalId, List<ServicesDO> serviceList) throws DataAccessException {
        ConsentDO consentDO = getSGUIDByUser(piiPrincipalId);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String currentTime = dateFormat.format(calendar.getTime());

        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "UPDATE user_consent.SERVICE_MAP_CRID" +
                    "SET STATUS='Revoked',CONSENT_TIME=?" +
                    "WHERE SGUID=?" +
                    "AND SERVICE_ID=?" +
                    "AND PURPOSE_ID=?";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, currentTime);
                preparedStatement.setString(2, consentDO.getSGUID());
                for (int i = 0; i < serviceList.size(); i++) {
                    preparedStatement.setInt(3, serviceList.get(i).getServiceId());
                    for (int j = 0; j < serviceList.get(i).getPurposeDetailsArr().length; j++) {
                        if (serviceList.get(i).getPurposeDetailsArr()[j].getPurposeId() != 0) {
                            preparedStatement.setInt(4, serviceList.get(i).getPurposeDetailsArr()[j].
                                    getPurposeId());
                            preparedStatement.executeUpdate();
                        }
                    }
                }
                if (log.isDebugEnabled()) {
                    log.debug("Subject : " + piiPrincipalId + " was successfully revoked.");
                }
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while revoking the subject : " + piiPrincipalId + " " +
                        "from the data source");
            }
        }
    }

    /**
     * This method gets the termination days of a purpose
     *
     * @param purposeId
     * @return
     * @throws DataAccessException
     */
    @Override
    public int getPurposeTerminationDays(int purposeId) throws DataAccessException {
        int termination = 0;
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "SELECT TERMINATION " +
                    "FROM user_consent.PURPOSES " +
                    "WHERE PURPOSE_ID=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, purposeId);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                termination = resultSet.getInt(1);
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while getting the termination days of a purpose.");
            }
        }
        return termination;
    }

    /**
     * This method adds a record about the created consent receipts to the database
     *
     * @param jsonJWT
     * @param consentReceiptId
     * @param SGUID
     * @param timestamp
     * @throws DataAccessException
     */
    public void trackConsentReceipt(String jsonJWT, String consentReceiptId, String SGUID, String timestamp)
            throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "INSERT INTO RECEIPT_TRACKER(CONSENT_RECEIPT_ID,CONSENT_RECEIPT,STATUS,SGUID," +
                    "RECEIPT_TIMESTAMP)" +
                    "VALUES(?,?,?,?,?)";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, consentReceiptId);
                preparedStatement.setString(2, jsonJWT);
                preparedStatement.setString(3, "Generated");
                preparedStatement.setString(4, SGUID);
                preparedStatement.setString(5, timestamp);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                if (log.isDebugEnabled()) {
                    log.debug("Error occurred while adding the json file to the tracker" + e);
                }
                throw new DataAccessException("Error occurred while adding the json file to the tracker");
            }
        }
    }

    @Override
    public List<PiiCategoryDO> getPersonalInfoCatForConfig() throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "SELECT * FROM user_consent.PII_CATEGORY;";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<PiiCategoryDO> personallyIdentifiableInfoCatList = new ArrayList<>();
                while (resultSet.next()) {
                    PiiCategoryDO piiCategoryDO = new PiiCategoryDO();
                    piiCategoryDO.setPiiCatId(resultSet.getInt(1));
                    piiCategoryDO.setPiiCat(resultSet.getString(2));
                    piiCategoryDO.setPiiCatDescription(resultSet.getString(3));
                    piiCategoryDO.setSensitivity(resultSet.getInt(4));
                    personallyIdentifiableInfoCatList.add(piiCategoryDO);
                }
                return personallyIdentifiableInfoCatList;
            } catch (SQLException e) {
                throw new DataAccessException("Error while getting the Personally Identifiable Information Category " +
                        "list");
            }
        }
        return null;
    }

    @Override
    public List<PurposeDetailsDO> getPurposesForConfig() throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "SELECT A.*,B.THIRD_PARTY_NAME FROM user_consent.PURPOSES AS A,user_consent.THIRD_PARTY AS B " +
                    "WHERE A.THIRD_PARTY_ID=B.THIRD_PARTY_ID;";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<PurposeDetailsDO> purposeDetailsDOList = new ArrayList<>();
                while (resultSet.next()) {
                    PurposeDetailsDO purposeDetailsDO = new PurposeDetailsDO();
                    purposeDetailsDO.setPurposeId(resultSet.getInt(1));
                    purposeDetailsDO.setPurpose(resultSet.getString(2));

                    PurposeCategoryDO[] purposeCategoryDOArr = getPurposeCatsForPurposeConf(resultSet.getInt(1))
                            .toArray(new PurposeCategoryDO[0]);
                    purposeDetailsDO.setPurposeCategoryDOArr(purposeCategoryDOArr);

                    purposeDetailsDO.setPrimaryPurpose(resultSet.getString(4));
                    purposeDetailsDO.setTermination(resultSet.getString(5));
                    purposeDetailsDO.setThirdPartyDis(resultSet.getString(6));
                    purposeDetailsDO.setThirdPartyId(resultSet.getInt(7));
                    purposeDetailsDO.setthirdPartyName(resultSet.getString(8));

                    PiiCategoryDO[] piiCategoryDOArr = getPersonalIdentifyCatForPurposeConf(resultSet.getInt(1))
                            .toArray(new PiiCategoryDO[0]);
                    purposeDetailsDO.setpiiCategoryArr(piiCategoryDOArr);
                    purposeDetailsDOList.add(purposeDetailsDO);
                }
                return purposeDetailsDOList;
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while getting the Purposes list", e);
            }
        }
        return null;
    }

    private List<PurposeCategoryDO> getPurposeCatsForPurposeConf(int purposeId) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "SELECT A.*, B.PURPOSE_CAT_SHORT_CODE\n" +
                    "FROM user_consent.PURPOSE_MAP_PURPOSE_CAT AS A,\n" +
                    "user_consent.PURPOSE_CATEGORY AS B\n" +
                    "WHERE A.PURPOSE_CAT_ID=B.PURPOSE_CAT_ID\n" +
                    "AND A.PURPOSE_ID=?;";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, purposeId);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<PurposeCategoryDO> purposeCatList = new ArrayList<>();
                while (resultSet.next()) {
                    PurposeCategoryDO purposeCat = new PurposeCategoryDO();
                    purposeCat.setPurposeId(resultSet.getInt(1));
                    purposeCat.setPurposeCatId(resultSet.getInt(2));
                    purposeCat.setPurposeCatShortCode(resultSet.getString(3));
                    purposeCatList.add(purposeCat);
                }
                return purposeCatList;
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while getting the Purpose Categories");
            }
        }
        return null;
    }

    private List<PiiCategoryDO> getPersonalIdentifyCatForPurposeConf(int purposeId) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "SELECT A.*,B.PII_CAT\n" +
                    "FROM PURPOSE_MAP_PII_CAT AS A,PII_CATEGORY AS B\n" +
                    "WHERE A.PII_CAT_ID=B.PII_CAT_ID\n" +
                    "AND A.PURPOSE_ID=?;";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, purposeId);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<PiiCategoryDO> piiCategoryDOList = new ArrayList<>();
                while (resultSet.next()) {
                    PiiCategoryDO piiCategoryDO = new PiiCategoryDO();
                    piiCategoryDO.setPurposeId(resultSet.getInt(1));
                    piiCategoryDO.setPiiCatId(resultSet.getInt(2));
                    piiCategoryDO.setPiiCat(resultSet.getString(3));
                    piiCategoryDOList.add(piiCategoryDO);
                }
                return piiCategoryDOList;
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while getting the Personally Identifiable Info " +
                        "Categories to the purpose configurations", e);
            }
        }
        return null;
    }

    @Override
    public List<ServicesDO> getServicesForConf() throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = SQLQueries.servicesForConfQuery;

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<ServicesDO> servicesDOList = new ArrayList<>();
                while (resultSet.next()) {
                    ServicesDO servicesDO = new ServicesDO();
                    servicesDO.setServiceId(resultSet.getInt(1));
                    servicesDO.setServiceDescription(resultSet.getString(2));
                    PurposeDetailsDO[] purposeDetailsDO = getPurposeDetailsForServiceConf(resultSet.getInt(1))
                            .toArray(new PurposeDetailsDO[0]);
                    servicesDO.setPurposeDetails(purposeDetailsDO);
                    servicesDOList.add(servicesDO);
                }
                return servicesDOList;
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while getting the service details for the " +
                        "configurations");
            }
        }
        return null;
    }

    private List<PurposeDetailsDO> getPurposeDetailsForServiceConf(int serviceId) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = SQLQueries.purposeDetailsForServiceConfQuery;

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, serviceId);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<PurposeDetailsDO> purposeDetailsDOList = new ArrayList<>();
                while (resultSet.next()) {
                    PurposeDetailsDO purposeDetailsDO = new PurposeDetailsDO();
                    purposeDetailsDO.setPurposeId(resultSet.getInt(2));
                    purposeDetailsDO.setPurpose(resultSet.getString(3));
                    purposeDetailsDOList.add(purposeDetailsDO);
                }
                return purposeDetailsDOList;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<ServicesDO> getServicesForUserView(String subjectName) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();

            String query = "SELECT DISTINCT A.SERVICE_ID,C.SERVICE_DESCRIPTION,B.PII_PRINCIPAL_ID\n" +
                    "FROM SERVICE_MAP_CRID AS A,TRANSACTION_DETAILS AS B,SERVICES AS C\n" +
                    "WHERE A.SGUID=B.SGUID\n" +
                    "AND B.PII_PRINCIPAL_ID=?\n" +
                    "AND A.SERVICE_ID=C.SERVICE_ID;";

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, subjectName);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<ServicesDO> servicesDOList = new ArrayList<>();
                while (resultSet.next()) {
                    ServicesDO servicesDO = new ServicesDO();
                    servicesDO.setServiceId(resultSet.getInt(1));
                    servicesDO.setServiceDescription(resultSet.getString(2));
                    servicesDOList.add(servicesDO);
                }
                return servicesDOList;
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while getting the services for the user view");
            }
        }
        return null;
    }

    /**
     * This method returns the user details
     *
     * @return
     */
    @Override
    public List<ConsentDO> getUserConsent() {
        return consents;
    }

    /**
     * this method returns data controller details
     *
     * @return
     */
    @Override
    public List<DataControllerDO> getDataController() {
        return dataControllerList;
    }

    /**
     * This method returns services
     *
     * @return
     */
    @Override
    public List<ServicesDO> getServices() {
        return serviceList;
    }
}
