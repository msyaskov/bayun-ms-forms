package dev.bayun.ms.forms.rest.form;

import com.fasterxml.jackson.annotation.JsonTypeName;
import dev.bayun.ms.forms.domain.core.Question;
import dev.bayun.sdk.rest.core.RestObject;

/**
 * @author Максим Яськов
 */
@JsonTypeName("question")
public class QuestionRestObject extends RestObject<Question, Void> {

    public QuestionRestObject() {
        super();
    }

    public QuestionRestObject(Question object) {
        super(object, null);
    }
}
