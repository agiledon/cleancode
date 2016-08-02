package zhangyi.cleancode.slap.entity;

public class Training {
    private Course course;
    private int traineeCount;

    public Training(Course course, int traineeCount) {
        this.course = course;
        this.traineeCount = traineeCount;
    }

    public Course getCourse() {
        return course;
    }

    public int getTraineeCount() {
        return traineeCount;
    }

    public double amountFor() {
        double thisAmount = 0;
        switch (getCourse().getPriceCode()) {
            case Course.ENTERPRISE:
                if (getTraineeCount() > 15)
                    thisAmount += 2000 * getTraineeCount() * 0.9;
                else thisAmount += 2000 * getTraineeCount();
                break;
            case Course.COMMUNITY:
                if (getTraineeCount() > 15)
                    thisAmount += 1000 * getTraineeCount() * 0.8;
                else thisAmount += 1000 * getTraineeCount();
                break;
            case Course.COMMONWEAL:
                thisAmount += 100 * getTraineeCount();
                break;
        }
        return thisAmount;
    }
}
