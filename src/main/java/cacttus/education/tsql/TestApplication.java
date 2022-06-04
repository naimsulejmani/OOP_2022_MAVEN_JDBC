package cacttus.education.tsql;

import cacttus.education.tsql.infrastructure.CategoryRepository;
import cacttus.education.tsql.models.Category;
import cacttus.education.tsql.models.Employee;
import cacttus.education.tsql.repositories.EmployeeRepository;

import java.time.LocalDate;
import java.util.List;

public class TestApplication {
    public static void main(String[] args) {
//        Employee emp1 = new Employee();
//        Employee emp2 = new Employee();
//        emp1.setEmpId(1);
//        emp2.setEmpId(1);
//
//        System.out.println(emp1.equals(emp2));

        EmployeeRepository employeeRepository = new EmployeeRepository();
        List<Employee> employees = employeeRepository.getAll();
        System.out.println("----------- ALL EMPLOYEES --------------");
        for (Employee e : employees) {
            System.out.println(e);
        }


        System.out.println("\n--------- ONE EMPLOYEE ----------------");
        Employee employee = employeeRepository.get(5);
        System.out.println(employee);

//
        //  System.out.println("INSERT AN EMPLOYEE");
        employee = new Employee(11, "Sulejmani", "Naim", "Profesor", "Mr", LocalDate.now(), LocalDate.now()
                , "address", "city", "prishtine", "12000", "Kosove", "049123123", 0);
//        boolean isCreated = employeeRepository.add(employee);
//        System.out.println("U Regjistrua: " + isCreated);

        System.out.println("UPDATE AN EMPLOYEE");
        employee.setFirstName("Albin");
        employee.setLastName("Hetemi");
        boolean isUpdated = employeeRepository.modify(employee);
        System.out.println("U Azhurnua: " + isUpdated);

        System.out.println("DELETE AN EMPLOYEE");
        //boolean isDeleted = employeeRepository.remove(employee);
        boolean isDeleted = employeeRepository.removeById(employee.getEmpId());
        System.out.println("U Fshi: " + isDeleted);

        System.out.println();
        System.out.println("TEST CATEGORIES");

        CategoryRepository categoryRepository = new cacttus.education.tsql.repositories.CategoryRepository();
        List<Category> categories = categoryRepository.getAllByNameLike("Con");
        System.out.println("CATEGORY WITH LIKE FILTER");
        for (Category c : categories) {
            System.out.println(c);
        }

        System.out.println("Total Categories: " + categoryRepository.count());

    }
}
