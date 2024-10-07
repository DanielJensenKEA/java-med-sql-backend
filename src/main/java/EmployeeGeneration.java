import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class EmployeeGeneration {
    Connection con;
    List<Employee> mainList;
    List<Departments> depts;

    public EmployeeGeneration() throws SQLException {
        con = runDatabaseConnection();
        mainList = populateList();
        depts = populateDepts();
    }

    private List<Departments> populateDepts() throws SQLException {
        String query ="SELECT * FROM dept";
        List<Departments> departments = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while(rs.next()) {
            int deptNo = rs.getInt("DEPTNO");

            String dName = rs.getString("DNAME");

            String location = rs.getString("LOC");
            Departments dept = new Departments(deptNo, dName, location);
            depts.add(dept);
        }
        return departments;
    }

    public List<Employee> getEmployeeList() {
        return mainList;
    }

    //Employee arguments
    //EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO
    public Connection runDatabaseConnection() {
        String database = "jdbc:mysql://localhost:3306/emp_dept";
        String user = "daniel";
        String password = "leinad";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(database, user, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public List<Employee> populateList() throws SQLException {
        // EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO
        String query = "SELECT * FROM emp";
        List<Employee> list = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("***All Names And Employee Numbers ***");

        while (rs.next()) {
            int empNo = rs.getInt("EMPNO");
            String name = rs.getString("ENAME");

            String job = rs.getString("JOB");
            if (rs.wasNull()) {
                job = null;  // Allow null value for job
            }

            // Use Integer for MGR, since it can be null
            Integer mgr = rs.getObject("MGR") != null ? rs.getInt("MGR") : null;

            // Handle hireDate, parsing only if it's not null
            Date hireDateSQL = rs.getDate("HIREDATE");
            LocalDate hireDate = hireDateSQL != null ? hireDateSQL.toLocalDate() : null;

            // Use Integer for sal, comm, and deptNo since they can be null
            Integer sal = rs.getObject("SAL") != null ? rs.getInt("SAL") : null;
            Integer comm = rs.getObject("COMM") != null ? rs.getInt("COMM") : null;
            Integer deptNo = rs.getObject("DEPTNO") != null ? rs.getInt("DEPTNO") : null;

            Employee em = new Employee(empNo, name, job, mgr, hireDate, sal, comm, deptNo);
            list.add(em);
        }

        return list;
    }

    public void aPrintAllEmpAndNo() {
        System.out.println("All employees and numbers");
        for (Employee e : mainList) {
            System.out.println("Name: "+e.geteName()+" EmpNo:"+e.getEmpNo());
        }
    }

    public void bPrintAllEmpNoAndNamesStartWithS()  {
        System.out.println("Names that start with S:");
        for (Employee e : mainList) {
            if (e.geteName().startsWith("S")) {
                System.out.println(e.geteName());
            }
        }
        System.out.println(" ");
    }

    public void cPrintTotalSalaryOfEmps()  {
        int totalSalary = 0;

        System.out.println("Total Salary cost:");
        for (Employee e : mainList) {
            totalSalary += e.getSal();
        }
        System.out.println("Total Salary: "+totalSalary);
    }

    public void dReturnHighestSalariedPerson()  {
        Employee em = mainList.getFirst();
        System.out.println("Highest Salaried Person:");
        for (Employee e : mainList) {
            if (em.getSal() < e.getSal()) {
                em = e;
            }
        }
        System.out.println("Employee with highest salary: "+" Name:"+em.geteName()+" Salary: "+em.getSal());

    }

    public void eReturnNumberOfEmpWhereSalaryHigherThanAverage()  {
        System.out.println("Emps with salary higher than average");
        int totalSalary = 0;
        for (Employee e : mainList) {
            totalSalary += e.getSal();
        }
        int listSize = mainList.size();
        int averageSalary = totalSalary/listSize;
        List<Employee> empsMoreThanAvgSal = new ArrayList<>();
        for (Employee e : mainList) {
            if (e.getSal() > averageSalary) {
                empsMoreThanAvgSal.add(e);
            }
        }

        for (Employee e : empsMoreThanAvgSal) {
            System.out.println(e.geteName()+": SAL:"+e.getSal());
        }

    }

    public void fReturnEmployeesWithAGivenManagerNumber(Integer mgrNo) {
        List<Employee> listWithSpecificManagers = new ArrayList<>();
        System.out.println("Emps with "+mgrNo+" as manager");
        for (Employee e : mainList) {
            if (Objects.equals(e.getMGR(), mgrNo)) { // Skal bruge Objects.equals for at håndtere null-værdier.
                listWithSpecificManagers.add(e);
            }
        }
        for (Employee e : listWithSpecificManagers) {
            System.out.println("Name: "+e.geteName()+" MGR: "+e.getMGR());
        }
    }

    public void getDeptNamesWithMoreThanFiveEmployees() {
        Map<Integer, Integer> deptEmployeeCount = new HashMap<>();

        // Count employees per department
        for (Employee emp : mainList) {
            deptEmployeeCount.put(emp.getDeptNo(), deptEmployeeCount.getOrDefault(emp.getDeptNo(), 0) + 1);
        }

        List<String> departmentNames = new ArrayList<>();
        // Find department names with more than 5 employees
        for (Departments dept : depts) {
            if (deptEmployeeCount.getOrDefault(dept.getDeptNo(), 0) > 5) {
                departmentNames.add(dept.getdName());
            }
        }

        for (String s : departmentNames) {
            System.out.println(s);
    }




    }
}
