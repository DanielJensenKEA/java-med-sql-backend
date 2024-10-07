import java.time.LocalDate;

public class Employee {
    private int empNo;
    private String eName;
    private String job;
    private Integer MGR;
    private LocalDate hireDate;
    private Integer sal;
    private Integer comm;
    private Integer deptNo;
    /*
    VI BLIVER NØDT TIL AT HÅNDTERE NULL VÆRDIER VED AT BRUGE WRAPPER KLASSEN FOR INT. DEN PRIMITIVE VÆRDI KAN IKKE
    INDEHOLDE NULL VÆRDIER.
    */

    public Employee(int empNo, String eName, String job, Integer MGR, LocalDate hireDate, Integer sal, Integer comm, Integer deptNo) {
        this.empNo = empNo;
        this.eName = eName;
        this.job = job;
        this.MGR = MGR;
        this.hireDate = hireDate;
        this.sal = sal;
        this.comm = comm;
        this.deptNo = deptNo;
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMGR() {
        return MGR;
    }

    public void setMGR(Integer MGR) {
        this.MGR = MGR;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Integer getSal() {
        return sal;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }

    public Integer getComm() {
        return comm;
    }

    public void setComm(Integer comm) {
        this.comm = comm;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }
}
