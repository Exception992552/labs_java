package lab2;

abstract class Pet {
    void voice() {
    }
}
class Dog extends Pet {
    void voice() {
        System.out.println("Гав-гав");
    }
}
class Tiger extends Pet {
    void voice() {
        System.out.println("Ррр-р-р");
    }
}
class Cat extends Pet {
    void voice() {
        System.out.println("Мяу-мяу");
    }
}

class Main {
    public static void main(String[] args) {
        Pet d = new Dog();
        Pet t = new Tiger();
        Pet c = new Cat();
        d.voice();
        t.voice();
        c.voice();
    }
}
