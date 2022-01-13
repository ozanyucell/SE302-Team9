import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Tree implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String familyName;
    private Person headNode = new Person();
    private ArrayList<Person> members = new ArrayList<Person>();
    private String about;

    Tree() {
        setFamilyName("Unknown");
        setAbout("No information.");
    }

    Tree(String familyName, String about, Person headNode) {
        setFamilyName(familyName);
        setAbout(about);
        setHeadNode(headNode);
        setMembers(headNode);
    }

    public void setFamilyName(String familyName) { this.familyName = familyName; }

    public void setHeadNode(Person headNode) { this.headNode = headNode; }

    public void setMembers(Person member) { this.members.add(member); }

    public void setAbout(String about) { this.about = about; }

    public String getFamilyName() { return familyName; }

    public Person getHeadNode() { return headNode; }

    public ArrayList<Person> getMembers() { return this.members; }

    public String getAbout() { return about; }

    //for testing
    public void printTree() {
        System.out.println("-----------------------------");
        System.out.println("Aile Adı: " + getFamilyName());
        // if(members==null) { System.out.println("This tree is empty!"); }
        // else { System.out.println(getMembers()); }
        System.out.println("\nHakkında\n" + getAbout());
        System.out.println("-----------------------------");
    }

    public void displayTree(JFrame frame, int width, int height){
        JFrame newFrame = new JFrame();
        newFrame.pack();
        newFrame.setSize(width, height);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(true);
        newFrame.setLayout(null);
        newFrame.setTitle("");

        frame.setVisible(false);
        frame.dispose();

        Main.menuBar(newFrame, width);

        JLabel familyName = new JLabel("Aile Adı: " + getFamilyName());
        familyName.setBounds(10, 40, width, 15);
        newFrame.add(familyName);

        JLabel about = new JLabel("Aile Hakkında: " + getAbout());
        about.setBounds(10, 80, width, 15);
        newFrame.add(about);

        /*
        JLabel members = new JLabel("Family Members: " + getMembers());
        members.setBounds(10, 120, width, 15);
        newFrame.add(members);
        */
    }

    public void jTreeDisplayer(JFrame frame, int width, int height){
        JFrame newFrame = new JFrame();
        newFrame.pack();
        newFrame.setSize(width, height);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setVisible(true);
        newFrame.setLayout(new FlowLayout());
        newFrame.setTitle("Ağaç Görüntüleyici");

        frame.setVisible(false);
        frame.dispose();

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(getHeadNode());

        JTree jtree;
        jtree = new javax.swing.JTree(rootNode);

        jTreeCreator(rootNode, null);

        newFrame.add(jtree);

        newFrame.setVisible(true);
    }

    public JTree jTreeVisualiser(){
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(getHeadNode().getId());
        JTree jtree;
        jtree = new javax.swing.JTree(rootNode);

        jTreeCreator(rootNode, null);

        jtree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                Modification.selectedNode = (DefaultMutableTreeNode) jtree.getLastSelectedPathComponent();
                String currentPersonID = Modification.selectedNode.getUserObject().toString();
                for(int x = 0; x < Modification.newCreatedTree.getMembers().size(); x++){
                    if(Modification.newCreatedTree.getMembers().get(x).getId().equals(currentPersonID)){
                        Modification.currentPersonOnVisualiser = Modification.newCreatedTree.getMembers().get(x);
                    }
                }
            }
        });

        return jtree;
    }

    public void jTreeCreator(DefaultMutableTreeNode root, DefaultMutableTreeNode childNode){

        if (getHeadNode().getRelation() == null || getHeadNode().getRelation().getChildren() == null){
            return; // last child needs to abort
        }

        for(Person child : getHeadNode().getRelation().getChildren()) {
            if (!Modification.displayedNodes.contains(child)){
                childNode = new DefaultMutableTreeNode(child.getId());
                root.add(childNode);
                Modification.displayedNodes.add(child);
            }
        }
    }
}
