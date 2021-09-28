package com.cor.demo.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * jpa-lucene-spring-demo
 */
@EnableTransactionManagement
@SpringBootApplication(scanBasePackageClasses = Application.class)
public class Application {

    /**
     * @param args application arguments
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
    }
}
