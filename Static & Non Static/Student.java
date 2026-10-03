class Student {

    // Static variable: one copy shared by all objects
    static int staticCount = 0;

    // Non-static variable: separate copy for each object
    int nonStaticCount = 0;

    Student() {
        staticCount++;
        nonStaticCount++;
    }
}

