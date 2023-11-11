package dev.bayun.ms.forms.validation.answer;

import dev.bayun.ms.forms.validation.answer.value.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Максим Яськов
 */

@Configuration
public class AnswerValidatorConfiguration {

    @Bean
    public AnswerValidator defaultAnswerValidator() {
        return new DefaultAnswerValidator(delegatingAnswerValueValidator());
    }

    @Bean
    public AnswerValueValidator delegatingAnswerValueValidator() {
        return new DelegatingAnswerValueValidator(List.of(
                new EmptyQuestionAnswerValueValidator(),
                new ShortTextQuestionAnswerValueValidator(),
                new LongTextQuestionAnswerValueValidator(),
                new RadioQuestionAnswerValueValidator(),
                new DropdownQuestionAnswerValueValidator(),
                new CheckboxQuestionAnswerValueValidator()
        ));
    }

}
