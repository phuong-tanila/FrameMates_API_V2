package fu.training.FrameMates_BE;

import fu.training.FrameMates_BE.share.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FrameMatesBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrameMatesBeApplication.class, args);
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
						.addMapping("/**")
						.allowedOrigins(
								"**"
						).allowedOrigins("https://framemates.io.vn", "https://studio.framemates.io.vn/")
						.allowedMethods("*");
			}
		};

	}
//	@Bean
//	public FilterRegistrationBean corsFilter() {
//		FilterRegistrationBean registration = new FilterRegistrationBean();
//		registration.setFilter(new CorsFilter());
//		registration.addUrlPatterns("/*");
//		return registration;
//	}
}
