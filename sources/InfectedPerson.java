import java.util.Random;

public class InfectedPerson extends Person {
    private static final double probabilityToInfect = 0.3;
    private int numberOfTests;

    public InfectedPerson() {
        super();
        this.numberOfTests = 0;
    }

    public InfectedPerson(int numberOfTests){
        super();
        this.numberOfTests = numberOfTests;
    }

    // Method to test infected person on covid19, after 3 negative tests it becomes healthy person
    @Override
    public Person test(double junk) {
        Random rand = new Random();
        double random = rand.nextDouble();

        // Probability that person is negative first time is ((numberOfTests + 1) * probabilityToInfect) = 0.3, second time is 0.6 and third time is 0.9
        if(random < (numberOfTests + 1) * probabilityToInfect){
            // Third negative test, person is healthy
            if(++numberOfTests == 3){
                return new HealthyPerson(true);
            } else {
                return new InfectedPerson(numberOfTests);
            }
        } else {
            return new InfectedPerson(numberOfTests);
        }
    }

    public int getNumberOfTests() {
        return numberOfTests;
    }

    public void setNumberOfTests(int numberOfTests) {
        this.numberOfTests = numberOfTests;
    }
}
