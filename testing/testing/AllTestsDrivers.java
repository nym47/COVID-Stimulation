package testing;
public class AllTestsDrivers 
{
    public static void main(String[] args)
        {
            System.out.println("All tests started.");
            try
            { 
                HealthyPersonDriver test1 = new HealthyPersonDriver();
                InfectedPersonDriver test2 = new InfectedPersonDriver();
                //PersonDriver test3 = new PersonDriver(); Person is abstract
                PopulationDriver test4 = new PopulationDriver();
                SimulationDriver test5 = new SimulationDriver();
                test1.main(args);
                test2.main(args);
                //test3.main(args);
                test4.main(args);
                test5.main(args);
                System.out.println("All tests passed."); 
            }
            catch(Exception ex) 
            { 
                System.out.println("All tests failed."); 
            } 
            System.out.println("All tests over.");
        }
}