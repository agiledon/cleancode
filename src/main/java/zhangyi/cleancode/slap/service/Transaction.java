package zhangyi.cleancode.slap.service;

import zhangyi.cleancode.slap.infrastructure.DatabasePool;

import java.sql.*;

public class Transaction {
    DatabasePool dbPool;
    Connection connection;
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;
    boolean transactionState;

    public Transaction() {
    }

    void cleanTransaction() {
        // clean the related resources
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
        // rollback transaction
        connection.rollback();
    }

    void commitTransaction() throws SQLException {
        // commit transaction
        connection.commit();
    }

    void enableTransaction() throws SQLException {
        // enable transaction
        statement = connection.createStatement();
        transactionState = connection.getAutoCommit();
        connection.setAutoCommit(false);
    }

    void initTransaction() {
        // initial the related resources
        connection = null;
        preparedStatement = null;
        statement = null;
        resultSet = null;
        transactionState = false;
    }
}