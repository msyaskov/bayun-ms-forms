package dev.bayun.ms.forms.service;

import dev.bayun.ms.forms.http.NotFoundException;

/**
 * @author Максим Яськов
 */

public class QuestionNotFoundException extends NotFoundException {

    public QuestionNotFoundException() {
        super();
    }

    public QuestionNotFoundException(String message) {
        super(message);
    }

    public QuestionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuestionNotFoundException(Throwable cause) {
        super(cause);
    }
}
