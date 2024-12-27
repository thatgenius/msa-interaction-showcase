package zhbanov.evgenii.orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import zhbanov.evgenii.amclient.config.properties.AmClientProperties;
import zhbanov.evgenii.orchestrator.config.properties.OrchestratorProperties;

@SpringBootApplication
@EnableConfigurationProperties({OrchestratorProperties.class, AmClientProperties.class})
public class OrchestratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrchestratorApplication.class, args);
    }
}