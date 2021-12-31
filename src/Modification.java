import java.util.ArrayList;
import java.util.Scanner;

public class Modification {
    private ArrayList<Tree> trees;

    public void addTree(){
        String familyName, about;
        Scanner treeScan = new Scanner(System.in);

        System.out.print("Name: ");
        familyName = treeScan.next();
        System.out.print("\nSurname: ");
        about = treeScan.next();

        System.out.print("How many members?: ");  //this part is
        int memberCount = treeScan.nextInt();     //for testing,
        System.out.println();                     //can be removed with GUI

        for(int x=0; x < memberCount; x++){
            System.out.println();
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
        System.out.print("\nSurname: ");
        surname = memberScan.next();
        System.out.print("\nAge: ");
        age = memberScan.nextInt();
        System.out.print("\nGender: ");
        gender = memberScan.next();
        System.out.print("\nBorn Date: ");
        bornDate = memberScan.next();
        System.out.print("\nAbout: ");
        about = memberScan.next();

        Person newMember = new Person(name, surname, age, gender, bornDate, about);

        Tree tree = new Tree();
        tree.setMembers(newMember);
    }

    public void editTree(){}
    public void deleteTree(){}
    public void mergeTrees(){}

}
