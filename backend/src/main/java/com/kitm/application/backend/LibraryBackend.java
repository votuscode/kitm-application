package com.kitm.application.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.kitm.application.api", "com.kitm.application.backend"})
public class LibraryBackend {

  public static void main(String[] args) {
    SpringApplication.run(LibraryBackend.class, args);
  }
}
