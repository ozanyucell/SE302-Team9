import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static Tree tree;

    public static void main(String[] args) throws IOException {
        Modification.startup();
        mainGUI();
    }

    public static void mainGUI(){
        int width = 875, height = 540;

        JFrame frame = new JFrame();
        frame.pack();
        frame.setSize(width,height);
        frame.setTitle("Family Tree Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        menuBar(frame, width);

        JButton openButton = new JButton("Open a Tree");
        openButton.setBounds(170,150,200,200);
        frame.add(openButton);

        JButton createButton = new JButton("Create a Tree");
        createButton.setBounds(500,150,200,200);
        frame.add(createButton);

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTreeConnector(frame, openButton, null, width, height);
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Modification.createTree(frame, width, height);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.setVisible(true);
    }

    public static void menuBar(JFrame frame, int width){
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, width, 30);
        frame.add(menuBar);

        JMenu menuButton1 = new JMenu("File");
        menuBar.add(menuButton1);

        JMenu menuButton2 = new JMenu("Help");
        menuBar.add(menuButton2);

        JMenuItem openBarButton = new JMenuItem("Open");
        menuButton1.add(openBarButton);

        JMenuItem createBarButton = new JMenuItem("Create");
        menuButton1.add(createBarButton);

        JMenuItem rootFolder = new JMenuItem("Change Root Directory");
        menuButton1.add(rootFolder);

        JMenuItem gitHubLink = new JMenuItem("GitHub");
        menuButton2.add(gitHubLink);

        openBarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTreeConnector(frame, null, openBarButton, width, 540);
            }
        });

        rootFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser folderChooser = new JFileChooser();
                folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                folderChooser.setDialogTitle("Please Select a Directory");
                folderChooser.setCurrentDirectory(new File(Modification.rootDirectoryPath));

                if (folderChooser.showOpenDialog(rootFolder) == JFileChooser.APPROVE_OPTION){
                    Modification.rootDirectoryPath = folderChooser.getSelectedFile().getAbsolutePath();
                    try {
                        Modification.pathWriter();
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        gitHubLink.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URL("https://github.com/ozanyucell/SE302-Team9").toURI());
                }
                catch (Exception a) {
                    a.printStackTrace();
                }
            }
        });

    }

    public static void openTreeConnector(JFrame frame, JButton button, JMenuItem menuItem, int width, int height){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Please Select a .tree File");
        fileChooser.setCurrentDirectory(new File(Modification.rootDirectoryPath));

        if(button == null){
            if (fileChooser.showOpenDialog(menuItem) == JFileChooser.APPROVE_OPTION){
                if(fileChooser.getSelectedFile().getAbsolutePath().endsWith(".tree")) {
                    try { tree = Modification.pullTree(fileChooser.getSelectedFile().getAbsolutePath()); }
                    catch (IOException | ClassNotFoundException ex) { ex.printStackTrace(); }

                    //tree.displayTree(frame, width, height); // GUI template here
                    tree.jTreeDisplayer(frame, width, height);
                    tree.printTree(); // NEEDS TO BE REPLACED WITH GUI
                }

                else {
                    JOptionPane.showMessageDialog(frame, "Please select a file with .tree extension.");
                }
            }
        }

        if(menuItem == null){
            if (fileChooser.showOpenDialog(button) == JFileChooser.APPROVE_OPTION){
                if(fileChooser.getSelectedFile().getAbsolutePath().endsWith(".tree")) {
                    try { tree = Modification.pullTree(fileChooser.getSelectedFile().getAbsolutePath()); }
                    catch (IOException | ClassNotFoundException ex) { ex.printStackTrace(); }

                    //tree.displayTree(frame, width, height); // GUI template here
                    tree.jTreeDisplayer(frame, width, height);

                }

                else {
                    JOptionPane.showMessageDialog(frame, "Please select a file with .tree extension.");
                }
            }
        }
    }

    public static void pressedBack(JFrame frame1, JFrame frame2) {
        frame2.setVisible(false);
        frame1.setVisible(true);
        JButton backButton = new JButton("Back");
        backButton.setBounds(80, 150, 100, 25);
    }
}
