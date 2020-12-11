import java.io.Serializable;

public abstract class Person implements Serializable {
    private static int id = 0;
    private final int personId;

    public Person() {
        this.personId = id++;
    }

    public int getPersonId() {
        return personId;
    }

    public abstract Person test(double probability);
}
