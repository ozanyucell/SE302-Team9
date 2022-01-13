import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Relation implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Person mother, father, partner;
    private ArrayList<Person> children = new ArrayList<Person>();

    Relation(){
        setMother(null);
        setFather(null);
        setPartner(null);
    }

    public void setMother(Person mother) { this.mother = mother; }

    public void setFather(Person father) { this.father = father; }

    public void setPartner(Person partner) { this.partner = partner; }

    public void setChildren(Person children) { this.children.add(children); }

    public void setChildrenArray(ArrayList<Person> childrenArray) {
        this.children.addAll(childrenArray);
    }

    public Person getMother() { return mother; }

    public Person getFather() { return father; }

    public Person getPartner() { return partner; }

    public ArrayList<Person> getChildren() { return children; }
}
