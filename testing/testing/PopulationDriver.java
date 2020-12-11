package testing;
//import Population;

public class PopulationDriver 
{
    public void main(String[] args) 
    {
        System.out.println("Population test started.");
        try
        { 
            Population test = new Population(100, 2);
            System.out.println("Population test passed."); 
        } 
        catch(Exception ex) 
        { 
            System.out.println("Population test failed."); 
        } 
        System.out.println("Population test over.");
    }
}