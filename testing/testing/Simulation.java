package testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Math.atan;

public class Simulation {
    private static final double infectionRate = 0.2;
    private static boolean authorized = false;
    private static HashMap<String, String> credentials = new HashMap<>();
    private static ArrayList<String> information = new ArrayList<>();
    // Adding username and password ti admins
    static {
        credentials.put("admin1", "admin");
        credentials.put("admin2", "admin");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int day = 1;

        System.out.print("Input number of people:");
        int numOfPeople = scanner.nextInt();
        System.out.print("Input number of infected people:");
        int numOfInfectedPeople = scanner.nextInt();

        // Create new population
        Population population = new Population(numOfPeople, numOfInfectedPeople);

        int totalInfected = population.getNumberOfInfected(), totalCured = 0;
        int choice = 1;
        boolean exit = false;
        while(!exit){
            System.out.println("\t--- Day " + day + " ---");
            printMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    // Print information for current day
                    double percent = (double)(population.getNumberOfInfected())/population.getNumberOfPopulation() * 100;
                    System.out.println("Infected percentage " + percent);
                    System.out.println("Healthy percentage " + (100 - percent));

                    System.out.print("Input the number of the day to calculate slope: ");
                    int slopeDay = scanner.nextInt();
                    Population tmp = Population.readPrevious(slopeDay);

                    double slope = atan((numOfInfectedPeople - tmp.getNumberOfInfected()) - (day - slopeDay));
                    System.out.println("Slope of the pandemic " + slope);
                    break;
                case 2:
                    // Generate information for the next day
                    population.savePrevious(day);
                    day++;
                    population = new Population(day);

                    totalCured += population.getNumberOfNewCured();
                    totalInfected += population.getNumberOfInfected();
                    break;
                case 3:
                    // Generate report for the user
                    System.out.println("Total number of infected is " + totalInfected);
                    System.out.println("Total number of cured is " + totalCured);
                    break;
                case 4:
                    // Log in as administrator and see the report
                    System.out.print("Enter your username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();


                    // Check if admin username and password are correct
                    if(credentials.containsKey(username)){
                        if(credentials.get(username).equals(password)){
                            authorized = true;
                            System.out.println(String.format("%-10s %-10s %-10s %-10s", "Day", "Infected", "Healthy", "Phase"));

                            Population temp = Population.readPrevious(1);
                            System.out.println(String.format("%-10d %-10d %-10d %-10s", 1, temp.getNumberOfInfected(),
                                    (temp.getNumberOfPopulation() - temp.getNumberOfInfected()), "Spreading"));

                            int prev = temp.getNumberOfInfected();
                            for(int d = 2; d < day; d++){
                                temp = Population.readPrevious(d);

                                System.out.print(String.format("%-10d %-10d %-10d ", d, temp.getNumberOfInfected(),
                                        (temp.getNumberOfPopulation() - temp.getNumberOfInfected())));

                                if(prev < temp.getNumberOfInfected()){
                                    System.out.println("Spreading");
                                } else if(prev > temp.getNumberOfInfected()){
                                    System.out.println("Terminating");
                                } else {
                                    System.out.println("Flattening");
                                }
                                prev = temp.getNumberOfInfected();
                            }
                            System.out.println();

                            String newInput = "";
                            while(!newInput.equals("exit")){
                                System.out.println("Add new information about pandemic, input 'exit' for the end");
                                newInput = scanner.nextLine();
                                information.add(newInput);
                            }

                        } else{
                            System.out.println("Invalid password");
                        }
                    } else {
                        System.out.println("Invalid password");
                    }
                    break;
                case 5:
                    System.out.println("--- Information ---");
                    if(information.size() == 0){
                        System.out.println("No new information");
                    }
                    for(String info : information){
                        System.out.println(info);
                    }
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Unknown option");
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Print day info");
        System.out.println("2. Go to next day");
        System.out.println("3. Generate report");
        System.out.println("4. Log in as administrator");
        System.out.println("5. See information about pandemic");
        System.out.println("6. Exit");
    }


}
