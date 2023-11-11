package dev.bayun.ms.forms.validation.answer.value;

import dev.bayun.ms.forms.domain.core.Question;
import dev.bayun.ms.forms.domain.question.DropdownQuestion;
import dev.bayun.ms.forms.domain.question.RadioQuestion;
import org.springframework.lang.NonNull;

import java.util.Collection;

/**
 * @author Максим Яськов
 */
public class DropdownQuestionAnswerValueValidator implements AnswerValueValidator {

    @Override
    @SuppressWarnings("unchecked")
    public void validate(Object value, @NonNull Question question) throws InvalidAnswerValueException {
        if (!(question instanceof DropdownQuestion dropdownQuestion)) {
            return;
        }

        if (dropdownQuestion.isRequired() && value == null) {
            throw new InvalidAnswerValueException("Answer value required, but missing");
        }

        if (value != null) {
            if (!AnswerValidationUtils.isCollectionOf(value, String.class)) {
                throw new InvalidAnswerValueException(String.format("Answer value (%s) must be an instance of Collection of %s", value, String.class));
            }

            Collection<String> radioValue = (Collection<String>) value;

            if (radioValue.isEmpty()) {
                if (dropdownQuestion.isRequired()) {
                    throw new InvalidAnswerValueException(String.format("Answer value (%s) must have one value", radioValue));
                } else {
                    return;
                }
            }

            if (radioValue.size() > 1) {
                throw new InvalidAnswerValueException(String.format("Answer value (%s) must have one value", radioValue));
            }

            String optionId = radioValue.iterator().next();
            boolean questionContainsOptionById = dropdownQuestion.getOptions().stream().anyMatch(option -> option.getId().equals(optionId));
            if (!questionContainsOptionById) {
                throw new InvalidAnswerValueException(String.format("%s doesn't have an option with provided optionId (%s)", dropdownQuestion, radioValue));
            }
        }
    }
}
