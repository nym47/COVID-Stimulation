package testing;
//import InfectedPerson;

public class InfectedPersonDriver 
{
    public void main(String[] args) 
    {
        System.out.println("InfectedPerson test started.");
        try
        { 
            InfectedPerson test = new InfectedPerson();
            System.out.println("InfectedPerson test passed."); 
        } 
        catch(Exception ex) 
        { 
            System.out.println("InfectedPerson test failed."); 
        } 
        System.out.println("InfectedPerson test over.");
    }
}
