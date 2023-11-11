package dev.bayun.ms.forms.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

/**
 * @author Максим Яськов
 */

@Data
public final class AnswerId {

    private final String id;

    @JsonCreator
    public AnswerId(@JsonProperty("id") String id) {
        this.id = id;
    }

}
