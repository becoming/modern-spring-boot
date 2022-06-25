package tech.becoming.modernspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import co.elastic.apm.attach.ElasticApmAttacher;

@SpringBootApplication
public class ModernSpringBootApplication {

	public static void main(String[] args) {
		if (System.getenv().containsKey("ELASTIC_APM_ENABLED")) {
			ElasticApmAttacher.attach();
		}
		SpringApplication.run(ModernSpringBootApplication.class, args);
	}

}
