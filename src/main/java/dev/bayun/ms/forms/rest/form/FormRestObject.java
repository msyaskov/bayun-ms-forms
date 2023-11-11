package dev.bayun.ms.forms.rest.form;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeName;
import dev.bayun.ms.forms.domain.core.Form;
import dev.bayun.sdk.rest.core.RestObject;

/**
 * @author Максим Яськов
 */
@JsonTypeName("form")
public class FormRestObject extends RestObject<Form, FormMeta> {

    public FormRestObject() {
        super();
    }

    public FormRestObject(Form object, FormMeta meta) {
        super(object, meta);
    }
}
