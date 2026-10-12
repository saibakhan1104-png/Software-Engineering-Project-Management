import java.util.Scanner;

class Employee {
    int salary;                    // Instance variable -> lives inside the Employee object on the Heap

    Employee(int salary) {
        this.salary = salary;
    }

    void display(int bonusInput) {
        int bonus = bonusInput;    // Local variable -> display()'s stack frame

        System.out.println("\nInside display() Method:");
        System.out.println("Salary (Instance Variable): " + salary);
        System.out.println("Bonus (Local Variable): " + bonus);

        // Local inner class, declared inside the method
        class Report {
            void show() {
                // show() runs in its own stack frame
                // It can read 'salary' through the enclosing Employee object
                System.out.println("Report: Employee salary is " + salary);
            }
        }

        Report r = new Report();   // The Report object lives on the Heap
        System.out.println("\nReport Object Created");
        System.out.println("Report Identity Hash Code: " + System.identityHashCode(r));
        r.show();
    }
}

public class InnerClassMemoryDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter salary: ");
        int salary = sc.nextInt();

        System.out.print("Enter bonus: ");
        int bonus = sc.nextInt();

        Employee emp = new Employee(salary);
        System.out.println("\nEmployee Object Created");
        System.out.println("Employee Identity Hash Code: " + System.identityHashCode(emp));

        emp.display(bonus);

        System.out.println("\nMemory Concept:");
        System.out.println("Employee object -> Heap");
        System.out.println("Report object -> Heap");
        System.out.println("salary -> Instance field");
        System.out.println("bonus -> Local variable in display()");
        System.out.println("show() -> Executes in its own method frame");

        sc.close();
    }
}