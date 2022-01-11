import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.Scanner;

public class Modification {
    static String rootDirectoryPath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\Family Tree Generator";

    public static void startup() throws IOException {
        File directory = new File(rootDirectoryPath);
        if (!directory.isDirectory()) {

            directory.mkdir();
        }

        File pathConfig = new File(rootDirectoryPath+"\\path.config");
        if (!pathConfig.exists()) {
            pathConfig.createNewFile();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(rootDirectoryPath+"\\path.config")));
        String line = reader.readLine();
        if(line != null){
            rootDirectoryPath = line;
        }
    }

    public static void pathWriter() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\Family Tree Generator\\path.config", true)));
        bufferedWriter.append(rootDirectoryPath);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    public static Tree createTree() throws IOException {
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
            createMember(newTree);
            System.out.println();
        }
        String createFilePath = rootDirectoryPath + familyName + ".tree";
        pushTree(newTree, createFilePath);

        return newTree;
    }

    public static void createMember(Tree newTree){
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

    public static void pushTree(Tree tree, String createPath) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(createPath));
        oos.writeObject(tree);
        oos.close();
    }

    public static Tree pullTree(String filePath) throws IOException, ClassNotFoundException {
        Tree importedTree;
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        importedTree = (Tree) ois.readObject();
        ois.close();
        return importedTree;
    }

    public static void editTree(){}

    public static void deleteTree(){}

    public static void mergeTrees(){}

}
