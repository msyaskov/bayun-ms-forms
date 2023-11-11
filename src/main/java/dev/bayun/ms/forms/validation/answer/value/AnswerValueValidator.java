package dev.bayun.ms.forms.validation.answer.value;

import dev.bayun.ms.forms.domain.core.Question;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @author Максим Яськов
 */
public interface AnswerValueValidator {

    void validate(@Nullable Object value, @NonNull Question question) throws InvalidAnswerValueException;

}
