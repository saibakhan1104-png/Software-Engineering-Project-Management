public class Mainstd {
    public static void main(String[] args) {

        Student s1 = new Student();
        Student s2 = new Student();
        Student s3 = new Student();

        // Static count
        System.out.println("Static Count = " + Student.staticCount);

        // Non-static count
        System.out.println("s1 Non-Static Count = " + s1.nonStaticCount);
        System.out.println("s2 Non-Static Count = " + s2.nonStaticCount);
        System.out.println("s3 Non-Static Count = " + s3.nonStaticCount);
    }
}