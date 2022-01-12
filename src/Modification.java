import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Modification {
    static String rootDirectoryPath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\Family Tree Generator";
    public static Tree newCreatedTree;

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
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\Family Tree Generator\\path.config", false)));
        bufferedWriter.append(rootDirectoryPath);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    public static void createTree(JFrame frame, int width, int height) throws IOException {

        JFrame newFrame = new JFrame();
        newFrame.pack();
        newFrame.setSize(width, height);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(true);
        newFrame.setLayout(null);
        newFrame.setTitle("Create Tree");
        frame.setVisible(false);
        frame.dispose();
        JTextField textField;
        JTextField textField2;


        JPanel panel = new JPanel();
        panel.setBounds(10, 20, 300, 460);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        JPanel helperPanel = new JPanel();

        JLabel ctLabel = new JLabel("                                 CREATE TREE");
        ctLabel.setBounds(80, 40, 100, 15);
        panel.add(ctLabel);

        JLabel ctLabel1 = new JLabel("Family Name:");
        panel.add(ctLabel1);

        textField = new JTextField();
        panel.add(textField);

        JLabel ctLabel2 = new JLabel("About the family:");
        panel.add(ctLabel2);

        textField2 = new JTextField();
        panel.add(textField2);

        JLabel mainLabel = new JLabel("                                Root Member");
        panel.add(mainLabel);
        JLabel empty = new JLabel(" ");
        panel.add(mainLabel);

        JLabel NameLabel = new JLabel("Name:");
        panel.add(NameLabel);

        JTextField nameField = new JTextField();
        panel.add(nameField);

        JLabel surnameLabel = new JLabel("Surname:");
        panel.add(surnameLabel);
        JTextField surnameField = new JTextField();
        panel.add(surnameField);

        JLabel bornDLabel = new JLabel("Born Date:");
        panel.add(bornDLabel);
        JTextField bornDField = new JTextField();
        panel.add(bornDField);

        JLabel ageLabel = new JLabel("Age:");
        panel.add(ageLabel);
        JTextField ageField = new JTextField();
        panel.add(ageField);

        JLabel aboutLabel = new JLabel("About:");
        panel.add(aboutLabel);
        JTextField aboutField = new JTextField();
        panel.add(aboutField);

        JLabel genderLabel = new JLabel("Gender:");
        panel.add(genderLabel);
        JTextField genderField = new JTextField();
        panel.add(genderField);

        newFrame.add(panel);

        JButton contButton = new JButton("Continue");
        JButton backButton = new JButton("Back");
        panel.add(contButton);
        panel.add(backButton);

        contButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String familyName = textField.getText();
                String about = textField2.getText();
                String headName=null, headSurname=familyName, headGender=null, headBornDate=null, headAbout=null;
                String headAge = null;
                headName=nameField.getText();
                headSurname=surnameField.getText();
                headGender=genderField.getText();
                headBornDate=bornDField.getText();
                headAbout=aboutField.getText();
                headAge=ageField.getText(); // age is string atm
                System.out.println(headName + headAbout+ headSurname);
                //Person headNode = new Person(headName, headSurname, 0, headGender, headBornDate, headAbout);
                //newCreatedTree = new Tree(familyName, about, headNode);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Main.pressedBack(frame,newFrame);
            }
        });



        //pushTree(newTree, rootDirectoryPath + familyName + ".tree");

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

        // Relation newMemberRelation = new Relation();

        Person newMember = new Person(name, surname, age, gender, bornDate, about);
    }

    public static void pushTree(Tree tree, String createPath) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(createPath));
        oos.writeObject(tree);
        oos.close();
    }

    public static Tree pullTree(String filePath) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        Tree importedTree = (Tree) ois.readObject();
        ois.close();
        return importedTree;
    }

    public static void editTree(){}

    public static void deleteTree(){}

    public static void mergeTrees(){}

}
