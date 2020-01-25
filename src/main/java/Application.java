public class Application {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        int x = 10;
//        int y = (5 + x) * x - x;
        int y = calculator.calculateY(x);

        System.out.println("My input X = " + x);
        System.out.println("My output Y = " + y);
    }
}
