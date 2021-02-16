package tech.becoming.modernspringboot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.user")
public class UserProperties {

    private int maxaAge = 65;

    public int getMaxaAge() {
        return maxaAge;
    }

    public void setMaxaAge(int maxaAge) {
        this.maxaAge = maxaAge;
    }
}
