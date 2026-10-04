public class Object_Counter {
    public static int count=0;
    public int nonStaticCount = 0;
    public String name;

    public Object_Counter(String name){
        this.name=name;
        count++;
        nonStaticCount++;
    }
    public void details() {
        System.out.println("Name:" +name);
    }

}
