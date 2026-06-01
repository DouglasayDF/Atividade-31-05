package com.curso.atividade_31_05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.curso.atividade_31_05")
public class Atividade3105Application {

    public static void main(String[] args) {
        SpringApplication.run(Atividade3105Application.class, args);
    }

}
