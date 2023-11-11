package dev.bayun.ms.forms.validation.answer.value;

import dev.bayun.ms.forms.domain.core.Option;
import dev.bayun.ms.forms.domain.core.Question;
import dev.bayun.ms.forms.domain.question.CheckboxQuestion;
import dev.bayun.ms.forms.domain.question.DropdownQuestion;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Максим Яськов
 */
public class CheckboxQuestionAnswerValueValidator implements AnswerValueValidator {

    @Override
    @SuppressWarnings("unchecked")
    public void validate(Object value, @NonNull Question question) throws InvalidAnswerValueException {
        if (!(question instanceof CheckboxQuestion checkboxQuestion)) {
            return;
        }

        if (checkboxQuestion.isRequired() && value == null) {
            throw new InvalidAnswerValueException("Answer value required, but missing");
        }

        if (value != null) {
            if (!AnswerValidationUtils.isCollectionOf(value, String.class)) {
                throw new InvalidAnswerValueException(String.format("Answer value (%s) must be an instance of Collection of %s", value, String.class));
            }

            Collection<String> checkboxValue = (Collection<String>) value;

            if (checkboxValue.isEmpty()) {
                if (checkboxQuestion.isRequired()) {
                    throw new InvalidAnswerValueException(String.format("Answer value (%s) must have one value", checkboxValue));
                } else {
                    return;
                }
            }

            boolean questionContainsOptionsById = checkboxQuestion.getOptions().stream()
                    .map(Option::getId)
                    .collect(Collectors.toCollection(LinkedList::new))
                    .containsAll(checkboxValue);

            if (!questionContainsOptionsById) {
                throw new InvalidAnswerValueException(String.format("%s doesn't have an option with provided optionId", checkboxQuestion));
            }
        }
    }
}
