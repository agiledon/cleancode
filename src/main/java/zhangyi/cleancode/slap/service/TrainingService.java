package zhangyi.cleancode.slap.service;

import zhangyi.cleancode.slap.entity.Customer;
import zhangyi.cleancode.slap.entity.Training;

import java.sql.*;
import java.util.List;

public class TrainingService {
    private final TransactionScope transactionScope = new TransactionScope();
    private final Command command = new InnerCommand(this);

    public void subscribe(List<Training> trainings, Customer customer) throws SQLException {
        transactionScope.usingTransaction(command);
    }

    private void addOrder(Customer customer, List<Training> trainings) {


    }

    private void addTrainingItem(Customer customer, Training training) {


    }

    public static class InnerCommand implements Command {
        private final TrainingService trainingService;
        private List<Training> trainings;
        private Customer customer;

        public InnerCommand(TrainingService trainingService) {
            this.trainingService = trainingService;
        }

        @Override
        public void execute() {
            for (Training training : this.trainings) {
                trainingService.addTrainingItem(this.customer, training);
            }
            trainingService.addOrder(this.customer, this.trainings);
        }
    }
}
