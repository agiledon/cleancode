package zhangyi.cleancode.slap.service;

import zhangyi.cleancode.slap.entity.Customer;
import zhangyi.cleancode.slap.entity.Training;

import java.sql.*;
import java.util.List;

public class TrainingService {
    private final Transaction transaction = new Transaction();

    public void subscribe(List<Training> trainings, Customer customer) throws SQLException {
        transaction.init();
        try {
            transaction.enable();

            // business logic
            for (Training training : trainings) {
                addTrainingItem(customer, training);
            }
            addOrder(customer, trainings);

            transaction.commit();
        } catch (SQLException sqlx) {
            transaction.rollback();
            throw sqlx;
        } finally {
            transaction.clean();
        }
    }

    private void addOrder(Customer customer, List<Training> trainings) {


    }

    private void addTrainingItem(Customer customer, Training training) {


    }
}
