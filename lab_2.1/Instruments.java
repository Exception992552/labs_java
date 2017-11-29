package lab2;

interface Instruments {
    final static String key = "До мажор";
    public void play();
}
class Drum implements Instruments {
    public void play() {
        System.out.println("бум бац бац бум бац бац");
    }
}
class Guitar implements Instruments {
    public void play() {
        System.out.println("до ми соль до ре до");
    }
}
class Main1 {
    public static void main(String[] args) {
        Instruments d = new Drum();
        Instruments g = new Guitar();
        d.play();
        g.play();
    }
}
