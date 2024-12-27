package zhbanov.evgenii.amclient.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Getter
@Setter
@ConfigurationProperties("msa.integration.showcase.am-client")
public class AmClientProperties {

    private AccountManagerProperties accountManager;
    private String amLogin;
    private String amPassword;

    @Getter
    @Setter
    @ConstructorBinding
    public static class AccountManagerProperties {

        /**
         * Адрес Account Manager'а
         */
        private String url;

        public AccountManagerProperties(@DefaultValue("http://127.0.0.1:8081") String url) {
            this.url = url;
        }
    }
}
