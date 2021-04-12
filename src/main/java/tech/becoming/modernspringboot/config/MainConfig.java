package tech.becoming.modernspringboot.config;

import com.fasterxml.jackson.databind.Module;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties
@Configuration
public class MainConfig {

    @Bean
    public Module getTryModule() {
        return new TryModule();
    }

}
