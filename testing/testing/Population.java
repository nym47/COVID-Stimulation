package testing;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class Population implements Serializable {
    private int numberOfPopulation;
    private int numberOfInfected;

    private int numberOfNewInfected, numberOfNewCured;

    private ArrayList<Person> population;

    // Constructor for the first day
    public Population(int numberOfPopulation, int numberOfInfected) {
        this.numberOfPopulation = numberOfPopulation;
        numberOfNewInfected = this.numberOfInfected = numberOfInfected;
        numberOfNewCured = 0;

        population = new ArrayList<>();

        int i = 0;
        // First add infected people
        for (; i < numberOfInfected; i++) {
            population.add(new InfectedPerson());
        }
        // After infected add healthy people
        for (; i < numberOfPopulation; i++) {
            population.add(new HealthyPerson());
        }

        // Serialize population to file day1.ser
        savePrevious(1);
    }

    // Constructor for the other days
    public Population(int day) {
        numberOfNewCured = numberOfNewInfected = 0;

        population = new ArrayList<>();
        // Read population from previous day
        Population previous = readPrevious(day - 1);

        this.numberOfPopulation = previous.numberOfPopulation;

        // Sort population, first infected, then healthy
        Collections.sort(previous.population, (o1, o2) -> {
            if (o1 instanceof InfectedPerson && o2 instanceof HealthyPerson) {
                return -1;
            } else {
                return 1;
            }
        });

        int i;
        // Calculate number of new infected people
        int toInfect = (int) (0.2 * previous.numberOfInfected);
        int toInfectTemp = toInfect;
        numberOfNewInfected = toInfect;

        // First infect certain number of healthy people
        for (i = previous.numberOfInfected; i < numberOfPopulation; i++) {
            if (toInfect > 0) {
                population.add(previous.population.get(i).test(1));
                toInfect--;
            } else {
                population.add(previous.population.get(i).test(0));
            }
        }

        // Test infected people to check if there are healthy people
        for (i = 0; i < previous.numberOfInfected; i++) {
            Person p = previous.population.get(i).test(1);
            if (p instanceof HealthyPerson) {
                numberOfNewCured++;
            }
            population.add(p);
        }

        // New number of infected people
        this.numberOfInfected = previous.numberOfInfected + toInfectTemp;
    }

    public int getNumberOfNewInfected() {
        return numberOfNewInfected;
    }

    public void setNumberOfNewInfected(int numberOfNewInfected) {
        this.numberOfNewInfected = numberOfNewInfected;
    }

    public int getNumberOfNewCured() {
        return numberOfNewCured;
    }

    public void setNumberOfNewCured(int numberOfNewCured) {
        this.numberOfNewCured = numberOfNewCured;
    }

    // De-serialization of population from file
    public static Population readPrevious(int day) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("day" + day + ".ser"))) {
            return (Population) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Serialization of population to file
    public void savePrevious(int day) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("day" + day + ".ser"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfPopulation() {
        return numberOfPopulation;
    }

    public void setNumberOfPopulation(int numberOfPopulation) {
        this.numberOfPopulation = numberOfPopulation;
    }

    public int getNumberOfInfected() {
        return numberOfInfected;
    }

    public void setNumberOfInfected(int numberOfInfected) {
        this.numberOfInfected = numberOfInfected;
    }
}