package dev.bayun.ms.forms.service;

import dev.bayun.ms.forms.http.NotFoundException;

/**
 * @author Максим Яськов
 */

public class FormNotFoundException extends NotFoundException {

    public FormNotFoundException() {
        super();
    }

    public FormNotFoundException(String message) {
        super(message);
    }

    public FormNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormNotFoundException(Throwable cause) {
        super(cause);
    }
}
