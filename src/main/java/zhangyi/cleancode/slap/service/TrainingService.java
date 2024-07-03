package zhangyi.cleancode.slap.service;

import zhangyi.cleancode.slap.entity.Customer;
import zhangyi.cleancode.slap.entity.Training;

import java.sql.*;
import java.util.List;

public class TrainingService {
    private final Transaction transaction = new Transaction();

    public void subscribe(List<Training> trainings, Customer customer) throws SQLException {
        transaction.executeWith(() -> {
            // business logic
            for (Training training : trainings) {
                addTrainingItem(customer, training);
            }
            addOrder(customer, trainings);
        });
    }

    private void addOrder(Customer customer, List<Training> trainings) {


    }

    private void addTrainingItem(Customer customer, Training training) {


    }
}
