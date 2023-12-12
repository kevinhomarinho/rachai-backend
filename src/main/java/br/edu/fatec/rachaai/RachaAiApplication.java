package br.edu.fatec.rachaai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.edu.fatec.rachaai")
public class RachaAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RachaAiApplication.class, args);
    }

}
