package zhbanov.evgenii.amclient;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import zhbanov.evgenii.exception.ExceptionHelper;
import zhbanov.evgenii.model.dto.AccountCredentialsDto;
import zhbanov.evgenii.model.dto.TokenDto;

// TODO (Backlog задача): Полученный токен должен иметь время жизни, в
//  течение которого мы храним его в кэше библиотеки Account-Manager client'а.
//  После того как время действия токена истекает (it gets stale),
//  мы идем за ним снова в Account-Manager. Пока ходим каждый раз.
//  Кэш реализовать на основе PostgreSQL.
public class TokenClient {

    private final WebClient WEB_CLIENT = WebClient.create();

    /**
     * Retrieves a token from Account-Manager by the provided credentials.
     */
    public TokenDto retrieve(String accountManagerUrl, String login, String password) {
        return WEB_CLIENT
                .post()
                .uri(accountManagerUrl + "/token")
                .bodyValue(new AccountCredentialsDto(login, password))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(TokenDto.class)
                .doOnError(ExceptionHelper::failAccountManager)
                .block();
    }
}