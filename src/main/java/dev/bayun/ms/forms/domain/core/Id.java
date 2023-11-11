package dev.bayun.ms.forms.domain.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Максим Яськов
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Id<T> {

    private T id;

}
