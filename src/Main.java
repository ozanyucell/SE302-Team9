import java.util.Scanner;

// SET METHOD FOR members ARRAY LIST IN TREE CLASS DOESN'T WORK!

public class Main {
    public static void main(String[] args) {
        Modification modification = new Modification();

        Person person = new Person();

        Tree tree = new Tree();

        modification.addTree();

        System.out.println(modification.trees);
        System.out.println(modification.tempFamilyMembers);

        //modification.trees.get(0).printTree();

        System.out.println();
        System.out.println(tree.getMembers());
        System.out.println(tree.getMembers() != null);
        System.out.println(tree.getMembers().size());

        modification.tempFamilyMembers.get(0).printPerson();
        modification.tempFamilyMembers.get(1).printPerson();

        tree.getMembers().get(0).printPerson();

    }
}
