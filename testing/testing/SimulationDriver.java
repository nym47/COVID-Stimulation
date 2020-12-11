package testing;
//import Simulation;

public class SimulationDriver 
{
    public void main(String[] args) 
    {
        System.out.println("Simulation test started.");
        try
        { 
            Simulation test = new Simulation();
            System.out.println("Simulation test passed."); 
        } 
        catch(Exception ex) 
        { 
            System.out.println("Simulation test failed."); 
        } 
        System.out.println("Simulation test over.");
    }
}