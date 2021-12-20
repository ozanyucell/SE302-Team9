import java.util.ArrayList;

public class Tree {
    private String familyName;
    private Person familyMember;
    private ArrayList<Person> members;
    private String about;

    Tree() {
        this.familyName = "Unknown";
        this.about = "No information.";
    }

    Tree(String familyName, String about) {
        setFamilyName(familyName);
        setAbout(about);
    }

    public void setFamilyName(String familyName) { this.familyName = familyName; }

    public void setFamilyMember(Person familyMember) { this.familyMember = familyMember; }

    public void setMembers(ArrayList<Person> members) { this.members = members; }

    public void setAbout(String about) { this.about = about; }

    public String getFamilyName() { return familyName; }

    public Person getFamilyMember() { return familyMember; }

    public ArrayList<Person> getMembers() { return members; }

    public String getAbout() { return about; }

    public void printTree() {
        System.out.println("-----------------------------");
        System.out.println("Family name: " + getFamilyName());
        System.out.println(getMembers());
        System.out.println("\nAbout\n" + getAbout());
        System.out.println("-----------------------------");
    }
}
