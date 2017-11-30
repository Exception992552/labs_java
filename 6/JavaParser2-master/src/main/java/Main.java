import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        try {
            String url = "https://rozetka.com.ua/ua/universalnye-mobilnye-batarei/c387969";
            JavaParser parser = new JavaParser();
            final long startTime = System.currentTimeMillis();
            parser.parseCategory();
            final long endTime = System.currentTimeMillis();

            System.out.println("Total execution time: " + (endTime - startTime));
        }
        catch (IOException x)
        {
            System.out.println(x);
        }
    }
}
