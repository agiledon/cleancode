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
        connection = null;
        preparedStatement = null;
        statement = null;
        resultSet = null;
        transactionState = false;
        try {
            statement = connection.createStatement();
            transactionState = connection.getAutoCommit();
            connection.setAutoCommit(false);
            for (Training training : trainings) {
                addTrainingItem(customer, training);
            }
            addOrder(customer, trainings);
            connection.commit();
        } catch (SQLException sqlx) {
            connection.rollback();
            throw sqlx;
        } finally {
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

    private void addOrder(Customer customer, List<Training> trainings) {


    }

    private void addTrainingItem(Customer customer, Training training) {


    }
}
