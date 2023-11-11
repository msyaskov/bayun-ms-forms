package dev.bayun.ms.forms.validation.answer;

import dev.bayun.ms.forms.domain.Answer;
import dev.bayun.ms.forms.domain.core.Form;

/**
 * @author Максим Яськов
 */
public interface AnswerValidator {

    void validate(Answer answer, Form form);

}
