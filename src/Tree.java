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

    public void jTreeDisplayer(JFrame frame, int width, int height){

        JFrame newFrame = new JFrame();
        newFrame.pack();
        newFrame.setSize(1366, 720);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setTitle("Open Tree");

        frame.setVisible(false);
        frame.dispose();

        JPanel treePanel = new JPanel();
        JPanel infoPanel = new JPanel();

        JSplitPane sl = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, infoPanel,treePanel);

        sl.setDividerLocation(newFrame.getHeight() / 5 * 2);

        newFrame.add(sl);

        Main.menuBar(newFrame,infoPanel, 1100);

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(getHeadNode().getName() + " " + getHeadNode().getSurname());

        JTree jtree = new javax.swing.JTree(rootNode);

        jTreeOpener(rootNode, null);

        treePanel.add(jtree);

        newFrame.setVisible(true);
    }

    public void jTreeOpener(DefaultMutableTreeNode rootNode, DefaultMutableTreeNode childNode){
        Modification.displayedNodes.clear();

        if (getHeadNode().getRelation() == null || getHeadNode().getRelation().getChildren() == null){
            return; // last child needs to abort
        }

        for(Person child : getHeadNode().getRelation().getChildren()) {
            if (!Modification.displayedNodes.contains(child)){
                childNode = new DefaultMutableTreeNode(child.getId());
                rootNode.add(childNode);
                Modification.displayedNodes.add(child);
            }
        }
    }

    public JTree jTreeVisualiser(Person headNode, boolean inputIsPartner){
        DefaultMutableTreeNode rootNode;
        if(!inputIsPartner) {
            rootNode = new DefaultMutableTreeNode(headNode.getId());
        }
        else{
            rootNode = new DefaultMutableTreeNode(headNode.getId() + " (Spouse of " +
                    headNode.getRelation().getPartner().getName() + ")");
        }

        JTree jtree;
        jtree = new javax.swing.JTree(rootNode);

        jTreeCreator(rootNode, null);

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
