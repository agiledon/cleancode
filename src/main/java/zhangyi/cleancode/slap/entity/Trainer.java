package zhangyi.cleancode.slap.entity;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private String name;
    private List<Training> trainings = new ArrayList<Training>();

    public Trainer(String name) {
        this.name = name;
    }

    public void addTraining(Training training) {
        trainings.add(training);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int points = 0;
        String result = "Subscription Record for " + getName() + "\n";

        for (Training training : trainings) {

            double thisAmount = training.amountFor();
            // add points for enterprise course
            if (training.getCourse().getPriceCode() == Course.ENTERPRISE) {
                points = points + 2;
            } else {
                points++;
            }
            //show figures
            result += "\t" + training.getCourse().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }

        //add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(points) +
                " points";
        return result;
    }

}
