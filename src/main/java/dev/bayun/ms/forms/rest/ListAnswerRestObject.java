package dev.bayun.ms.forms.rest;

import com.fasterxml.jackson.annotation.JsonTypeName;
import dev.bayun.ms.forms.domain.Answer;
import dev.bayun.sdk.rest.core.RestObject;

import java.util.List;

/**
 * @author Максим Яськов
 */
@JsonTypeName("list_answer")
public class ListAnswerRestObject extends RestObject<List<Answer>, Void> {

    public ListAnswerRestObject() {
        super();
    }

    public ListAnswerRestObject(List<Answer> list) {
        super(list, null);
    }
}
