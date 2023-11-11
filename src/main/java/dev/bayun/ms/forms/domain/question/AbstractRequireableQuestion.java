package dev.bayun.ms.forms.domain.question;

import dev.bayun.ms.forms.domain.core.Question;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Максим Яськов
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractRequireableQuestion extends Question {

    private boolean required;

}
