import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

import static java.lang.Integer.parseInt;

public class Modification {
    static String rootDirectoryPath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\Family Tree Generator";
    public static Tree newCreatedTree;
    public static String familyName;
    public static int publicMemberID = 1;
    public static Person currentPersonOnVisualiser;
    public static DefaultMutableTreeNode selectedNode;
    public static DefaultMutableTreeNode selectedPartnerNode;
    public static HashSet<Person> displayedNodes = new HashSet<Person>();
    public static ArrayList<JTree> spouseTrees = new ArrayList<JTree>();

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
        newFrame.setSize(340, 540);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(true);
        newFrame.setLayout(null);
        newFrame.setTitle("Create Tree");
        newFrame.setLocationRelativeTo(null);
        frame.setVisible(false);
        frame.dispose();
        JTextField textField;
        JTextField textField2;

        JPanel panel = new JPanel();
        panel.setBounds(10, 20, 300, 460);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel ctLabel = new JLabel("                                 Create Tree");
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

        JLabel aboutLabel = new JLabel("About:");
        panel.add(aboutLabel);
        JTextField aboutField = new JTextField();
        panel.add(aboutField);

        JLabel genderLabel = new JLabel("Gender (Please enter 'Male' or 'Female'):");
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
                if (genderField.getText().equals("Female") || genderField.getText().equals("Male")) {
                    familyName = textField.getText();
                    String about = textField2.getText();
                    String headName, headSurname, headGender, headBornDate, headAbout;
                    headName = nameField.getText();
                    headSurname = surnameField.getText();
                    headGender = genderField.getText();
                    headBornDate = bornDField.getText();
                    headAbout = aboutField.getText();

                    try {
                        Person headNode = new Person(headName, headSurname, headGender, headBornDate, headAbout);
                        newCreatedTree = new Tree(familyName, about, headNode);

                        JFrame frame = new JFrame();
                        frame.pack();
                        frame.setSize(1366, 720);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setTitle(familyName);
                        frame.setLocationRelativeTo(null);

                        newFrame.setVisible(false);

                        JPanel inputPanel = new JPanel();
                        JPanel treePanel = new JPanel();

                        treePanel.setLayout(new FlowLayout());

                        JTree tree = newCreatedTree.jTreeVisualiser(newCreatedTree.getHeadNode(), false);
                        tree.addTreeSelectionListener(new TreeSelectionListener() {
                            @Override
                            public void valueChanged(TreeSelectionEvent e) {
                                selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                                String currentPersonID = selectedNode.getUserObject().toString();
                                for (int x = 0; x < newCreatedTree.getMembers().size(); x++) {
                                    if (newCreatedTree.getMembers().get(x).getId().equals(currentPersonID)) {
                                        currentPersonOnVisualiser = newCreatedTree.getMembers().get(x);
                                        break;
                                    }
                                }
                            }
                        });

                        treePanel.add(tree);

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

                        JLabel aboutLabel = new JLabel("  About:");
                        inputPanel.add(aboutLabel);

                        JTextField aboutField = new JTextField();
                        inputPanel.add(aboutField);

                        JLabel genderLabel = new JLabel("  Gender (Please enter 'Male' or 'Female'):");
                        inputPanel.add(genderLabel);

                        JTextField genderField = new JTextField();
                        inputPanel.add(genderField);

                        JButton childButton = new JButton("Add as a child");
                        inputPanel.add(childButton);

                        JButton partnerButton = new JButton("Add as a partner");
                        inputPanel.add(partnerButton);

                        JButton editButton = new JButton("Edit selected member");
                        inputPanel.add(editButton);

                        JButton confirmButton = new JButton("Confirm the tree");
                        inputPanel.add(confirmButton);

                        inputPanel.setLayout(new GridLayout(8, 2));

                        editButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String name = nameField.getText();
                                String surname = surnameField.getText();
                                String bornDate = bornDField.getText();
                                String aboutPerson = aboutField.getText();
                                String gender = genderField.getText();

                                if (!name.equals("")) {
                                    currentPersonOnVisualiser.setName(name);
                                }
                                if (!surname.equals("")) {
                                    currentPersonOnVisualiser.setSurname(surname);
                                }

                                if (!gender.equals("")) {
                                    currentPersonOnVisualiser.setGender(gender);
                                }
                                if (!bornDate.equals("")) {
                                    currentPersonOnVisualiser.setBornDate(bornDate);
                                }
                                if (!aboutPerson.equals("")) {
                                    currentPersonOnVisualiser.setAbout(aboutPerson);
                                }

                                currentPersonOnVisualiser.setId(name + " " + surname + " - " + currentPersonOnVisualiser.getId().split(" - ")[1]);

                                selectedNode.setUserObject(currentPersonOnVisualiser.getId());

                                tree.updateUI();
                            }
                        });

                        confirmButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFileChooser newFileChooser = new JFileChooser();
                                newFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                                newFileChooser.setDialogTitle("Please select a directory to create.");
                                newFileChooser.setCurrentDirectory(new File(Modification.rootDirectoryPath));

                                if (newFileChooser.showOpenDialog(confirmButton) == JFileChooser.APPROVE_OPTION) {
                                    try {
                                        pushTree(newCreatedTree, newFileChooser.getSelectedFile().getAbsolutePath() + "\\" + familyName + ".tree");
                                        frame.dispose();
                                        JOptionPane.showMessageDialog(null, "Tree has successfully been saved.");
                                        Main.mainGUI();
                                    }
                                    catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        });

                        childButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    String name = nameField.getText();
                                    String surname = surnameField.getText();
                                    String bornDate = bornDField.getText();
                                    String aboutPerson = aboutField.getText();
                                    String gender = genderField.getText();

                                    if (currentPersonOnVisualiser != null) {
                                        Person newMember = new Person(name, surname, gender, bornDate, aboutPerson);
                                        currentPersonOnVisualiser.setChildren(newMember);
                                        newCreatedTree.setMembers(newMember);
                                        if (currentPersonOnVisualiser.getGender().equals("Female")) {
                                            newMember.setMother(currentPersonOnVisualiser);
                                        }
                                        else if (currentPersonOnVisualiser.getGender().equals("Male")) {
                                            newMember.setFather(currentPersonOnVisualiser);
                                        }

                                        newCreatedTree.jTreeCreator(selectedNode);

                                        tree.updateUI();
                                    }

                                    else {
                                        JOptionPane.showMessageDialog(null, "Please select a member to add.");
                                    }
                                }
                                catch(Exception bruh){
                                    JOptionPane.showMessageDialog(null, "Please enter valid data.");
                                }
                            }
                        });

                        partnerButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    if(currentPersonOnVisualiser != null) {

                                        String name = nameField.getText();
                                        String surname = surnameField.getText();
                                        String bornDate = bornDField.getText();


                                        String aboutPerson = nameField.getText();
                                        String gender = nameField.getText();
                                        Person newMember = new Person(name, surname, gender, bornDate, aboutPerson);
                                        currentPersonOnVisualiser.setPartner(newMember);
                                        newCreatedTree.setMembers(newMember);
                                        newMember.setPartner(currentPersonOnVisualiser);
                                        newMember.getRelation().setChildrenArray(currentPersonOnVisualiser.getRelation().getChildren());
                                        JTree spouseJTree = newCreatedTree.jTreeVisualiser(newMember,true);
                                        treePanel.add(spouseJTree);
                                        tree.updateUI();
                                    }

                                    else {
                                        JOptionPane.showMessageDialog(null, "Please select a member to add.");
                                    }
                                }

                                catch(Exception bruh){
                                    JOptionPane.showMessageDialog(null, "Please enter valid data.");

                                }
                            }
                        });

                        sl.setDividerLocation(frame.getHeight() / 5 * 3);
                        frame.add(sl);
                        frame.setVisible(true);
                    }
                    catch (Exception nonIntInput) {
                        JOptionPane.showMessageDialog(null, "Please enter valid data.");
                    }
                }

                else{
                    JOptionPane.showMessageDialog(null, "Please enter valid data.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.pressedBack(frame, newFrame);
            }
        });

        pushTree(newCreatedTree, rootDirectoryPath + familyName + ".tree");
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
}
