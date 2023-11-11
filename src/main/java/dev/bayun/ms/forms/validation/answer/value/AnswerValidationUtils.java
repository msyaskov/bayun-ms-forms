package dev.bayun.ms.forms.validation.answer.value;

import dev.bayun.ms.forms.domain.core.Question;

import java.util.Collection;

/**
 * @author Максим Яськов
 */
public class AnswerValidationUtils {

    public static boolean isRequired(Question abstractQuestion) {
        return false;
    }

    public static boolean isString(Object value) {
        return value instanceof String;
    }

    public static boolean isOptionId(String value, Question abstractQuestion) {
        return true;
    }


    public static boolean isCollectionOf(Object answerValue, Class<?> aClass) {
        if (!(answerValue instanceof Collection<?>)) {
            return false;
        }

        for (Object o : ((Collection<?>) answerValue)) {
            if (!aClass.isInstance(o)) {
                return false;
            }
        }

        return true;
    }
}
