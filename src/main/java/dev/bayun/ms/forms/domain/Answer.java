package dev.bayun.ms.forms.domain;

import dev.bayun.ms.forms.validation.group.OnCreate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * @author Максим Яськов
 */

@Data
@Document("answers")
public class Answer {

    @Id
    private String id;

    private String authorId;

    private String formId;

    @NotNull(groups = OnCreate.class)
    private Map<String, Object> values;

}
