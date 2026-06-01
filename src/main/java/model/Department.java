package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "department")
public class Department {
    @EmbeddedId
    @Column (name = "id")
    String id;
    @Column (name = "name")
    String name;
    @Column (name = "manager_name")
    String managerName;
    //@OneToMany(mappedBy = "employee")
    //List<Employee> employees = new ArrayList<>();
    public Department() {}
    public Department(String id, String name, String managerName) {
        this.id = id;
        this.name = name;
        this.managerName = managerName;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getManagerName() { return managerName; }
    public void setManagerName(String manager_name) { this.managerName = manager_name; }
}
