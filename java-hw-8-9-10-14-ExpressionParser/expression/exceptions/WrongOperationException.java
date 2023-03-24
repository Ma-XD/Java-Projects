package expression.exceptions;

public class WrongOperationException extends ParseException{
    public WrongOperationException(String message) {
        super(message);
    }
}
