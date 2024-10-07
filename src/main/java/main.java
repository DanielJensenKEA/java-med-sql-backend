import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
//        EmployeeRepository er = new EmployeeRepository();

        try {
            EmployeeGeneration eg = new EmployeeGeneration();

            //Opgave A En metode, der udskrivermedarbejdernummer og -navnpå alle medarbejdere.
            eg.aPrintAllEmpAndNo();

            //Opgave B En metode, der udskriver medarbejdernummer og -navn på medarbejdere, der starter med ”S”.
            eg.bPrintAllEmpNoAndNamesStartWithS();

            //Opgave C En metode, der udskriver den samlede lønudgift for alle medarbejdere
            eg.cPrintTotalSalaryOfEmps();

            //Opgave D En metode der udskriver navnet på den højst lønnede medarbejder
            eg.dReturnHighestSalariedPerson();

            //Opgave E En metode, der udskriver antalmedarbejdere som tjener mere end gennemsnittet
            eg.eReturnNumberOfEmpWhereSalaryHigherThanAverage();

            //Opgave F En metode, der udskriver navnet på medarbejdere med en given manager
            Integer mgrNo = 7698;
            eg.fReturnEmployeesWithAGivenManagerNumber(mgrNo);

            //Opgave G En metode, der udskriver afdelingsnavn på afdeling med mere end 5 medarbejdere.
            eg.getDeptNamesWithMoreThanFiveEmployees(); //Virker ikke

            /*
            DEL 2 KRÆVER OMSKRIVNING AF METODER TIL AT RETURNERE DATA FREM FOR SYSOUTS. EMPLOYEEREPOSITORY BLEV BRUGT TIL NEDENSTÅENDE
            VI BRUGER DERFOR EMPLOYEEGENERATION I FHT. AT RETURNERE DATA
            */
//            //Opgave A En metode, der udskrivermedarbejdernummer og -navnpå alle medarbejdere.
//            er.aPrintAllEmpAndNo();
//
//            //Opgave B En metode, der udskriver medarbejdernummer og -navn på medarbejdere, der starter med ”S”.
//            er.bPrintAllEmpNoAndNamesStartWithS();
//
//            //Opgave C En metode, der udskriver den samlede lønudgift for alle medarbejdere
//            er.cPrintTotalSalaryOfEmps();
//
//            //Opgave D En metode der udskriver navnet på den højst lønnede medarbejder
//            er.dReturnHighestSalariedPerson();
//
//            //Opgave E En metode, der udskriver antalmedarbejdere som tjener mere end gennemsnittet
//            er.eReturnNumberOfEmpWhereSalaryHigherThanAverage();
//
//            //Opgave F En metode, der udskriver navnet på medarbejdere med en
//            //          given manager(managers navngives som parametertil metoden)
//            er.fReturnEmployeesWithAGivenManagerNumber();
//
//            //Opgave G En metode, der udskriver afdelingsnavn på afdeling med mere end 5 medarbejdere.
//            er.gReturnDeptNameOnDeptsWithMoreThanFiveEmps();






            System.out.println("\n***********Reached end of table.***********");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
