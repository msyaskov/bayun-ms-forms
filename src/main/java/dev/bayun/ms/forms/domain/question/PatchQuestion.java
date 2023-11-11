package dev.bayun.ms.forms.domain.question;

import dev.bayun.ms.forms.domain.core.Question;
import lombok.*;

/**
 * @author Максим Яськов
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatchQuestion  {

    private String id;
    private String label;
    private String comment;
    private Boolean required;

}
