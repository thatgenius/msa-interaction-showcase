package zhbanov.evgenii.keywordsupplier.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import zhbanov.evgenii.exception.ExceptionHelper;
import zhbanov.evgenii.keywordsupplier.config.properties.KeywordSupplierProperties;
import zhbanov.evgenii.model.dto.AccountDto;

import static zhbanov.evgenii.model.Constants.TOKEN_HEADER_KEY;

@Component
public class AccountManagerClient {

    private final WebClient WEB_CLIENT;

    public AccountManagerClient(KeywordSupplierProperties properties) {
        WEB_CLIENT = WebClient.create(properties.getAccountManager().getUrl());
    }

    public AccountDto retrieveDataByToken(String token) {
        return WEB_CLIENT
                .get()
                .uri("/token/account-data")
                .header(TOKEN_HEADER_KEY, token)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AccountDto.class)
                .doOnError(ExceptionHelper::failAccountManager)
                .block();
    }
}