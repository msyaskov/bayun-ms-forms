package dev.bayun.ms.forms.validation.answer;

import dev.bayun.ms.forms.domain.Answer;
import dev.bayun.ms.forms.domain.core.Form;
import dev.bayun.ms.forms.validation.answer.value.AnswerValueValidator;
import dev.bayun.ms.forms.validation.answer.value.InvalidAnswerValueException;
import lombok.RequiredArgsConstructor;

/**
 * @author Максим Яськов
 */

@RequiredArgsConstructor
public class DefaultAnswerValidator implements AnswerValidator {

    private final AnswerValueValidator valueValidator;

    @Override
    public void validate(Answer answer, Form form) {
        try {

            form.getQuestions().forEach(question -> valueValidator.validate(answer.getValues().get(question.getId()), question));

            boolean isContainUnnecessary = answer.getValues().keySet().stream().anyMatch(questionId -> isQuestionNotExistByQuestionId(questionId, form));
            if (isContainUnnecessary) {
                throw new InvalidAnswerException("The answer contains unnecessary questions");
            }
        } catch (InvalidAnswerValueException e) {
            throw new InvalidAnswerException(e);
        }
    }

    private boolean isQuestionNotExistByQuestionId(String questionId, Form form) {
        return form.getQuestions().stream().noneMatch(question -> question.getId().equals(questionId));
    }
}
