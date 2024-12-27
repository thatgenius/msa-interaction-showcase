package zhbanov.evgenii.wikifuzzysearcher.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("msa.interaction.showcase.wiki-fuzzy-searcher")
public class WikiFuzzySearcherProperties {

    private WikipediaProperties wikipedia;

    @Getter
    @Setter
    public static class WikipediaProperties {
        private String url;
    }
}
