import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // test addTree
        Tree newTree = Modification.addTree();

        System.out.println();
        System.out.println(newTree.getMembers());

        gui();
    }

    public static void gui(){
        int width = 875, height = 540;

        JFrame frame = new JFrame("Family Tree Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 40, width, height);
        frame.add(panel);
        panel.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, width, 30);
        frame.add(menuBar);

        JMenu menuButton1 = new JMenu("File");
        menuBar.add(menuButton1);

        JMenu menuButton2 = new JMenu("Help");
        menuBar.add(menuButton2);

        ArrayList<String> familyNames = new ArrayList<>();

        for (int i = 0; i < Modification.getTrees().size(); i++){
            familyNames.add(Modification.getTrees().get(i).getFamilyName());
        }

        int positionX = 10, positionY = 30;

        for (String familyName : familyNames) {
            JButton newButton = new JButton(familyName);
            if(frame.getHeight() - positionX+80 < 50) {
                positionX = 10;
                newButton.setBounds(positionX + 90, positionY+90, 80, 80);
                positionY += 90;
            }
            else {
                newButton.setBounds(positionX + 90, positionY, 80, 80);
            }
            positionX += 90;
            panel.add(newButton);
        }

        JButton newButton = new JButton("+");
        newButton.setBounds(positionX + 90, positionY, 80, 80);
        panel.add(newButton);

        frame.setVisible(true);
    }
}
