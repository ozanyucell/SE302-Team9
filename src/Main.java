import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        gui();
    }

    public static void gui(){
        int width = 875, height = 540;

        JFrame frame = new JFrame();
        frame.pack();
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, width, 30);
        frame.add(menuBar);

        JMenu menuButton1 = new JMenu("File");
        menuBar.add(menuButton1);

        JMenu menuButton2 = new JMenu("Help");
        menuBar.add(menuButton2);

        JButton openButton = new JButton("Open Tree");
        openButton.setBounds(170,150,200,200);
        frame.add(openButton);

        JButton createButton = new JButton("Create Tree");
        createButton.setBounds(500,150,200,200);
        frame.add(createButton);

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose a .tree file");

                if (fileChooser.showOpenDialog(openButton) == JFileChooser.APPROVE_OPTION){
                    if(fileChooser.getSelectedFile().getAbsolutePath().endsWith(".tree")) {
                        Tree tree = new Tree();
                        Modification.filePath = fileChooser.getSelectedFile().getAbsolutePath();
                        try { tree = Modification.pullTree(); }
                        catch (IOException | ClassNotFoundException ex) { ex.printStackTrace(); }

                        tree.printTree(); // NEEDS TO BE REPLACED WITH GUI
                    }

                    else {
                        JOptionPane.showMessageDialog(frame, "Please select a .tree file.");
                    }
                }
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame newFrame = new JFrame();
                newFrame.pack();
                newFrame.setSize(width,height);

                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
