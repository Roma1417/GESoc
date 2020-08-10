package utn.dds.tpAnual.db.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TEST")
public class TestEntity {

    private static final long serialVersionUID = -1798070786993154676L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long employeeId;

    @Column(name = "NOMBRE", unique = true, nullable = false, length = 100)
    private String name;

    @Column(name = "APELLIDO", unique = true, nullable = false, length = 100)
    private String surename;

    @OneToMany(mappedBy="one", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TestEntityMany> many;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public TestEntity(){

    }

    public TestEntity(String name, String surename) {
        this.name = name;
        this.surename = surename;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public List<TestEntityMany> getMany() {
        return many;
    }

    public void setMany(List<TestEntityMany> many) {
        this.many = many;
    }
}