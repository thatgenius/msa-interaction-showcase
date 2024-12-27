package zhbanov.evgenii.keywordsupplier.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import zhbanov.evgenii.exception.ExceptionHelper;
import zhbanov.evgenii.keywordsupplier.config.properties.KeywordSupplierProperties;
import zhbanov.evgenii.model.dto.RandomWordsDto;

@Component
public class RandomWordApiClient {

    private final WebClient WEB_CLIENT;

    public RandomWordApiClient(KeywordSupplierProperties properties) {
        WEB_CLIENT = WebClient.create(properties.getRandomWordApi().getUrl());
    }

    /**
     * The method retrieves random words.
     * Returns: an array of random words.
     */
    public RandomWordsDto retrieve(int keywordNumber) {
        String[] randomWords = WEB_CLIENT
                .get()
                .uri("/api?words=" + keywordNumber)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String[].class)
                .doOnError(ExceptionHelper::failRandomWordApi)
                .block();
        return new RandomWordsDto(randomWords);
    }
}