package studentkitchen.studentkitchen;

import java.util.prefs.PreferenceChangeListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
public class StudentkitchenApplication extends SpringBootServletInitializer {

		
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PreferenceChangeListener.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(StudentkitchenApplication.class, args);
	}

}
