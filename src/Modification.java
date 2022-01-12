import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Modification {
    static String rootDirectoryPath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\Family Tree Generator";
    public static Tree newCreatedTree;
    public static String familyName;

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
                familyName = textField.getText();
                String about = textField2.getText();
                String headName, headSurname, headGender, headBornDate, headAbout;
                int headAge;
                headName=nameField.getText();
                headSurname=surnameField.getText();
                headGender=genderField.getText();
                headBornDate=bornDField.getText();
                headAbout=aboutField.getText();

                try {
                    headAge = parseInt(ageField.getText());

                    Person headNode = new Person(headName, headSurname, headAge, headGender, headBornDate, headAbout);
                    newCreatedTree = new Tree(familyName, about, headNode);

                    JFrame frame = new JFrame();
                    frame.pack();
                    frame.setSize(1366, 720);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setTitle("Tree Generator");
                    newFrame.setVisible(false);

                    JPanel inputPanel = new JPanel();
                    JPanel treePanel = new JPanel();

                    treePanel.setLayout(new FlowLayout());
                    treePanel = newCreatedTree.jTreeVisualiser();

                    frame.add(treePanel);

                    JSplitPane sl = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputPanel, treePanel);

                    JLabel nameLabel = new JLabel("  Name:");
                    inputPanel.add(nameLabel);

                    JTextField nameField = new JTextField();
                    inputPanel.add(nameField);

                    JLabel surnameLabel = new JLabel("  Surname:");
                    inputPanel.add(surnameLabel);

                    JTextField surnameField = new JTextField();
                    inputPanel.add(surnameField);

                    JLabel bornDLabel = new JLabel("  Born Date:");
                    inputPanel.add(bornDLabel);

                    JTextField bornDField = new JTextField();
                    inputPanel.add(bornDField);

                    JLabel ageLabel = new JLabel("  Age:");
                    inputPanel.add(ageLabel);

                    JTextField ageField = new JTextField();
                    inputPanel.add(ageField);

                    JLabel aboutLabel = new JLabel("  About:");
                    inputPanel.add(aboutLabel);

                    JTextField aboutField = new JTextField();
                    inputPanel.add(aboutField);

                    JLabel genderLabel = new JLabel("  Gender:");
                    inputPanel.add(genderLabel);

                    JTextField genderField = new JTextField();
                    inputPanel.add(genderField);

                    JButton motherButton = new JButton("Add as a mother");
                    inputPanel.add(motherButton);

                    JButton fatherButton = new JButton("Add as a father");
                    inputPanel.add(fatherButton);

                    JButton childButton = new JButton("Add as a child");
                    inputPanel.add(childButton);

                    JButton partnerButton = new JButton("Add as a partner");
                    inputPanel.add(partnerButton);

                    inputPanel.setLayout(new GridLayout(8, 2));

                    sl.setDividerLocation(frame.getHeight() / 5 * 3);
                    frame.add(sl);
                    frame.setVisible(true);
                }

                catch(Exception c){
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for age.");
                }

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.pressedBack(frame,newFrame);
            }
        });

        pushTree(newCreatedTree, rootDirectoryPath + familyName + ".tree");
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
