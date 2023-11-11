package dev.bayun.ms.forms.validation.answer.value;

import dev.bayun.ms.forms.domain.core.Question;
import dev.bayun.ms.forms.domain.question.EmptyQuestion;
import org.springframework.lang.NonNull;

/**
 * @author Максим Яськов
 */
public class EmptyQuestionAnswerValueValidator implements AnswerValueValidator {

    @Override
    public void validate(Object value, @NonNull Question question) throws InvalidAnswerValueException {
        if (!(question instanceof EmptyQuestion)) {
            return;
        }

        if (value != null) {
            throw new InvalidAnswerValueException("Answer value isn't null");
        }
    }
}
