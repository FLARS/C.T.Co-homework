import java.util.Scanner;

public class UserInputScanner {

    private Scanner scanner = new Scanner(System.in);

    public int nextInt() {
        return scanner.nextInt();
    }


    public String next() {
        return scanner.next();
    }
}