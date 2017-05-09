package csulb.edu.aasthajain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import csulb.edu.aasthajain.controller.UserServiceLayer;

@SpringBootApplication
public class SpringBootBasewebappApplication implements ApplicationRunner {

	@Autowired // Tells the application context to inject an instance of
	private UserServiceLayer userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBasewebappApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		userService.performService("insertBatch");
	}
}
