public class Calculator {

    public int calculateY(int x) {
        AdditionService additionService = new AdditionService();
        MultiplicationService multiplicationService = new MultiplicationService();
        SubtractionService subtractionService = new SubtractionService();

        // y = x
        int y = x;

        // y = 5 + x
        y = additionService.add(5, y);

        // y = (5 + x) * x
        y = multiplicationService.multiply(y, x);

        // y = (5 + x) * x - x
        y = subtractionService.subtract(y, x);

        return y;
    }
}
