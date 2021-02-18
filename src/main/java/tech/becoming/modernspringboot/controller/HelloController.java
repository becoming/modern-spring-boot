package tech.becoming.modernspringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.becoming.modernspringboot.properties.MainProperties;

@RestController
public class HelloController {

    private final MainProperties properties;

    public HelloController(MainProperties properties) {this.properties = properties;}

    @GetMapping
    public String hello() {
        return properties.getCors();
    }


}
