package ro.utcn.sd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ro.utcn.sd.entity.Internship;
import ro.utcn.sd.service.InternshipService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class SdApplication {

	public static void main(String[] args) {

		SpringApplication.run(SdApplication.class, args);
		}

}
