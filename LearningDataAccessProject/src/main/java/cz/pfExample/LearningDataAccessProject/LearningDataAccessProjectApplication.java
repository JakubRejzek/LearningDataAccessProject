package cz.pfExample.LearningDataAccessProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.io.Console;

@SpringBootApplication
public class LearningDataAccessProjectApplication {
	public static void main(String[] args) {

		SpringApplication.run(LearningDataAccessProjectApplication.class, args);
	}
}
@Configuration
@EnableScheduling
@ConditionalOnProperty(name="sheduling.enabled", matchIfMissing = true)
class ShedulingCOnfiguration
{

}

