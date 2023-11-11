package dev.bayun.ms.forms.domain.core;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

/**
 * @author Максим Яськов
 */

@Data
public class Option {

    private String id;

    private String label;

}
