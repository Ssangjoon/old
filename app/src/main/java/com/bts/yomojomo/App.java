/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.bts.yomojomo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args); // 스프링부트를 실행
  }
}
