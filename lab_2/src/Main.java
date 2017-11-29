
class JThread extends Thread {

    JThread(String name){
        super(name);
    }

    public void run(){

        System.out.printf("Поток %s начал работу... \n", Thread.currentThread().getName());
        int t=0;
        try{
            Thread.sleep(500);
            for(int i =0; i<10; i++)
                System.out.println(t+=i);
        }
        catch(InterruptedException e){
            System.out.println("Поток прерван");
        }
        System.out.printf("Поток %s завершил работу... \n", Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args){
     System.out.println("Главный поток начал работу...");
    new JThread("JThread").start();
    System.out.println("Главный поток завершил работу...");
    }
}