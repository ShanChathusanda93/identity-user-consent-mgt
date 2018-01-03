package org.wso2.identity.carbon.user.consent.mgt.backend.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
    private static Log log = LogFactory.getLog(DBUtils.class);

    public static void closeAllConnections(Connection connection,PreparedStatement preparedStatementOne,
                                           PreparedStatement preparedStatementTwo,ResultSet resultSet){
        closeDbConnection(connection);
        closePreparedStat(preparedStatementOne);
        closePreparedStat(preparedStatementTwo);
        closeResultSet(resultSet);
    }

    public static void closeAllConnections(Connection connection, PreparedStatement preparedStatement, ResultSet
            resultSet) {
        closeDbConnection(connection);
        closePreparedStat(preparedStatement);
        closeResultSet(resultSet);
    }

    public static void closeAllConnections(Connection connection,PreparedStatement preparedStatement){
        closeDbConnection(connection);
        closePreparedStat(preparedStatement);
    }

    public static void closeAllConnections(PreparedStatement preparedStatement,ResultSet resultSet){
        closePreparedStat(preparedStatement);
        closeResultSet(resultSet);
    }

    public static void closeAllConnections(PreparedStatement preparedStatement){
        closePreparedStat(preparedStatement);
    }

    private static void closeDbConnection(Connection connection) {
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Database error. Could not close the db connection. Continue with others. - " + e.getMessage(), e);
            }
        }
    }

    private static void closePreparedStat(PreparedStatement preparedStatement) {
        if(preparedStatement!=null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.error("Database error. Could not close the prepared statement. Continue with others. - " + e.getMessage()
                        , e);
            }
        }
    }

    private static void closeResultSet(ResultSet resultSet) {
        if(resultSet!=null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.error("Database error. Could not close the result set. Continue with others. - " + e.getMessage(), e);
            }
        }
    }
}