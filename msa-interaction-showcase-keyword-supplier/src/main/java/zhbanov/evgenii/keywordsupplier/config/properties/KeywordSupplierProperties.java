package zhbanov.evgenii.keywordsupplier.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("msa.interaction.showcase.keyword-supplier")
public class KeywordSupplierProperties {
    private RandomWordApiProperties randomWordApi;
    private AccountManagerProperties accountManager;

    @Getter
    @Setter
    public static class RandomWordApiProperties {
        private String url;
    }

    @Getter
    @Setter
    public static class AccountManagerProperties {
        private String url;
    }
}
