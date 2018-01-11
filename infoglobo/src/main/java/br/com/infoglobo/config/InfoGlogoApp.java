package br.com.infoglobo.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@PropertySources({
        @PropertySource("classpath:application.yml")})
@ComponentScan(basePackages = "br.com.infoglobo")
@EnableConfigurationProperties({ApplicationProperties.class})
@EnableScheduling
public class InfoGlogoApp {


    public static void main(String[] args) {
        SpringApplication.run(InfoGlogoApp.class, args);
    }
}
