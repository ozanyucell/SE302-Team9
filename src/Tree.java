import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class Tree implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String familyName;
    private Person headNode = new Person();
    private ArrayList<Person> members = new ArrayList<Person>();
    private String about;
    private static HashSet<Person> displayedMembers = new HashSet<Person>();
    private static JTree spouseTree;

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
        newFrame.setLocationRelativeTo(null);

        frame.setVisible(false);
        frame.dispose();

        JPanel treePanel = new JPanel();
        JPanel infoPanel = new JPanel();
        JPanel menuPanel = new JPanel();

        JSplitPane sl = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, infoPanel,treePanel);

        sl.setDividerLocation(newFrame.getHeight() / 5 * 2);

        newFrame.add(sl);

        Main.menuBar(newFrame,menuPanel, 1100);

        infoPanel.add(menuPanel);
        infoPanel.setLayout(new GridLayout(9,1));

        JLabel famNameLabel = new JLabel("  Family Name: " + getFamilyName());
        infoPanel.add(famNameLabel);

        JLabel famAboutLabel = new JLabel("  About Family: " + getAbout());
        infoPanel.add(famAboutLabel);

        JLabel nameLabel = new JLabel(" ");
        infoPanel.add(nameLabel);

        JLabel surnameLabel = new JLabel(" ");
        infoPanel.add(surnameLabel);

        JLabel bornDLabel = new JLabel(" ");
        infoPanel.add(bornDLabel);

        JLabel genderLabel = new JLabel(" ");
        infoPanel.add(genderLabel);

        JLabel aboutLabel = new JLabel(" ");
        infoPanel.add(aboutLabel);

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(getHeadNode().getId());

        JTree jtree = new javax.swing.JTree(rootNode);

        treePanel.add(jtree);

        jTreeOpener(rootNode, getHeadNode(), treePanel);

        jtree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jtree.getLastSelectedPathComponent();
                String currentPersonID = selectedNode.getUserObject().toString();
                Person currentPersonOnVisualiser = null;
                for (int x = 0; x < getMembers().size(); x++) {
                    if (getMembers().get(x).getId().equals(currentPersonID)) {
                        currentPersonOnVisualiser = getMembers().get(x);
                        break;
                    }
                }
                assert currentPersonOnVisualiser != null;
                nameLabel.setText("  Name: " + currentPersonOnVisualiser.getName());
                surnameLabel.setText("  Surname: " + currentPersonOnVisualiser.getSurname());
                bornDLabel.setText("  Born Date: " + currentPersonOnVisualiser.getBornDate());
                genderLabel.setText("  Gender: " + currentPersonOnVisualiser.getGender());
                aboutLabel.setText("  About: " + currentPersonOnVisualiser.getAbout());
            }
        });

        spouseTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) spouseTree.getLastSelectedPathComponent();
                String currentPersonID = selectedNode.getUserObject().toString().split(" \\(")[0];
                Person currentPersonOnVisualiser = null;
                for (int x = 0; x < getMembers().size(); x++) {
                    if (getMembers().get(x).getId().equals(currentPersonID)) {
                        currentPersonOnVisualiser = getMembers().get(x);
                        break;
                    }
                }
                assert currentPersonOnVisualiser != null;
                nameLabel.setText("  Name: " + currentPersonOnVisualiser.getName());
                surnameLabel.setText("  Surname: " + currentPersonOnVisualiser.getSurname());
                bornDLabel.setText("  Born Date: " + currentPersonOnVisualiser.getBornDate());
                genderLabel.setText("  Gender: " + currentPersonOnVisualiser.getGender());
                aboutLabel.setText("  About: " + currentPersonOnVisualiser.getAbout());
            }
        });

        newFrame.setVisible(true);
    }

    public void jTreeOpener(DefaultMutableTreeNode rootNode, Person rootPerson, JPanel treePanel){
        DefaultMutableTreeNode childNode;

        if (getHeadNode().getRelation() == null || getHeadNode().getRelation().getChildren() == null){
            return; // last child needs to abort
        }

        else if(rootPerson.getRelation().getPartner() != null){
            DefaultMutableTreeNode spouseNode = new DefaultMutableTreeNode(getHeadNode().getRelation().getPartner().getId() + " (Spouse of " + getHeadNode().getName() + ")");
            spouseTree = new javax.swing.JTree(spouseNode);
            treePanel.add(spouseTree);
        }

        for(Person child : rootPerson.getRelation().getChildren()) {
            if (!displayedMembers.contains(child)){
                childNode = new DefaultMutableTreeNode(child.getId());
                rootNode.add(childNode);
                displayedMembers.add(child);
                jTreeOpener(childNode, child, treePanel);
            }
        }
    }

    public JTree jTreeVisualiser(Person headNode, boolean inputIsPartner){
        DefaultMutableTreeNode rootNode;
        if(!inputIsPartner) { rootNode = new DefaultMutableTreeNode(headNode.getId()); }
        else{
            rootNode = new DefaultMutableTreeNode(headNode.getId() + " (Spouse of " +
                    headNode.getRelation().getPartner().getName() + ")");
        }

        JTree jtree;
        jtree = new javax.swing.JTree(rootNode);

        jTreeCreator(rootNode, getHeadNode());

        return jtree;
    }

    public void jTreeCreator(DefaultMutableTreeNode root, Person rootPerson){
        DefaultMutableTreeNode childNode;

        if (getHeadNode().getRelation() == null || getHeadNode().getRelation().getChildren() == null){
            return; // last child needs to abort
        }

        for(Person child : rootPerson.getRelation().getChildren()) {
            if (!Modification.displayedNodes.contains(child)){
                childNode = new DefaultMutableTreeNode(child.getId());
                root.add(childNode);
                Modification.displayedNodes.add(child);
                jTreeCreator(childNode, child);
            }
        }
    }
}
