package dev.bayun.ms.forms.domain.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * @author Максим Яськов
 */

public enum FormScope {
    PRIVATE,
    PUBLIC;

    @JsonCreator
    public static FormScope fromValue(String value) {
        Objects.requireNonNull(value);

        for (FormScope scope : FormScope.values()) {
            if (scope.name().equalsIgnoreCase(value)) {
                return scope;
            }
        }

        throw new IllegalArgumentException("unknown FormScope: " + value);
    }

    @JsonValue
    public String value() {
        return this.name().toLowerCase();
    }
}
