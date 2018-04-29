package zhangyi.cleancode.slap.service;

import zhangyi.cleancode.slap.infrastructure.DatabasePool;

import java.sql.*;

public class TransactionScope {
    DatabasePool dbPool;
    Connection connection;
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;
    boolean transactionState;

    public TransactionScope() {
    }

    void teardownTransaction() {
        try {
            connection.setAutoCommit(transactionState);
            dbPool.release(connection);
            if (statement != null) statement.close();
            if (preparedStatement != null) preparedStatement.close();
            if (resultSet != null) resultSet.close();
        } catch (SQLException ignored) {
        }
    }

    void rollbackTransaction() throws SQLException {
        connection.rollback();
    }

    void commitTransaction() throws SQLException {
        connection.commit();
    }

    void beginTransaction() throws SQLException {
        statement = connection.createStatement();
        transactionState = connection.getAutoCommit();
        connection.setAutoCommit(false);
    }

    void setupTransaction() {
        connection = null;
        preparedStatement = null;
        statement = null;
        resultSet = null;
        transactionState = false;
    }
}