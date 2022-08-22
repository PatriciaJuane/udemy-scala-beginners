package playground;

// class which makes the analogies between Scala objects and Java statics
public class JavaPlayground {
    public static void main(String args[]) {
        System.out.println(Person.N_EYES);
    }
}

class Person {
    public static final int N_EYES = 2; // does not rely on an instance of a Person to access to this variable. IS A CLASS FUNCTIONALITY, NOT AN INSTANCE FUNCTIONALITY.
}

