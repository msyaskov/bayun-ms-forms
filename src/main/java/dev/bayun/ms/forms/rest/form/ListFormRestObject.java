package dev.bayun.ms.forms.rest.form;

import com.fasterxml.jackson.annotation.JsonTypeName;
import dev.bayun.ms.forms.domain.core.Form;
import dev.bayun.sdk.rest.core.RestObject;

import java.util.List;

/**
 * @author Максим Яськов
 */
@JsonTypeName("list_form")
public class ListFormRestObject extends RestObject<List<Form>, Void> {

    public ListFormRestObject() {
        super();
    }

    public ListFormRestObject(List<Form> list) {
        super(list, null);
    }
}
