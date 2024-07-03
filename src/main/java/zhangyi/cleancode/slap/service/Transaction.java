package zhangyi.cleancode.slap.service;

import zhangyi.cleancode.slap.entity.Customer;
import zhangyi.cleancode.slap.entity.Training;
import zhangyi.cleancode.slap.infrastructure.DatabasePool;

import java.sql.*;
import java.util.List;

public class Transaction {
    private DatabasePool dbPool;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    private boolean transactionState;

    public Transaction() {
    }

    public void executeWith(TransactionSupport ts) throws SQLException {
        this.init();
        try {
            this.enable();
            ts.execute();
            this.commit();
        } catch (SQLException sqlx) {
            this.rollback();
            throw sqlx;
        } finally {
            this.clean();
        }
    }

    private void init() {
        // initial the related resources
        connection = null;
        preparedStatement = null;
        statement = null;
        resultSet = null;
        transactionState = false;
    }

    private void enable() throws SQLException {
        // enable transaction
        statement = connection.createStatement();
        transactionState = connection.getAutoCommit();
        connection.setAutoCommit(false);
    }

    private void commit() throws SQLException {
        // commit transaction
        connection.commit();
    }

    private void rollback() throws SQLException {
        // rollback transaction
        connection.rollback();
    }

    private void clean() {
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
}