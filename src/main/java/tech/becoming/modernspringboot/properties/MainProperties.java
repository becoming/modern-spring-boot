package tech.becoming.modernspringboot.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.api")
@Getter
@Setter
public class MainProperties {

    private String cors = "localhost";
    private int maxPageSize = 50;

    @Setter(value = AccessLevel.NONE)
    private int maxNameChars = 255;

}
