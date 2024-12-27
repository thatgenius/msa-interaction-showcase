package zhbanov.evgenii.orchestrator.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import zhbanov.evgenii.exception.ExceptionHelper;
import zhbanov.evgenii.model.dto.RandomWordsDto;
import zhbanov.evgenii.model.dto.TokenDto;
import zhbanov.evgenii.orchestrator.config.properties.OrchestratorProperties;
import static zhbanov.evgenii.model.Constants.TOKEN_HEADER_KEY;

@Component
public class KeywordSupplierClient {

    private final WebClient WEB_CLIENT;
    private static final int ONE_KEYWORD_IS_ALL_WE_NEED_FOR_NOW = 1;

    public KeywordSupplierClient(OrchestratorProperties properties) {
        WEB_CLIENT = WebClient.create(properties.getKeywordSupplier().getUrl());
    }

    /**
     * Returns: one random word.
     */
    public RandomWordsDto retrieve(TokenDto tokenDto) {
        return WEB_CLIENT
                .get()
                .uri("/keywords/" + ONE_KEYWORD_IS_ALL_WE_NEED_FOR_NOW)
                .header(TOKEN_HEADER_KEY, tokenDto.getValue())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(RandomWordsDto.class)
                .doOnError(ExceptionHelper::failKeywordSupplier)
                .block();
    }
}