package dev.bayun.ms.forms.validation.answer.value;

/**
 * @author Максим Яськов
 */
public class InvalidAnswerValueException extends RuntimeException{

    public InvalidAnswerValueException() {
        super();
    }

    public InvalidAnswerValueException(String message) {
        super(message);
    }

    public InvalidAnswerValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAnswerValueException(Throwable cause) {
        super(cause);
    }
}
