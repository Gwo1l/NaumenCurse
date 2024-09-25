package Gwollya.inc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Employee {
    private String fullName;
    private Integer age;
    private String department;
    private Double salary;

    public static void start() {
        Employee empl1 = new Employee("Солодушкин Святослав Игоревич", 80,
                "Протоколы Интернет", 15000d);
        Employee empl2 = new Employee("Клепинин Александр Владимирович", 48,
                "ООП", 200_000d);
        Employee empl3 = new Employee("Ершова Анна Александровна", 35,
                "Матан", 80_000d);
        Employee empl4 = new Employee("Зиятдинов Александр Владимирович", 24,
                "Питон", 130_000d);
        Employee empl5 = new Employee("Окуловский Юрий Александрович", 29,
                "Машинное обучение", 500_000d);
        ArrayList<Employee> employeesList = new ArrayList<>();
        employeesList.add(empl1);
        employeesList.add(empl2);
        employeesList.add(empl3);
        employeesList.add(empl4);
        employeesList.add(empl5);

        List<Employee> sortedEmployeesList = employeesList.stream()
                .filter(employee -> employee.age > 30)
                .collect(Collectors.toList());
        sortedEmployeesList.forEach(el -> System.out.println(el.fullName));
    }

    public Employee(String fullName, Integer age, String department, Double salary) {
        this.fullName = fullName;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
