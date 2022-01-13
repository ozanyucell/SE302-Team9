import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashSet;

import static java.lang.Integer.parseInt;

public class Modification {
    static String rootDirectoryPath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\Aile Ağacı Oluşturucu";
    public static Tree newCreatedTree;
    public static String familyName;
    public static int publicMemberID = 1;
    public static Person currentPersonOnVisualiser;
    public static DefaultMutableTreeNode selectedNode;
    public static HashSet<Person> displayedNodes = new HashSet<Person>();

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
        newFrame.setTitle("Ağaç Oluştur");
        frame.setVisible(false);
        frame.dispose();
        JTextField textField;
        JTextField textField2;

        JPanel panel = new JPanel();
        panel.setBounds(10, 20, 300, 410);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel panel2 = new JPanel();
        panel2.setBounds(10, 435, 300, 30);
        panel2.setLayout(new GridLayout(1,2));
        panel2.setVisible(true);

        JLabel ctLabel = new JLabel("                                 Ağaç Oluşturma");
        ctLabel.setBounds(80, 40, 100, 15);
        panel.add(ctLabel);

        JLabel ctLabel1 = new JLabel("Aile Adı:");
        panel.add(ctLabel1);

        textField = new JTextField();
        panel.add(textField);

        JLabel ctLabel2 = new JLabel("Aileniz Hakkında:");
        panel.add(ctLabel2);

        textField2 = new JTextField();
        panel.add(textField2);

        JLabel mainLabel = new JLabel("                                Root Member"); // dont know about this one
        panel.add(mainLabel);

        JLabel NameLabel = new JLabel("İsim:");
        panel.add(NameLabel);

        JTextField nameField = new JTextField();
        panel.add(nameField);

        JLabel surnameLabel = new JLabel("Soyisim:");
        panel.add(surnameLabel);
        JTextField surnameField = new JTextField();
        panel.add(surnameField);

        JLabel bornDLabel = new JLabel("Doğum Tarihi:");
        panel.add(bornDLabel);
        JTextField bornDField = new JTextField();
        panel.add(bornDField);

        JLabel ageLabel = new JLabel("Yaş:");
        panel.add(ageLabel);
        JTextField ageField = new JTextField();
        panel.add(ageField);

        JLabel aboutLabel = new JLabel("Hakkında:");
        panel.add(aboutLabel);
        JTextField aboutField = new JTextField();
        panel.add(aboutField);

        JLabel genderLabel = new JLabel("Cinsiyet:");
        panel.add(genderLabel);
        JTextField genderField = new JTextField();
        panel.add(genderField);

        newFrame.add(panel);
        newFrame.add(panel2);

        JButton contButton = new JButton("Devam et");
        JButton backButton = new JButton("Geri");

        panel2.add(backButton);
        panel2.add(contButton);

        contButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                familyName = textField.getText();
                String about = textField2.getText();
                String headName, headSurname, headGender, headBornDate, headAbout;
                int headAge;
                headName = nameField.getText();
                headSurname = surnameField.getText();
                headGender = genderField.getText();
                headBornDate = bornDField.getText();
                headAbout = aboutField.getText();

                try {
                    headAge = parseInt(ageField.getText());
                    Person headNode = new Person(headName, headSurname, headAge, headGender, headBornDate, headAbout);
                    newCreatedTree = new Tree(familyName, about, headNode);

                    JFrame frame = new JFrame();
                    frame.pack();
                    frame.setSize(1366, 720);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setTitle("Aile Ağacı Oluşturucu");
                    newFrame.setVisible(false);

                    JPanel inputPanel = new JPanel();
                    JPanel treePanel = new JPanel();

                    treePanel.setLayout(new FlowLayout());

                    JTree tree = newCreatedTree.jTreeVisualiser();
                    treePanel.add(tree);

                    frame.add(treePanel);

                    JSplitPane sl = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputPanel, treePanel);

                    JLabel nameLabel = new JLabel("  İsim:");
                    inputPanel.add(nameLabel);

                    JTextField nameField = new JTextField();
                    inputPanel.add(nameField);

