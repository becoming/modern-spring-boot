package tech.becoming.modernspringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.becoming.modernspringboot.properties.UserProperties;

@RestController
public class HelloController {

    private final UserProperties properties;

    public HelloController(UserProperties properties) {this.properties = properties;}

    @GetMapping
    public int hello() {
        return properties.getMaxAge();
    }


}
