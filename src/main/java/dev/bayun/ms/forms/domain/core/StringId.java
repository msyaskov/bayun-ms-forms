package dev.bayun.ms.forms.domain.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Максим Яськов
 */
public class StringId extends Id<String> {

    @JsonCreator
    public StringId(@JsonProperty("id") String id) {
        super(id);
    }

}
