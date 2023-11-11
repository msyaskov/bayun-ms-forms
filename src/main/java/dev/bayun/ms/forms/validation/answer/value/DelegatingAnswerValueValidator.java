package dev.bayun.ms.forms.validation.answer.value;

import dev.bayun.ms.forms.domain.core.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.Collection;

/**
 * @author Максим Яськов
 */

@RequiredArgsConstructor
public class DelegatingAnswerValueValidator implements AnswerValueValidator {

    private final Collection<AnswerValueValidator> validators;

    @Override
    public void validate(Object value, @NonNull Question question) throws InvalidAnswerValueException {
        for (AnswerValueValidator validator : validators) {
            validator.validate(value, question);
        }
    }
}
