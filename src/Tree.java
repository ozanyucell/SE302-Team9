import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Tree implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String familyName;
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

    public void setMembers(Person member) { this.members.add(member); }

    public void setAbout(String about) { this.about = about; }

    public String getFamilyName() { return familyName; }

    public ArrayList<Person> getMembers() { return this.members; }

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

    public void displayTree(JFrame frame, int width, int height){
        JFrame newFrame = new JFrame();
        newFrame.pack();
        newFrame.setSize(width, height);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(true);
        newFrame.setLayout(null);
        newFrame.setTitle("Tree Displayer");

        frame.setVisible(false);
        frame.dispose();

        Main.menuBar(newFrame, width);
        
        JLabel familyName = new JLabel("Family Name: " + getFamilyName());
        familyName.setBounds(10, 40, width, 15);
        newFrame.add(familyName);

        JLabel about = new JLabel("About Family: " + getAbout());
        about.setBounds(10, 80, width, 15);
        newFrame.add(about);

        JLabel members = new JLabel("Family Members: " + getMembers());
        members.setBounds(10, 120, width, 15);
        newFrame.add(members);
    }
}
