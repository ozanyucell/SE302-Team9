import java.util.ArrayList;
import java.util.Scanner;

public class Modification {
    protected ArrayList<Tree> trees = new ArrayList<Tree>();
    private ArrayList<Person> tempFamilyMembers = new ArrayList<Person>();

    public void addTree(){
        String familyName, about;
        Scanner treeScan = new Scanner(System.in);

        System.out.print("Family Name: ");
        familyName = treeScan.next();
        System.out.print("About Family: ");
        about = treeScan.next();

        System.out.println();
        System.out.print("How many members?: ");  //this part is
        int memberCount = treeScan.nextInt();     //for testing,
        System.out.println();                     //can be removed with GUI

        for(int x=0; x < memberCount; x++){

            addMember();
            System.out.println();
        }

        Tree newTree = new Tree(familyName, about);
        trees.add(newTree);
    }

    public void addMember(){
        String name, surname, gender, bornDate, about;
        int age;
        Scanner memberScan = new Scanner(System.in);

        System.out.print("Name: ");
        name = memberScan.next();
        System.out.print("Surname: ");
        surname = memberScan.next();
        System.out.print("Age: ");
        age = memberScan.nextInt();
        System.out.print("Gender: ");
        gender = memberScan.next();
        System.out.print("Born Date: ");
        bornDate = memberScan.next();
        System.out.print("About: ");
        about = memberScan.next();

        Person newMember = new Person(name, surname, age, gender, bornDate, about);

        tempFamilyMembers.add(newMember);

        Tree tree = new Tree();
        tree.setMembers(tempFamilyMembers);
    }

    public void editTree(){}
    public void deleteTree(){}
    public void mergeTrees(){}

}
