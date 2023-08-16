package com.betrybe.agrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Application main class.
 */
@SpringBootApplication
@ComponentScan("com.betrybe.agrix")
@EntityScan("com.betrybe.agrix.model")
@EnableJpaRepositories("com.betrybe.agrix.interfaces")
public class AgrixApplication {

  public static void main(String[] args) {
    SpringApplication.run(AgrixApplication.class, args);
  }

}
