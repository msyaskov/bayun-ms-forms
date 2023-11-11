package dev.bayun.ms.forms.domain.core;

import dev.bayun.ms.forms.validation.group.OnCreate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author Максим Яськов
 */

@Data
@ToString
@Document("forms")
@EqualsAndHashCode
public class Form {

    @Id
    private String id;

    private String authorId;

    @Size(min = 1, max = 255)
    @NotNull(groups = OnCreate.class)
    private String name;

    @NotNull(groups = OnCreate.class)
    private FormScope scope;

    private List<Question> questions;

}
