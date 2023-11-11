package dev.bayun.ms.forms.rest.form;

import com.fasterxml.jackson.annotation.JsonTypeName;
import dev.bayun.ms.forms.domain.core.Option;
import dev.bayun.ms.forms.domain.core.Question;
import dev.bayun.sdk.rest.core.RestObject;

/**
 * @author Максим Яськов
 */
@JsonTypeName("option")
public class OptionRestObject extends RestObject<Option, Void> {

    public OptionRestObject() {
        super();
    }

    public OptionRestObject(Option object) {
        super(object, null);
    }
}
