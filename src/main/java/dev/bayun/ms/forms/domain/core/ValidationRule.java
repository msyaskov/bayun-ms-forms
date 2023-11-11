package dev.bayun.ms.forms.domain.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * @author Максим Яськов
 */

public enum ValidationRule {
    REQUIRED,
    EMAIL,
    PHONE,
    DECIMAL,
    INTEGER;

    @JsonCreator
    public static ValidationRule fromValue(String value) {
        Objects.requireNonNull(value);

        for (ValidationRule rule : ValidationRule.values()) {
            if (rule.name().equalsIgnoreCase(value)) {
                return rule;
            }
        }

        throw new IllegalArgumentException("unknown ValidationRule: " + value);
    }

    @JsonValue
    public String value() {
        return this.name().toLowerCase();
    }

}
