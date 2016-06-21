package app.bajiru.ir.Object.Model;

public class Customer {
    private String id;
    private String created_by;
    private String created_on;
    private String name;
    private String active;
    private String number;
    private String type;
    private People[] people;
    private String note;
    private Phones[] phones;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public People[] getPeople() {
        return people;
    }

    public void setPeople(People[] people) {
        this.people = people;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Phones[] getPhones() {
        return phones;
    }

    public void setPhones(Phones[] phones) {
        this.phones = phones;
    }
}
