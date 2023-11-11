package dev.bayun.ms.forms.domain.question;

import dev.bayun.ms.forms.domain.core.Option;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Максим Яськов
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractOptionQuestion extends AbstractRequireableQuestion {

    private List<Option> options = new LinkedList<>();

}
