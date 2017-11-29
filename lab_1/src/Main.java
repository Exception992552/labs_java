package lab1;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        Calc calc = new Calc(a, b);
        System.out.println(calc.plus());
        System.out.println(calc.minus());
        System.out.println(calc.mult());
        System.out.println(calc.div());
    }
}
