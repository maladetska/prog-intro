/** Prints {@code Hello, World!} on the console. */
public class HelloWorld3 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java HelloWorld3 <user 1> <user 2> ...");
        }

        System.out.printf("Hello");
        for (int i = 0; i < args.length; i++) {
            System.out.printf(", %s", args[i]);
        }

//        int i = 0;
//        while (i < args.length; ) {
//            System.out.printf(", %s", args[i]);
//            i++; // i = i + 1
//        }

        System.out.println("!");
    }
}
