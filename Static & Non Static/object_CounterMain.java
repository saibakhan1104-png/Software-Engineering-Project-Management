public class object_CounterMain {
    public static void main(String[] args){
        Object_Counter name1=new Object_Counter("Saiba ");
        Object_Counter name2=new Object_Counter("Nilima ");
        Object_Counter name3=new Object_Counter("Shurovy ");

        name1.details();
        name2.details();
        name3.details();

        System.out.println("Static Count: " +Object_Counter.count);
        System.out.println("Non Static Count:" +name1.nonStaticCount);
        System.out.println("Non Static Count:" +name2.nonStaticCount);
        System.out.println("Non Static Count:" +name3.nonStaticCount);



    }
}
