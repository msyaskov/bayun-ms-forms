package dev.bayun.ms.forms.validation.answer;

/**
 * @author Максим Яськов
 */
public class InvalidAnswerException extends RuntimeException {

    public InvalidAnswerException() {
        super();
    }

    public InvalidAnswerException(String message) {
        super(message);
    }

    public InvalidAnswerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAnswerException(Throwable cause) {
        super(cause);
    }
}
