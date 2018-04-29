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

    public void usingTransaction(Command command) throws SQLException {
        setupTransaction();
        try {
            beginTransaction();
            command.execute();
            commitTransaction();
        } catch (SQLException sqlx) {
            rollbackTransaction();
            throw sqlx;
        } finally {
            teardownTransaction();
        }
    }

    private void setupTransaction() {
        connection = null;
        preparedStatement = null;
        statement = null;
        resultSet = null;
        transactionState = false;
    }

    private void teardownTransaction() {
        try {
            connection.setAutoCommit(transactionState);
            dbPool.release(connection);
            if (statement != null) statement.close();
            if (preparedStatement != null) preparedStatement.close();
            if (resultSet != null) resultSet.close();
        } catch (SQLException ignored) {
        }
    }

    private void beginTransaction() throws SQLException {
        statement = connection.createStatement();
        transactionState = connection.getAutoCommit();
        connection.setAutoCommit(false);
    }

    private void commitTransaction() throws SQLException {
        connection.commit();
    }

    private void rollbackTransaction() throws SQLException {
        connection.rollback();
    }
}