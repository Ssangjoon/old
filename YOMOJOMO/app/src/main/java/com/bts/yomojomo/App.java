package com.bts.yomojomo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@EnableTransactionManagement
@RestController
@SpringBootApplication
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args); // 스프링부트를 실행
  }
}
