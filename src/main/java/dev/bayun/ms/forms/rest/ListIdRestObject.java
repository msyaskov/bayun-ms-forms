package dev.bayun.ms.forms.rest;

import com.fasterxml.jackson.annotation.JsonTypeName;
import dev.bayun.ms.forms.domain.core.StringId;
import dev.bayun.sdk.rest.core.RestObject;

import java.util.List;

/**
 * @author Максим Яськов
 */
@JsonTypeName("list_id")
public class ListIdRestObject extends RestObject<List<StringId>, Void> {

    public ListIdRestObject() {
        super();
    }

    public ListIdRestObject(List<StringId> list) {
        super(list, null);
    }
}
