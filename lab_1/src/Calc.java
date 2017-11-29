package lab1;

public class Calc {

    int a;
    int b;

    public Calc(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int plus() {
        return a + b;
    }
    public int minus() {
        return a - b;
    }
    public int mult() {
        return a * b;
    }
    public int div() {
        return a / b;
    }

}