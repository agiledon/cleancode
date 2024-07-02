package zhangyi.cleancode.slap.service;

import zhangyi.cleancode.slap.entity.Customer;
import zhangyi.cleancode.slap.entity.Training;
import zhangyi.cleancode.slap.infrastructure.DatabasePool;

import java.sql.*;
import java.util.List;

public class TrainingService {
    private DatabasePool dbPool;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    private boolean transactionState;

    public void subscribe(List<Training> trainings, Customer customer) throws SQLException {
        initTransaction();
        try {
            enableTransaction();

            // business logic
            for (Training training : trainings) {
                addTrainingItem(customer, training);
            }
            addOrder(customer, trainings);

            commitTransaction();
        } catch (SQLException sqlx) {
            rollbackTransaction();
            throw sqlx;
        } finally {
            cleanTransaction();
        }
    }

    private void cleanTransaction() {
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

    private void rollbackTransaction() throws SQLException {
        // rollback transaction
        connection.rollback();
    }

    private void commitTransaction() throws SQLException {
        // commit transaction
        connection.commit();
    }

    private void enableTransaction() throws SQLException {
        // enable transaction
        statement = connection.createStatement();
        transactionState = connection.getAutoCommit();
        connection.setAutoCommit(false);
    }

    private void initTransaction() {
        // initial the related resources
        connection = null;
        preparedStatement = null;
        statement = null;
        resultSet = null;
        transactionState = false;
    }

    private void addOrder(Customer customer, List<Training> trainings) {


    }

    private void addTrainingItem(Customer customer, Training training) {


    }
}
