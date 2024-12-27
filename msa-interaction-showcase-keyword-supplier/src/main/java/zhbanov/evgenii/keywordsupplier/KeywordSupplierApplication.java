package zhbanov.evgenii.keywordsupplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import zhbanov.evgenii.keywordsupplier.config.properties.KeywordSupplierProperties;

@SpringBootApplication
@EnableConfigurationProperties(KeywordSupplierProperties.class)
public class KeywordSupplierApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeywordSupplierApplication.class, args);
    }
}