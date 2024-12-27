package zhbanov.evgenii.orchestrator.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("msa.integration.showcase.orchestrator")
public class OrchestratorProperties {

    private KeywordSupplierProperties keywordSupplier;
    private WikiFuzzySearcherProperties wikiFuzzySearcher;

    @Getter
    @Setter
    public static class KeywordSupplierProperties {
        private String url;
    }

    @Getter
    @Setter
    public static class WikiFuzzySearcherProperties {
        private String url;
    }
}
