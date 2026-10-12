import java.util.Scanner;

class Student {
    int age;                       // Instance variable -> stored in the object on the Heap

    Student(int age) {
        this.age = age;
    }

    void display(int marksInput) {
        int marks = marksInput;    // Local variable -> stored in display()'s stack frame

        System.out.println("Object Identity Hash Code: " + System.identityHashCode(this));
        System.out.println("Instance Variable (age): " + age);
        System.out.println("Local Variable (marks): " + marks);
    }
}

public class StackHeapDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Age: ");
        int age = sc.nextInt();

        System.out.print("Marks: ");
        int marks = sc.nextInt();

        Student s = new Student(age);   // 's' (reference) is on the Stack, the object is on the Heap
        System.out.println("Student Object Created");
        s.display(marks);

        sc.close();
    }
}