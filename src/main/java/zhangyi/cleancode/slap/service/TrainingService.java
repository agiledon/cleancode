package zhangyi.cleancode.slap.service;

import zhangyi.cleancode.slap.entity.Customer;
import zhangyi.cleancode.slap.entity.Training;

import java.sql.*;
import java.util.List;

public class TrainingService {
    private final TransactionScope transactionScope = new TransactionScope();

    public void subscribe(List<Training> trainings, Customer customer) throws SQLException {
        transactionScope.setupTransaction();
        try {
            transactionScope.beginTransaction();

            for (Training training : trainings) {
                addTrainingItem(customer, training);
            }
            addOrder(customer, trainings);

            transactionScope.commitTransaction();
        } catch (SQLException sqlx) {
            transactionScope.rollbackTransaction();
            throw sqlx;
        } finally {
            transactionScope.teardownTransaction();
        }
    }

    private void teardownTransaction() {
        transactionScope.teardownTransaction();
    }

    private void rollbackTransaction() throws SQLException {
        transactionScope.rollbackTransaction();
    }

    private void commitTransaction() throws SQLException {
        transactionScope.commitTransaction();
    }

    private void beginTransaction() throws SQLException {
        transactionScope.beginTransaction();
    }

    private void setupTransaction() {
        transactionScope.setupTransaction();
    }

    private void addOrder(Customer customer, List<Training> trainings) {


    }

    private void addTrainingItem(Customer customer, Training training) {


    }
}
