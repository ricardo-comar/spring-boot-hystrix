package br.comar.ricardo.stuff.boot.hystrix;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootHystrixApplication extends SpringBootServletInitializer {

    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootHystrixApplication.class);
	}

	public static void main(String[] args) {
		new SpringBootHystrixApplication().configure(
				new SpringApplicationBuilder(SpringBootHystrixApplication.class)).run(args);
	}
}
