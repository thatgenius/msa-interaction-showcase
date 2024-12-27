package zhbanov.evgenii.wikifuzzysearcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import zhbanov.evgenii.wikifuzzysearcher.config.properties.WikiFuzzySearcherProperties;

@SpringBootApplication
@EnableConfigurationProperties(WikiFuzzySearcherProperties.class)
public class WikiFuzzySearcherApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(WikiFuzzySearcherApplication.class, args);
    }

    /**
     * Wiki-Fuzzy-Searcher servlet is required to provide XML from its API.
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_XML);
    }
}