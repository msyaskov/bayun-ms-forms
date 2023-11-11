package dev.bayun.ms.forms.service;

import dev.bayun.ms.forms.http.NotFoundException;

/**
 * @author Максим Яськов
 */

public class OptionNotFoundException extends NotFoundException {

    public OptionNotFoundException() {
        super();
    }

    public OptionNotFoundException(String message) {
        super(message);
    }

    public OptionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public OptionNotFoundException(Throwable cause) {
        super(cause);
    }
}
