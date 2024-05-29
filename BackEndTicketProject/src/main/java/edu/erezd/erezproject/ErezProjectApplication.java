package edu.erezd.erezproject;

import edu.erezd.erezproject.config.RSAKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackageClasses = {RSAKeyProperties.class})
public class ErezProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErezProjectApplication.class, args);
    }

}
