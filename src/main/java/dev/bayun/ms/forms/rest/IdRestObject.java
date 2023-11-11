package dev.bayun.ms.forms.rest;

import com.fasterxml.jackson.annotation.JsonTypeName;
import dev.bayun.ms.forms.domain.core.StringId;
import dev.bayun.sdk.rest.core.RestObject;

/**
 * @author Максим Яськов
 */
@JsonTypeName("id")
public class IdRestObject extends RestObject<StringId, Void> {

    public IdRestObject() {
        super();
    }

    public IdRestObject(StringId object) {
        super(object, null);
    }
}
