package model;

public class Position {
    String id;
    String name;
    double allowance;
    public Position() {}
    public Position(double allowance, String name, String id) {
        this.allowance = allowance;
        this.name = name;
        this.id = id;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getAllowance() { return allowance; }
    public void setAllowance(double allowance) { this.allowance = allowance; }
}
