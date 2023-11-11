package dev.bayun.ms.forms.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bayun.ms.forms.rest.form.FormRestObject;
import dev.bayun.ms.forms.rest.form.ListFormRestObject;
import dev.bayun.ms.forms.rest.form.OptionRestObject;
import dev.bayun.ms.forms.rest.form.QuestionRestObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Максим Яськов
 */
@Configuration
public class ObjectMapperConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerSubtypes(FormRestObject.class, QuestionRestObject.class, OptionRestObject.class, ListAnswerRestObject.class, ListFormRestObject.class, ListIdRestObject.class);

        return objectMapper;
    }
}
