import java.util.ArrayList;

public class Tree {
    private String familyName;
    //private Person familyMember;
    private ArrayList<Person> members = new ArrayList<Person>();
    private String about;

    Tree() {
        setFamilyName("Unknown");
        setAbout("No information.");
    }

    Tree(String familyName, String about) {
        setFamilyName(familyName);
        setAbout(about);
    }

    public void setFamilyName(String familyName) { this.familyName = familyName; }

    //public void setFamilyMember(Person familyMember) { this.familyMember = familyMember; }

    public void setMembers(ArrayList<Person> members) { this.members = members; }

    public void setAbout(String about) { this.about = about; }

    public String getFamilyName() { return familyName; }

    //public Person getFamilyMember() { return familyMember; }

    public ArrayList<Person> getMembers() { return members; }

    public String getAbout() { return about; }

    //for testing
    public void printTree() {
        System.out.println("-----------------------------");
        System.out.println("Family name: " + getFamilyName());
        if(members==null) { System.out.println("This tree is empty!"); }
        else { System.out.println(getMembers()); }
        System.out.println("\nAbout\n" + getAbout());
        System.out.println("-----------------------------");
    }
}
