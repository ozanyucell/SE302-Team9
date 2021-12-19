public class Person{
    private String name;
    private String surname;
    private int age;
    private String gender;
    private String bornDate;
    private String about;

    public Person() {
        name = "Unknown";
        surname = "Unknown";
        age = 0;
        gender = "Unknown";
        bornDate = "Unknown";
        about = "No information.";
    }

    public void setName(String name) { this.name = name; }

    public void setSurname(String surname) { this.surname = surname; }

    public void setAge(int age) { this.age = age; }

    public void setGender(String gender) { this.gender = gender; }

    public void setBornDate(String bornDate) { this.bornDate = bornDate; }

    public void setAbout(String about) { this.about = about; }

    public String getName() { return name; }

    public String getSurname() { return surname; }

    public int getAge() { return age; }

    public String getGender() { return gender; }

    public String getBornDate() { return bornDate; }

    public String getAbout() { return about; }
}
