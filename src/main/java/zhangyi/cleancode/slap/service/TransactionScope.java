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

    public void using(Command command) throws SQLException {
        setup();
        try {
            begin();
            command.execute();
            commit();
        } catch (SQLException sqlx) {
            rollback();
            throw sqlx;
        } finally {
            teardown();
        }
    }

    private void setup() {
        connection = null;
        preparedStatement = null;
        statement = null;
        resultSet = null;
        transactionState = false;
    }

    private void teardown() {
        try {
            connection.setAutoCommit(transactionState);
            dbPool.release(connection);
            if (statement != null) statement.close();
            if (preparedStatement != null) preparedStatement.close();
            if (resultSet != null) resultSet.close();
        } catch (SQLException ignored) {
        }
    }

    private void begin() throws SQLException {
        statement = connection.createStatement();
        transactionState = connection.getAutoCommit();
        connection.setAutoCommit(false);
    }

    private void commit() throws SQLException {
        connection.commit();
    }

    private void rollback() throws SQLException {
        connection.rollback();
    }
}