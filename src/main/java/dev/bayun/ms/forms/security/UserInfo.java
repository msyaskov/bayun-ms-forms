package dev.bayun.ms.forms.security;

import lombok.Data;

import java.util.UUID;

/**
 * @author Максим Яськов
 */

@Data
public class UserInfo {

    private UUID id;

    private String email;

    private String username;

    private String firstName;

    private String lastName;

    private String pictureUrl;

}
