package dev.bayun.ms.forms.domain.core;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.bayun.ms.forms.domain.QuestionType;
import dev.bayun.ms.forms.domain.question.*;
import lombok.Data;

/**
 * @author Максим Яськов
 */
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmptyQuestion.class, name = QuestionType.EMPTY),
        @JsonSubTypes.Type(value = ShortTextQuestion.class, name = QuestionType.SHORT_TEXT),
        @JsonSubTypes.Type(value = LongTextQuestion.class, name = QuestionType.LONG_TEXT),
        @JsonSubTypes.Type(value = RadioQuestion.class, name = QuestionType.RADIO),
        @JsonSubTypes.Type(value = DropdownQuestion.class, name = QuestionType.DROPDOWN),
        @JsonSubTypes.Type(value = CheckboxQuestion.class, name = QuestionType.CHECKBOX),
})
public abstract class Question {

    private String id;

    private String label;

    private String comment;

}
