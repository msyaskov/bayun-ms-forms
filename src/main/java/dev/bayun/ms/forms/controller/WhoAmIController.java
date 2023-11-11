package dev.bayun.ms.forms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Максим Яськов
 */
@RestController
@RequestMapping(path = "/whoami")
public class WhoAmIController {

    @GetMapping
    public String get() {
        return "I am ms-forms";
    }

}
