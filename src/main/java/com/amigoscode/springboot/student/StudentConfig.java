package com.amigoscode.springboot.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
  @Bean
  CommandLineRunner commandLineRunner(StudentRepository repository){
    return args -> {
      Student alexander = new Student("Alexander", "Alexander@gmail.com",
          LocalDate.of(2000, Month.APRIL, 5));
      Student mihail = new Student("Mihail", "Mihail@gmail.com",
          LocalDate.of(2001, Month.JULY, 14));
      Student andrey = new Student("Andrey", "Andrey@gmail.com",
          LocalDate.of(2003, Month.AUGUST, 27));

      repository.saveAll(List.of(alexander,mihail,andrey));
    };
  }
}
