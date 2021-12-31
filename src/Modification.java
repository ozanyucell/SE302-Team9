import java.util.ArrayList;
import java.util.Scanner;

public class Modification {
    private ArrayList<Tree> trees;

    public void addTree(){}

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
        System.out.print("\nBorn date: ");
        bornDate = memberScan.next();
        System.out.print("\nAbout: ");
        about = memberScan.next();

        Person newMember = new Person(name, surname, age, gender, bornDate, about);
    }

    public void editTree(){}
    public void deleteTree(){}
    public void mergeTrees(){}

}
