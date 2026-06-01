package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "employee")
public class Employee {
    @EmbeddedId
    @Column(name = "id")
    String id;
    @Column (name = "name")
    String name;
    @Column (name = "age")
    int age;
    @Column(name = "address")
    String address;
    //@ManyToOne(fetch = FetchType.LAZY)
    //@MapsId("id")
    //@JoinColumn(name = "dep_id")
    //Department d = new Department();
    public Employee() {}
    public Employee(String id, String address, int age, String name) {
        this.id = id;
        this.address = address;
        this.age = age;
        this.name = name;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
