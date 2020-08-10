package utn.dds.tpAnual.db.entity;


import javax.persistence.*;

@Entity
@Table(name = "TEST_SON")
public class TestEntityMany {

    private static final long serialVersionUID = -1798070786993154676L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long employeeId;

    @Column(name = "NOMBRE", unique = false, nullable = false, length = 100)
    private String name;

    @Column(name = "APELLIDO", unique = false, nullable = false, length = 100)
    private String surename;

    @ManyToOne(fetch = FetchType.LAZY)
    private TestEntity one;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public TestEntityMany(){

    }

    public TestEntityMany(String name, String surename, TestEntity one) {
        this.name = name;
        this.surename = surename;
        this.one = one;
    }

    public TestEntityMany(String name, String surename) {
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

    public TestEntity getOne() {
        return one;
    }

    public void setOne(TestEntity one) {
        this.one = one;
    }
}