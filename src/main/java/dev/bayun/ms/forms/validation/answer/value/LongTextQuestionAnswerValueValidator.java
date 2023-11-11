package dev.bayun.ms.forms.validation.answer.value;

import dev.bayun.ms.forms.domain.core.Question;
import dev.bayun.ms.forms.domain.question.LongTextQuestion;
import org.springframework.lang.NonNull;

/**
 * @author Максим Яськов
 */
public class LongTextQuestionAnswerValueValidator implements AnswerValueValidator {

    @Override
    public void validate(Object value, @NonNull Question question) throws InvalidAnswerValueException {
        if (!(question instanceof LongTextQuestion longTextQuestion)) {
            return;
        }

        if (longTextQuestion.isRequired() && value == null) {
            throw new InvalidAnswerValueException("Answer value required, but missing");
        }

        if (value != null && value.getClass() != String.class) {
            throw new InvalidAnswerValueException("Answer value must be an instance of " + String.class);
        }
    }
}
