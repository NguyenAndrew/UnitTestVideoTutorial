public class Calculator {

    private AdditionService additionService;
    private MultiplicationService multiplicationService;
    private SubtractionService subtractionService;

    Calculator(AdditionService additionService, MultiplicationService multiplicationService, SubtractionService subtractionService) {
        this.additionService = additionService;
        this.multiplicationService = multiplicationService;
        this.subtractionService = subtractionService;
    }

    public int calculateY(int x) {
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
