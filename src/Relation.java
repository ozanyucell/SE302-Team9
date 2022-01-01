import java.util.ArrayList;

public class Relation {
    private Person mother, father, partner;
    private ArrayList<Person> children;

    Relation(Person mother, Person father, ArrayList<Person> children){
        setMother(mother);
        setFather(father);
        setChildren(children);
    }

    public void setMother(Person mother) { this.mother = mother; }

    public void setFather(Person father) { this.father = father; }

    public void setPartner(Person partner) { this.partner = partner; }

    public void setChildren(ArrayList<Person> children) { this.children = children; }

    public Person getMother() { return mother; }

    public Person getFather() { return father; }

    public Person getPartner() { return partner; }

    public ArrayList<Person> getChildren() { return children; }

}
