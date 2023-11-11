package dev.bayun.ms.forms.domain.question;

import dev.bayun.ms.forms.domain.core.ValidationRule;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Максим Яськов
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractValidatableQuestion extends AbstractRequireableQuestion {

    private Collection<ValidationRule> validations = new LinkedList<>();

}
