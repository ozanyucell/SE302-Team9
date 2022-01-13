import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String id;
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
        setId(name + " " + surname + " - " + Modification.publicMemberID);
        this.relation = new Relation();
        Modification.publicMemberID++;
    }

    public void setId(String id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setSurname(String surname) { this.surname = surname; }

    public void setAge(int age) { this.age = age; }

    public void setGender(String gender) { this.gender = gender; }

    public void setBornDate(String bornDate) { this.bornDate = bornDate; }

    public void setAbout(String about) { this.about = about; }

    public void setMother(Person mother) {
        relation.setMother(mother);
    }

    public void setFather(Person father) {
        relation.setFather(father);
    }
    public void setPartner(Person partner) {
        relation.setPartner(partner);
    }
    public void setChildren(Person children) {
        relation.setChildren(children);
    }

    public String getId() { return id; }

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
        System.out.println("İsim: " + getName());
        System.out.println("Soyisim: " + getSurname());
        System.out.println("Yaş: " + getAge());
        System.out.println("Cinsiyet: " + getGender());
        System.out.println("Doğum Tarihi: " + getBornDate());
        System.out.println("\nHakkında\n" + getAbout());
        System.out.println("-----------------------------");
    }
}
