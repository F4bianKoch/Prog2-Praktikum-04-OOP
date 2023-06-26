package canteen;

public class SandwichHasTooFewIngredientsException extends RuntimeException{
    private final String message;

    public SandwichHasTooFewIngredientsException(String message) {
        this.message = message;
    }
}
