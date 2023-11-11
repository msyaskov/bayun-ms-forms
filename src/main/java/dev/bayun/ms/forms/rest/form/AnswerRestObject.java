package dev.bayun.ms.forms.rest.form;

import com.fasterxml.jackson.annotation.JsonTypeName;
import dev.bayun.ms.forms.domain.Answer;
import dev.bayun.sdk.rest.core.RestObject;

/**
 * @author Максим Яськов
 */
@JsonTypeName("form_answer")
public class AnswerRestObject extends RestObject<Answer, Void> {

    public AnswerRestObject() {
        super();
    }

    public AnswerRestObject(Answer object) {
        super(object, null);
    }
}
