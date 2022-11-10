package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            studentRepository repository){
        return args ->{
            Student mariam = new Student(
           1l,
                    "Mariam",
                    "Mariam@gmail.com",
                    LocalDate.of(2000, Month.JANUARY,10)

            );
            Student alex = new Student(
                    "alex",
                    "alex@gmail.com",
                    LocalDate.of(2000, Month.JANUARY,10)

            );
            repository.saveAll(List.of(mariam,alex));
        };
    }
}
