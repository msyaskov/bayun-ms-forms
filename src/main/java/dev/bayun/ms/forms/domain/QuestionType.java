package dev.bayun.ms.forms.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * @author Максим Яськов
 */

public final class QuestionType {

    public static final String EMPTY = "empty";

    public static final String SHORT_TEXT = "short_text";

    public static final String LONG_TEXT = "long_text";

    public static final String RADIO = "radio";

    public static final String CHECKBOX = "checkbox";

    public static final String DROPDOWN = "dropdown";

}
