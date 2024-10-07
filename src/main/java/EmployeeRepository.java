import java.sql.*;

public class EmployeeRepository {

    Connection con;

    public EmployeeRepository() {
        con = runDatabaseConnection();

    }
    public Connection runDatabaseConnection() {
        String database="jdbc:mysql://localhost:3306/emp_dept";
        String user="daniel";
        String password="leinad";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(database, user, password);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
    public void aPrintAllEmpAndNo() throws SQLException {
        String query = "SELECT ENAME, EMPNO FROM emp";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("***All Names And Employee Numbers ***");
        while(rs.next()) {
            System.out.println("Name: "+rs.getString("ENAME") + ": " + "EmpNo: "+rs.getInt("EMPNO"));
        }
        System.out.println(" ");
    }
    public void bPrintAllEmpNoAndNamesStartWithS() throws SQLException {
        String query = "SELECT ENAME, EMPNO FROM emp WHERE ENAME LIKE 'S%'";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        System.out.println("***All Names that starts with S And Employee Numbers ***");
        while(rs.next()) {
            System.out.println("Name: "+rs.getString("ENAME") + ": "+ "No: "+rs.getInt("EMPNO"));
        }
        System.out.println(" ");
    }
    public void cPrintTotalSalaryOfEmps() throws SQLException {
        String query ="SELECT SUM(SAL) AS TotalSalary FROM emp;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("*** Total of All Salaries ***");
        while(rs.next()) {
            System.out.println("Total Salary: "+rs.getInt("TotalSalary"));
        }
        System.out.println(" ");
    }
    public void dReturnHighestSalariedPerson() throws SQLException {
        String query = "SELECT ENAME, SAL FROM emp WHERE SAL = (SELECT MAX(SAL) FROM emp)";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("*** Return Highest Salaried Person ***");
        while(rs.next()) {
            System.out.println("Name: "+rs.getString("ENAME") + ": " + rs.getInt("SAL"));
        }
        System.out.println(" ");
    }
    public void eReturnNumberOfEmpWhereSalaryHigherThanAverage() throws SQLException {
        String query = "SELECT ENAME, SAL FROM EMP WHERE SAL > (SELECT AVG(SAL) FROM EMP)";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("*** Return Number of Employees Where Sal is Higher Than Average ***");
        while(rs.next()) {
            System.out.println("Name: "+rs.getString("ENAME") + ": " + rs.getInt("SAL"));
        }

        String queryNumber = "SELECT COUNT(*) AS 'Number of Employees' FROM EMP WHERE SAL >(SELECT AVG(SAL) FROM emp);";
        Statement stmt2 = con.createStatement();
        ResultSet rs2 = stmt2.executeQuery(queryNumber);
        while(rs2.next()) {
            System.out.println("Number of employees with salary higher than average: "+ rs2.getInt("Number of Employees"));
        }
        System.out.println(" ");
    }
    public void fReturnEmployeesWithAGivenManagerNumber() throws SQLException {
//        String query = "SELECT emp.ENAME AS 'Name', emp.SAL, emp.MGR AS 'Manager Number', emp2.ENAME AS 'MNGR Name' " +
//                "FROM emp JOIN emp as emp2 on emp.MGR = emp2.EMPNO WHERE emp.MGR = '7698'";
//        Statement stmt = con.createStatement();
//        ResultSet rs = stmt.executeQuery(query);
//        while(rs.next()) {
//            System.out.println("Name: "+rs.getString("Name")+": "+"Salary: "+rs.getInt("SAL") + ": "+
//                    "MGR Number: "+rs.getInt("Manager Number") +": "+"MNGR Name: "+rs.getString("MNGR Name"));
//        }
        String paramQuery = "SELECT emp.ENAME AS 'Name', emp.SAL, emp.MGR AS 'Manager Number', emp2.ENAME AS 'MNGR Name'" + "FROM emp JOIN emp as emp2 on emp.MGR = emp2.EMPNO WHERE emp.MGR =?";
        PreparedStatement ps = con.prepareStatement(paramQuery);
        ps.setInt(1, 7698);
        ResultSet rs = ps.executeQuery();
        System.out.println("*** Return Employees with a given MNGR number ***");
        while(rs.next()) {
            System.out.println("Name: "+rs.getString("Name")+": "+"Salary: "+rs.getInt("SAL") + ": " + "MGR Number: "+rs.getInt("Manager Number") +": "+"MNGR Name: "+rs.getString("MNGR Name"));
        }

        System.out.println(" ");
    }
    public void gReturnDeptNameOnDeptsWithMoreThanFiveEmps() throws SQLException {
        String query = "SELECT emp.ENAME, emp.DEPTNO AS 'Department No', dept.DNAME FROM emp JOIN dept ON emp.DEPTNO = dept.DEPTNO WHERE emp.DEPTNO IN (SELECT DEPTNO FROM emp GROUP BY DEPTNO HAVING COUNT(*) > 5)";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("*** Return Department names with more than five employees ***");
        while(rs.next()) {
            System.out.println("Name: "+rs.getString("emp.ENAME")+": "+"Sal: "+rs.getInt("Department No")+": "+rs.getString("dept.DNAME"));
        }
        System.out.println(" ");

    }



}
