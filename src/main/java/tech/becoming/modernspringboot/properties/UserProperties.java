package tech.becoming.modernspringboot.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.user")
@Getter
@Setter
public class UserProperties {

    private int maxAge = 65;
    private int firstName = 65;
}
