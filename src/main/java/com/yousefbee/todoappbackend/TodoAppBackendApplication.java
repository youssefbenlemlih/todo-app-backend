package com.yousefbee.todoappbackend;

import com.yousefbee.todoappbackend.config.SecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan()
@Import({SecurityConfiguration.class})
public class TodoAppBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(TodoAppBackendApplication.class, args);
  }

}
