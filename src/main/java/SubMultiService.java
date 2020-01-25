public class SubMultiService {

    SubtractionService subtractionService;
    MultiplicationService multiplicationService;

    public SubMultiService(SubtractionService subtractionService, MultiplicationService multiplicationService) {
        this.subtractionService = subtractionService;
        this.multiplicationService = multiplicationService;
    }


    public int subtractThenMultiplyBy2(int x, int y) {
        int output = subtractionService.subtract(x, y);
        return multiplicationService.multiply(output, 2);
    }
}
