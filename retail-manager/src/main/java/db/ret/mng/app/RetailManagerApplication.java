package db.ret.mng.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "db.ret.mng.*" })
public class RetailManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailManagerApplication.class, args);
	}
}
