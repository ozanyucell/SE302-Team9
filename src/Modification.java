import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Modification {
    private static ArrayList<Tree> trees = new ArrayList<Tree>();
    static HashSet<String> fileNames = new HashSet<String>();

    public static ArrayList<Tree> getTrees() { return trees; }

    public static Tree addTree(){
        String familyName, about;
        Scanner treeScan = new Scanner(System.in);

        System.out.print("Family Name: ");
        familyName = treeScan.next();
        System.out.print("About Family: ");
        about = treeScan.next();

        System.out.println();
        System.out.print("How many members?: ");  //this part is
        int memberCount = treeScan.nextInt();     //for testing,
        System.out.println();                     //can be replaced with GUI

        Tree newTree = new Tree(familyName, about);

        for(int x=0; x < memberCount; x++){
            addMember(newTree);
            System.out.println();
        }

        trees.add(newTree);

        return newTree;
    }

    public static void addMember(Tree newTree){
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

        newTree.setMembers(newMember);
    }

    public static void setFileNames(HashSet<String> fileNames) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("..\\test_area\\ftg_container\\file_names.txt")));
        String line = reader.readLine();

        while(line!=null) {
            fileNames.add(line);
            line = reader.readLine();
        }
    }

    public static void writeTree(Tree tree) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("..\\test_area\\ftg_container\\file_names.txt", true)));
        writer.append(tree.getFamilyName());
        writer.newLine();
        writer.flush();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("..\\test_area\\ftg_container\\"+ tree.getFamilyName() +".tree"));
        oos.writeObject(tree);
        oos.close();
    }

    public static void readTree(Tree tree) throws IOException, ClassNotFoundException {
        for (String fileName : fileNames){
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("..\\test_area\\ftg_container\\" + fileName + ".tree"));
            tree = (Tree) ois.readObject();
            trees.add(tree);
            ois.close();
        }
    }

    public static void editTree(){}

    public static void deleteTree(){}

    public static void mergeTrees(){}
}
