public class Calculator {

    private AdditionService additionService;
    private MultiplicationService multiplicationService;
    private SubtractionService subtractionService;
    private SubMultiService subMultiService;

    Calculator(AdditionService additionService, MultiplicationService multiplicationService, SubtractionService subtractionService, SubMultiService subMultiService) {
        this.additionService = additionService;
        this.multiplicationService = multiplicationService;
        this.subtractionService = subtractionService;
        this.subMultiService = subMultiService;
    }

    public int calculateY(int x) {
        // y = x
        int y = x;

        if (x < 0) {
            // y = 5 - x
            return subtractionService.subtract(5, y);
        }

        // y = 5 + x
        y = additionService.add(5, y);

        // y = (5 + x) * x
        y = multiplicationService.multiply(y, x);

        // y = (((5 + x) * x) - 5) * 2
        y = subMultiService.subtractThenMultiplyBy2(y, x);


        return y;
    }
}
