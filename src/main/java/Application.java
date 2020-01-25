public class Application {
    public static void main(String[] args) {

        AdditionService additionService = new AdditionService();
        MultiplicationService multiplicationService = new MultiplicationService();
        SubtractionService subtractionService = new SubtractionService();

        Calculator calculator = new Calculator(additionService, multiplicationService, subtractionService);

        int x = 10;
        int y = calculator.calculateY(x);

        System.out.println("My input X = " + x);
        System.out.println("My output Y = " + y);
    }
}
