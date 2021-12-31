import java.util.Scanner;

// SET METHOD FOR members ARRAY LIST IN TREE CLASS DOESN'T WORK!

public class Main {
    public static void main(String[] args) {
        Modification modification = new Modification();

        Person person = new Person();

        Tree tree = new Tree();

        modification.addTree();
        modification.trees.get(0).printTree();
        tree.getMembers().get(0).printPerson();

    }
}
