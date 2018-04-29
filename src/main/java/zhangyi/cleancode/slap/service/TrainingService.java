package zhangyi.cleancode.slap.service;

import zhangyi.cleancode.slap.entity.Customer;
import zhangyi.cleancode.slap.entity.Training;

import java.sql.*;
import java.util.List;

public class TrainingService {
    private final TransactionScope transactionScope = new TransactionScope();
    private final Command command = new Command(this);

    public void subscribe(List<Training> trainings, Customer customer) throws SQLException {
        transactionScope.setupTransaction();
        try {
            transactionScope.beginTransaction();

            command.execute(trainings, customer);

            transactionScope.commitTransaction();
        } catch (SQLException sqlx) {
            transactionScope.rollbackTransaction();
            throw sqlx;
        } finally {
            transactionScope.teardownTransaction();
        }
    }

    private void addOrder(Customer customer, List<Training> trainings) {


    }

    private void addTrainingItem(Customer customer, Training training) {


    }

    public static class Command {
        private final TrainingService trainingService;

        public Command(TrainingService trainingService) {
            this.trainingService = trainingService;
        }

        private void execute(List<Training> trainings, Customer customer) {
            for (Training training : trainings) {
                trainingService.addTrainingItem(customer, training);
            }
            trainingService.addOrder(customer, trainings);
        }
    }
}
