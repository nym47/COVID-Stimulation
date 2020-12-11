public class HealthyPerson extends Person {
    private boolean wasInfected;

    public HealthyPerson() {
        super();
        this.wasInfected = false;
    }

    public HealthyPerson(boolean wasInfected){
        super();
        this.wasInfected = wasInfected;
    }

    // Method to test infected person on covid19, if argument probability is greater than 0.5 person becomes infected
    @Override
    public Person test(double probability) {
        if(probability > 0.5){
            return new InfectedPerson();
        }
        return new HealthyPerson(wasInfected);
    }

    public boolean getWasInfected() {
        return wasInfected;
    }

    public void setWasInfected(boolean wasInfected) {
        this.wasInfected = wasInfected;
    }
}
