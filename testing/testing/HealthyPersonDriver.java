package testing;

//import HealthyPerson;

public class HealthyPersonDriver 
{
    public void main(String[] args) 
    {
        System.out.println("HealthyPerson test started.");
        try
        { 
            HealthyPerson test  = new HealthyPerson();
            System.out.println("HealthyPerson test passed."); 
        } 
        catch(Exception ex) 
        { 
            System.out.println("HealthyPerson test failed."); 
        } 
        System.out.println("HealthyPerson test over.");
    }
}