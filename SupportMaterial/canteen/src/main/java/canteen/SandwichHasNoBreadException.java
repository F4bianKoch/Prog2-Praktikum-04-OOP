package canteen;

public class SandwichHasNoBreadException extends RuntimeException{
    private final String message;

    public SandwichHasNoBreadException(String message) {
        this.message = message;
    }
}
