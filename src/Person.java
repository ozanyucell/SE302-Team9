import java.util.ArrayList;

public class Person{
    private String name;
    private String surname;
    private int age;
    private String gender;
    private String bornDate;
    private String about;
    private Relation relation;

    Person() {
        setName("Unknown");
        setSurname("Unknown");
        setAge(0);
        setGender("Unknown");
        setBornDate("Unknown");
        setAbout("No information.");
    }

    Person(String name, String surname, int age, String gender, String bornDate, String about){
        setName(name);
        setSurname(surname);
        setAge(age);
        setGender(gender);
        setBornDate(bornDate);
        setAbout(about);

    }

    public void setName(String name) { this.name = name; }

    public void setSurname(String surname) { this.surname = surname; }

    public void setAge(int age) { this.age = age; }

    public void setGender(String gender) { this.gender = gender; }

    public void setBornDate(String bornDate) { this.bornDate = bornDate; }

    public void setAbout(String about) { this.about = about; }

    public void setRelation(Person mother, Person father, Person partner, ArrayList<Person> children) {
        this.relation = new Relation(mother, father, partner, children);
    }

    public String getName() { return name; }

    public String getSurname() { return surname; }

    public int getAge() { return age; }

    public String getGender() { return gender; }

    public String getBornDate() { return bornDate; }

    public String getAbout() { return about; }

    public Relation getRelation() { return relation; }

    //for testing
    public void printPerson(){
        System.out.println("-----------------------------");
        System.out.println("Name: " + getName());
        System.out.println("Surname: " + getSurname());
        System.out.println("Age: " + getAge());
        System.out.println("Gender: " + getGender());
        System.out.println("Born Date: " + getBornDate());
        System.out.println("\nAbout\n" + getAbout());
        System.out.println("-----------------------------");
    }
}
