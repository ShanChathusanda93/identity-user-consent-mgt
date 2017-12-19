package org.wso2.identity.carbon.user.consent.mgt.backend.DAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.config.ConnectionConfig;
import org.wso2.identity.carbon.user.consent.mgt.backend.DAOInterfaces.MainDaoInt;
import org.wso2.identity.carbon.user.consent.mgt.backend.consentUtils.DBUtils;
import org.wso2.identity.carbon.user.consent.mgt.backend.constants.SQLQueries;
import org.wso2.identity.carbon.user.consent.mgt.backend.dbconnect.DBConnect;
import org.wso2.identity.carbon.user.consent.mgt.backend.exception.DataAccessException;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ConsentDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.DataControllerDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PiiCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeCategoryDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.PurposeDetailsDO;
import org.wso2.identity.carbon.user.consent.mgt.backend.model.ServicesDO;

import javax.naming.PartialResultException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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
            PreparedStatement preparedStatement = null;
            ResultSet rs = null;

            String query = SQLQueries.TRANSACTION_AND_DATA_CONTROLLER_DETAILS_QUERY;
            try {
                ConsentDO consentDO;
                DataControllerDO dataController;
                consents = new ArrayList<ConsentDO>();

                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, piiPrincipalId);
                rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    dataController = new DataControllerDO(rs.getInt(5), rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getString(9), rs.getString(10),
                            rs.getString(11), rs.getString(12), rs.getString(13));
                    consentDO = new ConsentDO(rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), dataController);
                    consents.add(consentDO);
                }
            } catch (SQLException e) {
                log.error("Database error. Could not get the user and data controller details. - " + e.getMessage(), e);
                throw new DataAccessException("Database error. Could not get the user and data controller details. - " +
                        "" + e.getMessage(), e);
            } finally {
                DBUtils.closeAllConnections(con, preparedStatement, rs);
            }
        }
    }

    /**
     * This constructor gets the services and purposes for one user
     *
     * @param sguid
     * @param dummy
     * @throws DataAccessException
     */
    public ConsentDao(String sguid, boolean dummy) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection con = dbConnect.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet rs = null;

            String query = SQLQueries.SERVICES_DETAILS_BY_USER_QUERY;
            try {
                ServicesDO services;
                serviceList = new ArrayList<ServicesDO>();

                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, sguid);
                rs = preparedStatement.executeQuery();

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
                log.error("Database error. Could not get consent details for the user. - " + e.getMessage(), e);
                throw new DataAccessException("Database error. Could not get service details for the user. - " + e.getMessage(), e);
            } finally {
                DBUtils.closeAllConnections(con, preparedStatement, rs);
            }
        }
    }

    /**
     * This constructor gets the services and purposes by third party for one user
     *
     * @param sguid
     * @param thirdPartyId
     * @throws DataAccessException
     */
    public ConsentDao(String sguid, int thirdPartyId) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection con = dbConnect.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet rs = null;

            String query = SQLQueries.SERVICE_DETAILS_BY_USER_AND_THIRD_PARTY_QUERY;
            try {
                ServicesDO services;
                PiiCategoryDO piiCategory;
                serviceList = new ArrayList<ServicesDO>();

                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, sguid);
                preparedStatement.setInt(2, thirdPartyId);
                rs = preparedStatement.executeQuery();

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
                log.error("Database error. Could not get consent details for the user and third party. - " + e
                        .getMessage(), e);
                throw new DataAccessException(
                        "Database error. Could not get consent details for the user and third party. - " + e.getMessage
                                (), e);
            } finally {
                DBUtils.closeAllConnections(con, preparedStatement, rs);
            }
        }
    }

    /*This method is for get consent details for a user by third party. Previous constructor is also for the same purpose*/
    public List<ServicesDO> getServiceDetailsByThirdParty(String subjectName, int thirdPartyId) throws DataAccessException {
        ConsentDao consentDao = new ConsentDao();
        ConsentDO user = consentDao.getSGUIDByUser(subjectName);
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String query = SQLQueries.CONSENT_DETAILS_BY_USER_BY_THIRD_PARTY_QUERY;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, user.getSGUID());
                preparedStatement.setInt(2, thirdPartyId);
                resultSet = preparedStatement.executeQuery();

                List<ServicesDO> servicesDOList = new ArrayList<>();
                while (resultSet.next()) {
                    ServicesDO servicesDO = new ServicesDO();
                    servicesDO.setServiceId(resultSet.getInt(2));
                    servicesDO.setServiceDescription(resultSet.getString(3));
                    List<Integer> purposeIdList = getPurposeIdByUserByServiceByThirdParty(user.getSGUID(), resultSet
                            .getInt(2), thirdPartyId);
                    List<PurposeDetailsDO> purposeDetailsDOList = new ArrayList<>();
                    for (int i = 0; i < purposeIdList.size(); i++) {
                        PurposeDetailsDO purposeDetailsDO = getPurposeDetailsByPurposeId(purposeIdList.get(i));
                        purposeDetailsDOList.add(purposeDetailsDO);
                    }
                    servicesDO.setPurposeDetails(purposeDetailsDOList.toArray(new PurposeDetailsDO[0]));
                    servicesDOList.add(servicesDO);
                }
                return servicesDOList;
            } catch (SQLException e) {
                log.error("Database error. Could not get consent details for the user and third party. - " + e
                        .getMessage(), e);
                throw new DataAccessException("Database error. Could not get consent details for the user and third party. - " + e
                        .getMessage(), e);
            } finally {
                DBUtils.closeAllConnections(connection, preparedStatement, resultSet);
            }
        }
        return null;
    }

    private List<Integer> getPurposeIdByUserByServiceByThirdParty(String sguid, int serviceId, int thirdPartyId) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String query = SQLQueries.PURPOSE_ID_BY_USER_BY_SERVICE_BY_THIRD_PARTY_QUERY;

            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, serviceId);
                preparedStatement.setString(2, sguid);
                preparedStatement.setInt(3, thirdPartyId);
                resultSet = preparedStatement.executeQuery();
                List<Integer> purposeIdList = new ArrayList<>();
                while (resultSet.next()) {
                    int i = resultSet.getInt(3);
                    purposeIdList.add(i);
                }
                return purposeIdList;
            } catch (SQLException e) {
                log.error("Database error. Could not get purpose IDs for services. - " + e.getMessage(), e);
                throw new DataAccessException("Database error. Could not get purpose IDs for services. - " + e
                        .getMessage(), e);
            } finally {
                DBUtils.closeAllConnections(connection, preparedStatement, resultSet);
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
    public ConsentDO getSGUIDByUser(String piiPrincipalId) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection con = dbConnect.getConnection();
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            String query = "SELECT A.SGUID,A.PII_PRINCIPAL_ID FROM TRANSACTION_DETAILS AS A WHERE A.PII_PRINCIPAL_ID=?;";
            try {
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, piiPrincipalId);
                resultSet = preparedStatement.executeQuery();

                ConsentDO consentDO = new ConsentDO();
                resultSet.next();//--Taking the cursor to the first result row
                consentDO.setSGUID(resultSet.getString(1));
                return consentDO;
            } catch (SQLException e) {
                log.error("Database error. Could not get system generated UID for the user. - " + e.getMessage(), e);
                throw new DataAccessException("Database error. Could not get system generated UID for the user. - " + e
                        .getMessage(), e);
            } finally {
                DBUtils.closeAllConnections(con, preparedStatement, resultSet);
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
            PreparedStatement preparedStatement = null;

            String query = "INSERT INTO TRANSACTION_DETAILS VALUES (?,?,?,?,?);";
            try {
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, consentDO.getCollectionMethod());
                preparedStatement.setString(2, consentDO.getSGUID());
                preparedStatement.setString(3, consentDO.getPiiPrincipalId());
                preparedStatement.setString(4, consentDO.getConsentTimestamp());
                preparedStatement.setInt(5, consentDO.getDataController().getDataControllerId());
                preparedStatement.executeUpdate();

                log.info("Successfully added the user " + consentDO.getPiiPrincipalId() + " to the database");
            } catch (SQLException e) {
                log.error("Database error. Could not add the user to the database. - " + e.getMessage(), e);
                throw new DataAccessException("Database error. Could not add the user to the database. - " + e
                        .getMessage(), e);
            } finally {
                DBUtils.closeAllConnections(con, preparedStatement);
            }
        }
    }

    /**
     * This method adds services and purposes that the user gave permission
     *
     * @param consentDO
     * @param services
     * @throws DataAccessException
     */
    @Override
    public void addUserConsentDetails(ConsentDO consentDO, ServicesDO[] services) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement = null;

            for (int j = 0; j < services.length; j++) {
                for (int i = 0; i < services[j].getPurposeDetailsArr().length; i++) {
                    String query = "INSERT INTO SERVICE_MAP_CRID (SGUID,SERVICE_ID,PURPOSE_ID,EXACT_TERMINATION," +
                            "CONSENT_TIME,COLLECTION_METHOD,STATUS,CONSENT_TYPE) VALUES (?,?,?,?,?,?,?,?);";
                    try {
                        preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, consentDO.getSGUID());
                        preparedStatement.setInt(2, services[j].getServiceId());
                        preparedStatement.setInt(3, services[j].getPurposeDetailsArr()[i].getPurposeId());
                        preparedStatement.setString(4, getPurposeTerminationDays(services[j].getPurposeDetailsArr()
                                [i].getPurposeId(), services[j].getPurposeDetailsArr()[i].getTimestamp()));
                        preparedStatement.setString(5, services[j].getPurposeDetailsArr()[i].getTimestamp());
                        preparedStatement.setString(6, services[j].getPurposeDetailsArr()[i]
                                .getCollectionMethod());
                        preparedStatement.setString(7, "Approved");
                        preparedStatement.setString(8,services[j].getPurposeDetailsArr()[i].getConsentType());
                        preparedStatement.executeUpdate();
                        log.info("Successfully added the user consents of the " + consentDO.getPiiPrincipalId()
                                + " to the database");
                    } catch (SQLException e) {
                        log.error("Database error. Could not add consents for the user to the database. - " + e
                                .getMessage(), e);
                        deletePiiPrincipal(consentDO);
                        throw new DataAccessException("Database error. Could not add consents for the user to the " +
                                "database. - " + e.getMessage(), e);
                    } finally {
                        DBUtils.closeAllConnections(connection, preparedStatement);
                    }
                }
            }
        }
    }

    private void deletePiiPrincipal(ConsentDO consentDO) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement = null;

            String query = "DELETE FROM user_consent.TRANSACTION_DETAILS WHERE SGUID=?;";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, consentDO.getSGUID());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                log.error("Error occurred while deleting the subject from the database", e);
                throw new DataAccessException("Error occurred while deleting the subject from the database");
            } finally {
                DBUtils.closeAllConnections(connection, preparedStatement);
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
            PreparedStatement preparedStatement = null;

            String query = SQLQueries.USER_CONSENT_DETAILS_UPDATE_QUERY;
            try {
                for (ServicesDO service : serviceList) {
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, service.getPurposeDetails().getCollectionMethod());
                    preparedStatement.setString(2, service.getPurposeDetails().getTimestamp());
                    preparedStatement.setString(3, service.getPurposeDetails().getConsentType());
                    preparedStatement.setString(4, consentDO.getSGUID());
                    preparedStatement.setInt(5, service.getServiceId());
                    preparedStatement.setInt(6, service.getPurposeDetails().getPurposeId());
                    preparedStatement.executeUpdate();
                }
                log.info("Successfully updated the consent details to the database");
            } catch (SQLException e) {
                log.error("Database error. Could not update consent details to the database. - "+e.getMessage(), e);
                throw new DataAccessException("Database error. Could not update consent details to the database. - " +
                        ""+e.getMessage(), e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement);
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

    public ServicesDO getServiceByUserByServiceIdDemo(String subjectName, int serviceId) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            ConsentDao consentDao = new ConsentDao();
            ConsentDO consentDO = consentDao.getSGUIDByUser(subjectName);

            String query = SQLQueries.SERVICE_BY_USER_BY_SERVICE_ID_DEMO_QUERY;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, consentDO.getSGUID());
                preparedStatement.setInt(2, serviceId);
                resultSet = preparedStatement.executeQuery();
                List<PurposeDetailsDO> purposeDetailsDOList = new ArrayList<>();
                while (resultSet.next()) {
                    PurposeDetailsDO purposeDetailsDO = getPurposeDetailsByPurposeId(resultSet.getInt(4));
                    purposeDetailsDOList.add(purposeDetailsDO);
                }
                resultSet.first();
                ServicesDO servicesDO = new ServicesDO();
                servicesDO.setServiceId(resultSet.getInt(2));
                servicesDO.setServiceDescription(resultSet.getString(3));
                servicesDO.setPurposeDetails(purposeDetailsDOList.toArray(new PurposeDetailsDO[0]));
                return servicesDO;
            } catch (SQLException e) {
                log.error("Database error. Could not get services for the user. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get services for the user. - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
            }
        }
        return null;
    }

    private PurposeDetailsDO getPurposeDetailsByPurposeId(int purposeId) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            String query = "SELECT A.*,B.THIRD_PARTY_NAME FROM PURPOSES AS A,THIRD_PARTY AS B WHERE PURPOSE_ID=? AND " +
                    "A.THIRD_PARTY_ID=B.THIRD_PARTY_ID;";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, purposeId);
                resultSet = preparedStatement.executeQuery();
                resultSet.first();

                PurposeDetailsDO purposeDetailsDO = new PurposeDetailsDO();
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

                PiiCategoryDO[] piiCategoryDOArr = getPersonalIdentifyCatForPurposeConf(resultSet.getInt(1)).toArray
                        (new PiiCategoryDO[0]);
                purposeDetailsDO.setpiiCategoryArr(piiCategoryDOArr);
                return purposeDetailsDO;
            } catch (SQLException e) {
                log.error("Database error. Could not get purpose. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get purpose. - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
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
        ServicesDO[] services=null;
        if (dbConnect.connect()) {
            Connection con = dbConnect.getConnection();
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            ConsentDao consentDao = new ConsentDao();
            ConsentDO consentInput = consentDao.getSGUIDByUser(piiPrincipalId);

            String query = SQLQueries.SERVICE_PURPOSES_BY_USER_BY_SERVICE_BY_PURPOSE_QUERY;
            try {
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, consentInput.getSGUID());
                preparedStatement.setInt(2, serviceId);
                preparedStatement.setInt(3, purposeId);
                resultSet = preparedStatement.executeQuery();

                resultSet.last();
                services = new ServicesDO[resultSet.getRow()];
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
            } catch (SQLException e) {
                log.error("Database error. Could not get consent details for the user. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get consent details for the user. - "+e
                        .getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(con,preparedStatement,resultSet);
            }
        }
        return services;
    }

    public PurposeDetailsDO getPurposeByUserByService(String subjectName, int serviceId, int purposeId) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            ConsentDao consentDao = new ConsentDao();
            ConsentDO consentDO = consentDao.getSGUIDByUser(subjectName);

            String query = SQLQueries.PURPOSE_BY_USER_BY_SERVICE_QUERY;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, consentDO.getSGUID());
                preparedStatement.setInt(2, serviceId);
                preparedStatement.setInt(3, purposeId);
                resultSet = preparedStatement.executeQuery();
                resultSet.first();
                PurposeDetailsDO purposeDetailsDO = getPurposeDetailsByPurposeId(resultSet.getInt(4));
                return purposeDetailsDO;
            } catch (SQLException e) {
                log.error("Database error. Could not get purpose details for the user by service. - "+e.getMessage()
                        ,e);
                throw new DataAccessException("Database error. Could not get purpose details for the user by service." +
                        " - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
            }
        }
        return null;
    }

    public String getUserNameFromSGUID(String subjectName) throws DataAccessException {
        String subject=null;
        if(dbConnect.connect()){
            Connection connection=dbConnect.getConnection();
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;
            ConsentDO consentDO=getSGUIDByUser(subjectName);

            String query="SELECT * FROM TRANSACTION_DETAILS WHERE SGUID=?";

            try {
                preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1,consentDO.getSGUID());
                resultSet=preparedStatement.executeQuery();
                resultSet.first();
                subject=resultSet.getString(3);
            } catch (SQLException e) {
                log.error("Database error. Could not get the subject name. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get the subject name. - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
            }
        }
        return subject;
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
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            String query = "SELECT COUNT(PII_PRINCIPAL_ID) as count FROM user_consent.TRANSACTION_DETAILS WHERE " +
                    "PII_PRINCIPAL_ID=?";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, piiPrincipalId);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                i = resultSet.getByte("count");
            } catch (SQLException e) {
                log.error("Database error. Could not get the existence of the user in the database. - "+e.getMessage
                        (),e);
                throw new DataAccessException("Database error. Could not get the existence of the user in the " +
                        "database. - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
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
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            String query = "SELECT DATA_CONTROLLER_ID FROM user_consent.DATA_CONTROLLER WHERE ORGANIZATION_NAME=?";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, orgName);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    dataControllerId = 0;
                } else {
                    dataControllerId = resultSet.getByte("DATA_CONTROLLER_ID");
                }
            } catch (SQLException e) {
                log.error("Database error. Could not get the existence of the data controller. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get the existence of the data controller. - " +
                        ""+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
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
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            String query = "SELECT SERVICE_ID FROM user_consent.SERVICES WHERE SERVICE_DESCRIPTION=?";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, serviceName);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    serviceId = 0;
                } else {
                    serviceId = resultSet.getInt("SERVICE_ID");
                }
            } catch (SQLException e) {
                log.error("Database error. Could not get service ID. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get service ID. - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
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
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            String query = "SELECT PURPOSE_ID FROM user_consent.PURPOSES WHERE PURPOSE=?";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, purposeName);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    purposeId = 0;
                } else {
                    purposeId = resultSet.getInt("PURPOSE_ID");
                }
            } catch (SQLException e) {
                log.error("Database error. Could not get purpose ID. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get purpose ID. - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
            }
        }
        return purposeId;
    }

    public DataControllerDO getDataController(int dataControllerId) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            String query = "SELECT * FROM DATA_CONTROLLER WHERE DATA_CONTROLLER_ID=?;";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, dataControllerId);
                resultSet = preparedStatement.executeQuery();
                resultSet.first();
                DataControllerDO dataControllerDO = new DataControllerDO();
                dataControllerDO.setDataControllerId(resultSet.getInt(1));
                dataControllerDO.setOrgName(resultSet.getString(2));
                dataControllerDO.setContactName(resultSet.getString(3));
                dataControllerDO.setStreet(resultSet.getString(4));
                dataControllerDO.setCountry(resultSet.getString(5));
                dataControllerDO.setEmail(resultSet.getString(6));
                dataControllerDO.setPhoneNo(resultSet.getString(7));
                dataControllerDO.setPublicKey(resultSet.getString(8));
                dataControllerDO.setPolicyUrl(resultSet.getString(9));
                return dataControllerDO;
            } catch (SQLException e) {
                log.error("Database error. Could not get the data controller details. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get the data controller details. - "+e
                        .getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
            }
        }
        return null;
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
            PreparedStatement preparedStatement=null;

            String query = "INSERT INTO user_consent.DATA_CONTROLLER(ORGANIZATION_NAME,CONTACT_NAME,STREET,COUNTRY,EMAIL," +
                    "PHONE_NUMBER,PUBLIC_KEY,POLICY_URL) VALUES(?,?,?,?,?,?,?,?)";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, dataController.getOrgName());
                preparedStatement.setString(2, dataController.getContactName());
                preparedStatement.setString(3, dataController.getStreet());
                preparedStatement.setString(4, dataController.getCountry());
                preparedStatement.setString(5, dataController.getEmail());
                preparedStatement.setString(6, dataController.getPhoneNo());
                preparedStatement.setString(7, dataController.getPublicKey());
                preparedStatement.setString(8, dataController.getPolicyUrl());
                preparedStatement.executeUpdate();
                log.info("Successfully added the data controller details to the database");
            } catch (SQLException e) {
                log.error("Database error. Could not add data controller details to the database. - "+e.getMessage()
                        ,e);
                throw new DataAccessException("Database error. Could not add data controller details to the database." +
                        " - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement);
            }
        }
    }

    public void updateDataController(DataControllerDO dataControllerDO) throws DataAccessException{
        if(dbConnect.connect()){
            Connection connection=dbConnect.getConnection();
            PreparedStatement preparedStatement=null;

            String query=SQLQueries.DATA_CONTROLLER_UPDATE_QUERY;
            try {
                preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1,dataControllerDO.getOrgName());
                preparedStatement.setString(2,dataControllerDO.getContactName());
                preparedStatement.setString(3,dataControllerDO.getStreet());
                preparedStatement.setString(4,dataControllerDO.getCountry());
                preparedStatement.setString(5,dataControllerDO.getEmail());
                preparedStatement.setString(6,dataControllerDO.getPhoneNo());
                preparedStatement.setString(7,dataControllerDO.getPublicKey());
                preparedStatement.setString(8,dataControllerDO.getPolicyUrl());
                preparedStatement.setInt(9,dataControllerDO.getDataControllerId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                log.error("Database error. Could not update the data controller. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not update the data controller. - "+e.getMessage
                        (),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement);
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
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            String query = SQLQueries.SENSITIVE_PERSONAL_INFO_CATEGORY_QUERY;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, SGUID);
                resultSet = preparedStatement.executeQuery();
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
                log.error("Database error. Could not get the sensitive personal info categories. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get the sensitive personal info categories. " +
                        "- "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
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
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            String query = SQLQueries.PURPOSE_CATEGORIES_QUERY;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, SGUID);
                resultSet = preparedStatement.executeQuery();

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
                log.error("Database error. Could not get purpose categories. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get purpose categories. - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
            }
        }
        return null;
    }

    /**
     * This method adds the personally identifiable info categories to the database
     *
     * @param piiCategory
     * @throws DataAccessException
     * @override PiiCategory DAO to add Personally Identifiable Info Categories
     */
    @Override
    public void addPiiCategory(PiiCategoryDO piiCategory) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement=null;

            String query = "INSERT INTO user_consent.PII_CATEGORY(PII_CAT,PII_CAT_DESCRIPTION,SENSITIVITY)\n" +
                    "VALUES(?,?,?);";

            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, piiCategory.getPiiCat());
                preparedStatement.setString(2, piiCategory.getPiiCatDescription());
                preparedStatement.setInt(3, piiCategory.getSensitivity());
                preparedStatement.executeUpdate();
                log.info("Personally Identifiable Information Category was successfully added to the database.");
            } catch (SQLException e) {
                log.error("Database error. Could not add personally identifiable info category tho the database. - " +
                        ""+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not add personally identifiable info category " +
                        "tho the database. - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement);
            }
        }
    }

    /**
     * This method updates the personally identifiable info categories to the database
     *
     * @param piiCategory
     * @throws DataAccessException
     * @override PiiCategory DAO to update Personally Identifiable Info Categories
     */
    @Override
    public void updatePersonallyIdentifiableInfoCat(PiiCategoryDO piiCategory) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement=null;

            String query = SQLQueries.PERSONALLY_IDENTIFIABLE_INFO_CAT_UPDATE_QUERY;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, piiCategory.getPiiCat());
                preparedStatement.setString(2, piiCategory.getPiiCatDescription());
                preparedStatement.setInt(3, piiCategory.getSensitivity());
                preparedStatement.setInt(4, piiCategory.getPiiCatId());
                preparedStatement.executeUpdate();
                log.info("Successfully updated the personally identifiable category to the database");
            } catch (SQLException e) {
                log.error("Database error. Could not update personally identifiable info category. - "+e.getMessage
                        (),e);
                throw new DataAccessException("Database error. Could not update personally identifiable info category" +
                        ". - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement);
            }
        }
    }

    /**
     * This method adds purpose details to the database
     *
     * @param purpose
     * @throws DataAccessException
     * @override PurposeDetails DAO to add purpose details to the database
     */
    @Override
    public void addPurposeDetails(PurposeDetailsDO purpose) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement=null;
            PreparedStatement preparedStatementPurposeId=null;
            ResultSet resultSet=null;

            String query = "INSERT INTO user_consent.PURPOSES(PURPOSE,PRIMARY_PURPOSE,TERMINATION,THIRD_PARTY_DIS," +
                    "THIRD_PARTY_ID) VALUES(?,?,?,?,?)";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, purpose.getPurpose());
                preparedStatement.setString(2, purpose.getPrimaryPurpose());
                preparedStatement.setString(3, purpose.getTermination());
                preparedStatement.setString(4, purpose.getThirdPartyDis());
                preparedStatement.setInt(5, purpose.getThirdPartyId());
                preparedStatement.executeUpdate();
                log.info("Successfully added the purpose details to the database");
            } catch (SQLException e) {
                log.error("Database error. Could not add purpose details. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not add purpose details. - "+e.getMessage(),e);
            }

            String purposeIdQuery="SELECT A.PURPOSE_ID FROM PURPOSES AS A WHERE PURPOSE=?";
            try {
                preparedStatementPurposeId=connection.prepareStatement(purposeIdQuery);
                preparedStatement.setString(1,purpose.getPurpose());
                resultSet=preparedStatementPurposeId.executeQuery();
                resultSet.first();
                mapPurposeWithPurposeCategories(connection,resultSet.getInt(1),purpose.getPurposeCategoryDOArr());
                mapPurposeWithPersonalInfoCategories(connection,resultSet.getInt(1),purpose.getpiiCategoryArr());
            } catch (SQLException e) {
                log.error("Database error. Could not get purpose id. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get purpose id. - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,preparedStatementPurposeId,resultSet);
            }
        }
    }

    private void mapPurposeWithPurposeCategories(Connection connection,int purposeId,PurposeCategoryDO[]
            purposeCategories) throws DataAccessException{
        PreparedStatement preparedStatement=null;
        String query="INSERT INTO PURPOSE_MAP_PURPOSE_CAT(PURPOSE_ID,PURPOSE_CAT_ID)\n" +
                "VALUES (?,?) ON DUPLICATE KEY UPDATE PURPOSE_CAT_ID=?;";
        try {
            for (PurposeCategoryDO purposeCategory:purposeCategories) {
                preparedStatement=connection.prepareStatement(query);
                preparedStatement.setInt(1, purposeId);
                preparedStatement.setInt(2,purposeCategory.getPurposeCatId());
                preparedStatement.setInt(3,purposeCategory.getPurposeCatId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("Database error. Could not map purpose to purpose category. - "+e.getMessage(),e);
            throw new DataAccessException("Database error. Could not map purpose to purpose category. - "+e
                    .getMessage(),e);
        } finally {
            DBUtils.closeAllConnections(connection,preparedStatement);
        }
    }

    private void mapPurposeWithPersonalInfoCategories(Connection connection,int purposeId,PiiCategoryDO[]
            piiCategories) throws DataAccessException{
        PreparedStatement preparedStatement=null;
        String query="INSERT INTO PURPOSE_MAP_PII_CAT(PURPOSE_ID, PII_CAT_ID) VALUES (?,?) ON DUPLICATE KEY UPDATE " +
                "PII_CAT_ID=?;";
        try {
            for(PiiCategoryDO piiCategory:piiCategories) {
                preparedStatement=connection.prepareStatement(query);
                preparedStatement.setInt(1, purposeId);
                preparedStatement.setInt(2,piiCategory.getPiiCatId());
                preparedStatement.setInt(3,piiCategory.getPiiCatId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("Database error. Could not map purpose to personally identifiable info category. - "+e
                    .getMessage(),e);
            throw new DataAccessException("Database error. Could not map purpose to personally identifiable info " +
                    "category. - "+e.getMessage(),e);
        } finally {
            DBUtils.closeAllConnections(connection,preparedStatement);
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
            PreparedStatement preparedStatement=null;

            String query = SQLQueries.PURPOSE_DETAILS_UPDATE_QUERY;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, purpose.getPurpose());
                preparedStatement.setString(2, purpose.getPrimaryPurpose());
                preparedStatement.setString(3, purpose.getTermination());
                preparedStatement.setString(4, purpose.getThirdPartyDis());
                preparedStatement.setInt(5, purpose.getThirdPartyId());
                preparedStatement.setInt(6, purpose.getPurposeId());
                preparedStatement.executeUpdate();
                //--have to delete all the mappings first then insert again..coz there will be scenarios like delete
                // one and add another one..to address that I use delete and then insert..
                deleteMappingsWithPurpose(connection,purpose.getPurposeId());
                mapPurposeWithPurposeCategories(connection,purpose.getPurposeId(),purpose.getPurposeCategoryDOArr());
                mapPurposeWithPersonalInfoCategories(connection,purpose.getPurposeId(),purpose.getpiiCategoryArr());
                log.info("Successfully updated the purpose details to the database.");
            } catch (SQLException e) {
                log.error("Database error. Could not update purpose details. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not update purpose details. - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement);
            }
        }
    }

    private void deleteMappingsWithPurpose(Connection connection,int purposeId) throws DataAccessException {
        deletePurposeCatMapWithPurpose(connection,purposeId);
        deletePersonalInfoCatMapWithPurpose(connection,purposeId);
    }

    private void deletePurposeCatMapWithPurpose(Connection connection,int purposeId) throws DataAccessException{
        PreparedStatement deletePurposeCatStat=null;
        String deletePurposeCat="DELETE FROM PURPOSE_MAP_PURPOSE_CAT WHERE PURPOSE_ID=?";
        try {
            deletePurposeCatStat=connection.prepareStatement(deletePurposeCat);
            deletePurposeCatStat.setInt(1,purposeId);
            deletePurposeCatStat.executeUpdate();
            deletePersonalInfoCatMapWithPurpose(connection,purposeId);
        } catch (SQLException e) {
            log.error("Database error. Could not delete purpose category. - "+e.getMessage(),e);
            throw new DataAccessException("Database error. Could not delete purpose categories. - "+e.getMessage(),e);
        } finally {
            DBUtils.closeAllConnections(connection,deletePurposeCatStat);
        }
    }

    private void deletePersonalInfoCatMapWithPurpose(Connection connection,int purposeId)throws DataAccessException{
        PreparedStatement deletePersonalInfoCatStat=null;
        String deletePersonalInfoCat="DELETE FROM PURPOSE_MAP_PII_CAT WHERE PURPOSE_ID=?";
        try {
            deletePersonalInfoCatStat=connection.prepareStatement(deletePersonalInfoCat);
            deletePersonalInfoCatStat.setInt(1,purposeId);
            deletePersonalInfoCatStat.executeUpdate();
        } catch (SQLException e) {
            log.error("Database error. Could not delete personally identifiable categories. - "+e.getMessage(),e);
            throw new DataAccessException("Database error. Could not delete personally identifiable categories. - "+e
                    .getMessage(),e);
        } finally {
            DBUtils.closeAllConnections(connection,deletePersonalInfoCatStat);
        }
    }

    /**
     * This method adds services to the database
     *
     * @param service
     * @throws DataAccessException
     * @override ServicesDAO to add service details to the data store
     */
    @Override
    public void addServiceDetails(ServicesDO service) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement=null;

            String query = "INSERT INTO user_consent.SERVICES(SERVICE_DESCRIPTION) VALUES(?);";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, service.getServiceDescription());
                preparedStatement.executeUpdate();
                int serviceId=getServiceIdByName(connection,service.getServiceDescription());
                mapServiceWithPurposes(connection,serviceId,service);
                log.info("Successfully added service details to the database");
            } catch (SQLException e) {
                log.error("Database error. Could not add service details to the database. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not add service details to the database. - "+e
                        .getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement);
            }
        }
    }

    private int getServiceIdByName(Connection connection,String serviceName) throws DataAccessException{
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        int serviceId;
        String query="SELECT SERVICE_ID FROM SERVICES WHERE SERVICE_DESCRIPTION=?";
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,serviceName);
            resultSet=preparedStatement.executeQuery();
            resultSet.first();
            serviceId=resultSet.getInt(1);
        } catch (SQLException e) {
            log.error("Database error. Could not get the service id. - "+e.getMessage(),e);
            throw new DataAccessException("Database error. Could not get the service id. - "+e.getMessage(),e);
        }
        return serviceId;
    }

    private void mapServiceWithPurposes(Connection connection,int serviceId,ServicesDO services) throws
            DataAccessException{
        PreparedStatement preparedStatement=null;
        String query="INSERT INTO SERVICE_MAP_PURPOSE(SERVICE_ID,PURPOSE_ID) VALUES(?,?);";
        try {
            for(PurposeDetailsDO purpose:services.getPurposeDetailsArr()) {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1,serviceId);
                preparedStatement.setInt(2,purpose.getPurposeId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("Database error. Could not map service with purposes. - "+e.getMessage(),e);
            throw new DataAccessException("Database error. Could not map service with purposes. - "+e.getMessage(),e);
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
            PreparedStatement preparedStatement=null;

            String query = "UPDATE user_consent.SERVICES SET SERVICE_DESCRIPTION=? WHERE SERVICE_ID=?;";

            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, service.getServiceDescription());
                preparedStatement.setInt(2,service.getServiceId());
                preparedStatement.executeUpdate();
                deleteServiceWithPurposesMap(connection,service.getServiceId());
                mapServiceWithPurposes(connection,service.getServiceId(),service);
                log.info("Successfully updated the service details to the database");
            } catch (SQLException e) {
                log.error("Database error. Could not update service details. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not update service details. - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement);
            }
        }
    }

    private void deleteServiceWithPurposesMap(Connection connection,int serviceId) throws DataAccessException{
        PreparedStatement preparedStatement=null;
        String query="DELETE FROM SERVICE_MAP_PURPOSE WHERE SERVICE_ID=?";
        try {
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,serviceId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Database error. Could not delete the service mapping with purposes. - "+e.getMessage(),e);
            throw new DataAccessException("Database error. Could not delete the service mapping with purposes. - "+e
                    .getMessage(),e);
        } /*finally {
            DBUtils.closeAllConnections(connection,preparedStatement);
        }*/
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
            PreparedStatement preparedStatement=null;

            String query = "UPDATE user_consent.TRANSACTION_DETAILS SET PII_PRINCIPAL_ID=? WHERE SGUID=?;";

            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, consentDO.getPiiPrincipalId());
                preparedStatement.setString(2, consentDO.getSGUID());
                preparedStatement.executeUpdate();
                log.info("Successfully updated the user name(subject name) to the database.");
            } catch (SQLException e) {
                log.error("Database error. Could not update user name(subject name). - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not update user name(subject name). - "+e
                        .getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement);
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
            PreparedStatement preparedStatement=null;

            String query = SQLQueries.CONSENT_BY_USER_REVOKE_QUERY;
            try {
                preparedStatement = connection.prepareStatement(query);
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
                log.info("Successfully revoked the user consent from the database");
            } catch (SQLException e) {
                log.error("Database error. Could not revoke consent. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not revoke consent. - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement);
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
    private String getPurposeTerminationDays(int purposeId, String consentedTime) throws DataAccessException {
        String exactTermination = null;
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            String query = "SELECT TERMINATION FROM user_consent.PURPOSES WHERE PURPOSE_ID=?";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, purposeId);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateFormat.parse(consentedTime));
                cal.add(Calendar.DATE, resultSet.getInt(1));
                exactTermination = dateFormat.format(cal.getTime());
            } catch (SQLException e) {
                log.error("Database error. Could not get purpose termination days. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get purpose termination days. - "+e
                        .getMessage(),e);
            } catch (ParseException e) {
                log.error("Time error. consented time is not in required date format");
                throw new DataAccessException("Error occurred while parsing the consented date.");
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
            }
        }
        return exactTermination;
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
            PreparedStatement preparedStatement=null;

            String query = "INSERT INTO RECEIPT_TRACKER(CONSENT_RECEIPT_ID,CONSENT_RECEIPT,STATUS,SGUID,RECEIPT_TIMESTAMP)" +
                    "VALUES(?,?,?,?,?)";
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, consentReceiptId);
                preparedStatement.setString(2, jsonJWT);
                preparedStatement.setString(3, "Generated");
                preparedStatement.setString(4, SGUID);
                preparedStatement.setString(5, timestamp);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
               log.error("Database error. Could not add the tracking details of the consent receipt. - "+e
                       .getMessage(),e);
               throw new DataAccessException("Database error. Could not add the tracking details of the consent " +
                        "receipt. - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement);
            }
        }
    }

    @Override
    public List<PiiCategoryDO> getPersonalInfoCatForConfig() throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            String query = "SELECT * FROM user_consent.PII_CATEGORY;";
            try {
                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
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
                log.error("Database error. Could not get the personally identifiable info categories. - "+e
                        .getMessage(),e);
                throw new DataAccessException("Database error. Could not get the personally identifiable info " +
                        "categories. - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
            }
        }
        return null;
    }

    @Override
    public List<PurposeDetailsDO> getPurposesForConfig() throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            String query = "SELECT A.*,B.THIRD_PARTY_NAME FROM user_consent.PURPOSES AS A,user_consent.THIRD_PARTY AS" +
                    " B WHERE A.THIRD_PARTY_ID=B.THIRD_PARTY_ID;";
            try {
                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
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
                log.error("Database error. Could not get purpose details for config. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get purpose details for config. - "+e
                        .getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
            }
        }
        return null;
    }

    private List<PurposeCategoryDO> getPurposeCatsForPurposeConf(int purposeId) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            String query = SQLQueries.PURPOSE_CATS_FOR_PURPOSE_CONF_QUERY;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, purposeId);
                resultSet = preparedStatement.executeQuery();
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
                log.error("Database error. Could not get purpose categories. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get purpose categories. - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
            }
        }
        return null;
    }

    private List<PiiCategoryDO> getPersonalIdentifyCatForPurposeConf(int purposeId) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            String query = SQLQueries.PERSONALLY_IDENTIFIABLR_CAT_FOR_PURPOSE_CONF_QUERY;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, purposeId);
                resultSet = preparedStatement.executeQuery();
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
                log.error("Database error. Could not get personally identifiable categories. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get personally identifiable categories. - " +
                        ""+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
            }
        }
        return null;
    }

    @Override
    public List<ServicesDO> getServicesForConf() throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            String query = SQLQueries.SERVICES_FOR_CONF_QUERY;
            try {
                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
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
                log.error("Database error. Could not get services for config. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get services for config. - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
            }
        }
        return null;
    }

    private List<PurposeDetailsDO> getPurposeDetailsForServiceConf(int serviceId) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            String query = SQLQueries.PURPOSE_DETAILS_FOR_SERVICE_CONF_QUERY;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, serviceId);
                resultSet = preparedStatement.executeQuery();
                List<PurposeDetailsDO> purposeDetailsDOList = new ArrayList<>();
                while (resultSet.next()) {
                    PurposeDetailsDO purposeDetailsDO = new PurposeDetailsDO();
                    purposeDetailsDO.setPurposeId(resultSet.getInt(2));
                    purposeDetailsDO.setPurpose(resultSet.getString(3));
                    purposeDetailsDOList.add(purposeDetailsDO);
                }
                return purposeDetailsDOList;
            } catch (SQLException e) {
                log.error("Database error. Could not get purpose details for service config. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get purpose details for service config. - " +
                        ""+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
            }
        }
        return null;
    }

    @Override
    public List<ServicesDO> getServicesForUserView(String subjectName) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            String query = SQLQueries.SERVICES_FOR_USER_VIEW_QUERY;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, subjectName);
                resultSet = preparedStatement.executeQuery();
                List<ServicesDO> servicesDOList = new ArrayList<>();
                while (resultSet.next()) {
                    ServicesDO servicesDO = new ServicesDO();
                    servicesDO.setServiceId(resultSet.getInt(1));
                    servicesDO.setServiceDescription(resultSet.getString(2));

                    List<Integer> purposeIdList = getPurposeIdByUserByService(resultSet.getString(4), resultSet.getInt
                            (1));
                    List<PurposeDetailsDO> purposeDetailsDOList = new ArrayList<>();
                    for (int i = 0; i < purposeIdList.size(); i++) {
                        PurposeDetailsDO purpose = getPurposeDetailsByPurposeId(purposeIdList.get(i));
                        purposeDetailsDOList.add(purpose);
                    }
                    servicesDO.setPurposeDetails(purposeDetailsDOList.toArray(new PurposeDetailsDO[0]));
                    servicesDOList.add(servicesDO);
                }
                return servicesDOList;
            } catch (SQLException e) {
                log.error("Database error. Could not get services. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get services. - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
            }
        }
        return null;
    }

    private List<Integer> getPurposeIdByUserByService(String sguid, int serviceId) throws DataAccessException {
        if (dbConnect.connect()) {
            Connection connection = dbConnect.getConnection();
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            String query = SQLQueries.PURPOSE_ID_BY_USER_BY_SERVICE_QUERY;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, serviceId);
                preparedStatement.setString(2, sguid);
                resultSet = preparedStatement.executeQuery();

                List<Integer> purposeIdList = new ArrayList<>();
                while (resultSet.next()) {
                    int i = resultSet.getInt(3);
                    purposeIdList.add(i);
                }
                return purposeIdList;
            } catch (SQLException e) {
                log.error("Database error. Could not get purpose IDs. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get purpose IDs. - "+e.getMessage(),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
            }
        }
        return null;
    }

    public List<PurposeCategoryDO> getPurposeCategories() throws DataAccessException{
        List<PurposeCategoryDO> purposeCategoryList=new ArrayList<>();
        if(dbConnect.connect()){
            Connection connection=dbConnect.getConnection();
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            String query="SELECT * FROM PURPOSE_CATEGORY;";

            try {
                preparedStatement=connection.prepareStatement(query);
                resultSet=preparedStatement.executeQuery();
                while (resultSet.next()){
                    PurposeCategoryDO purposeCategory=new PurposeCategoryDO();
                    purposeCategory.setPurposeCatId(resultSet.getInt(1));
                    purposeCategory.setPurposeCatShortCode(resultSet.getString(2));
                    purposeCategory.setPurposeCatDes(resultSet.getString(3));
                    purposeCategoryList.add(purposeCategory);
                }
            } catch (SQLException e) {
                log.error("Database error. Could not get the purpose categories. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not get the purpose categories. - "+e.getMessage
                        (),e);
            } finally {
                DBUtils.closeAllConnections(connection,preparedStatement,resultSet);
            }
        }
        return purposeCategoryList;
    }

    public void addPurposeCategory(PurposeCategoryDO purposeCategory) throws DataAccessException{
        if(dbConnect.connect()){
            Connection connection=dbConnect.getConnection();
            PreparedStatement preparedStatement=null;
            ResultSet resultSet=null;

            String query="INSERT INTO PURPOSE_CATEGORY (PURPOSE_CAT_SHORT_CODE, PURPOSE_CAT_DESCRIPTION)\n" +
                    "VALUES (?,?);";

            try {
                preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1,purposeCategory.getPurposeCatShortCode());
                preparedStatement.setString(2,purposeCategory.getPurposeCatDes());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                log.error("Database error. Could not add purpose category. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Colud not add purpose category. - "+e.getMessage(),e);
            }
        }
    }

    public void updatePurposeCategory(PurposeCategoryDO purposeCategory) throws DataAccessException{
        if(dbConnect.connect()){
            Connection connection=dbConnect.getConnection();
            PreparedStatement preparedStatement=null;

            String query=SQLQueries.PURPOSE_CATEGORY_UPDATE_QUERY;

            try {
                preparedStatement=connection.prepareStatement(query);
                preparedStatement.setString(1,purposeCategory.getPurposeCatShortCode());
                preparedStatement.setString(2,purposeCategory.getPurposeCatDes());
                preparedStatement.setInt(3,purposeCategory.getPurposeCatId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                log.error("Database error. Could not update purpose category. - "+e.getMessage(),e);
                throw new DataAccessException("Database error. Could not update purpose category. - "+e.getMessage(),e);
            }
        }
    }

//    public

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
