package project.lesson.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	public static final String ALLOWED_METHOD_NAMES =
			"GET,POST,PUT,DELETE,OPTIONS,HEAD";

	@Override
	public void addCorsMappings(CorsRegistry corsRegistry) {

		corsRegistry.addMapping("/**")
				.allowedMethods(ALLOWED_METHOD_NAMES.split(","));
	}
}