                    JLabel surnameLabel = new JLabel("  Soyisim:");
                    inputPanel.add(surnameLabel);

                    JTextField surnameField = new JTextField();
                    inputPanel.add(surnameField);

                    JLabel bornDLabel = new JLabel("  Doğum Tarihi:");
                    inputPanel.add(bornDLabel);

                    JTextField bornDField = new JTextField();
                    inputPanel.add(bornDField);

                    JLabel ageLabel = new JLabel("  Yaş:");
                    inputPanel.add(ageLabel);

                    JTextField ageField = new JTextField();
                    inputPanel.add(ageField);

                    JLabel aboutLabel = new JLabel("  Hakkında:");
                    inputPanel.add(aboutLabel);

                    JTextField aboutField = new JTextField();
                    inputPanel.add(aboutField);

                    JLabel genderLabel = new JLabel("  Cinsiyet:");
                    inputPanel.add(genderLabel);

                    JTextField genderField = new JTextField();
                    inputPanel.add(genderField);

                    JButton motherButton = new JButton("Anne olarak ekle");
                    inputPanel.add(motherButton);

                    JButton fatherButton = new JButton("Baba olarak ekle");
                    inputPanel.add(fatherButton);

                    JButton childButton = new JButton("Evlat olarak ekle");
                    inputPanel.add(childButton);

                    JButton partnerButton = new JButton("Eş olarak ekle");
                    inputPanel.add(partnerButton);

                    inputPanel.setLayout(new GridLayout(8, 2));

                    motherButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String name = nameField.getText();
                            String surname = surnameField.getText();
                            String bornDate = bornDField.getText();
                            int age = parseInt(ageField.getText());
                            String aboutPerson = aboutField.getText();
                            String gender = genderField.getText();
                            Person newMember = new Person(name, surname, age, gender, bornDate, aboutPerson);
                            currentPersonOnVisualiser.setMother(newMember);
                            newCreatedTree.jTreeCreator(selectedNode, null);
                            tree.updateUI();
                        }
                    });

                    fatherButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String name = nameField.getText();
                            String surname = surnameField.getText();
                            String bornDate = bornDField.getText();
                            int age = parseInt(ageField.getText());
                            String aboutPerson = aboutField.getText();
                            String gender = genderField.getText();
                            Person newMember = new Person(name, surname, age, gender, bornDate, aboutPerson);
                            currentPersonOnVisualiser.setFather(newMember);
                            newCreatedTree.jTreeCreator(selectedNode, null);
                            tree.updateUI();
                        }
                    });

                    childButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String name = nameField.getText();
                            String surname = surnameField.getText();
                            String bornDate = bornDField.getText();
                            int age = parseInt(ageField.getText());
                            String aboutPerson = aboutField.getText();
                            String gender = genderField.getText();
                            Person newMember = new Person(name, surname, age, gender, bornDate, aboutPerson);
                            try {
                                currentPersonOnVisualiser.setChildren(newMember);
                                newCreatedTree.jTreeCreator(selectedNode, null);
                                tree.updateUI();
                            }
                            catch(Exception notSelected) {
                                JOptionPane.showMessageDialog(null, "Lütfen eklemek için bir aile bireyi seçiniz.");
                            }
                        }
                    });

                    partnerButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String name = nameField.getText();
                            String surname = nameField.getText();
                            String bornDate = nameField.getText();
                            int age = parseInt(nameField.getText());
                            String aboutPerson = nameField.getText();
                            String gender = nameField.getText();
                            Person newMember = new Person(name, surname, age, gender, bornDate, aboutPerson);
                            currentPersonOnVisualiser.setPartner(newMember);
                            newCreatedTree.jTreeCreator(selectedNode, null);
                            tree.updateUI();
                        }
                    });

                    sl.setDividerLocation(frame.getHeight() / 5 * 3);
                    frame.add(sl);
                    frame.setVisible(true);
                }

                catch (Exception nonIntInput){
                    JOptionPane.showMessageDialog(null, "Lütfen yaşınız için uygun bir sayı giriniz.");
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

    public static void editTree(){}

    public static void deleteTree(){}

    public static void mergeTrees(){}

}
