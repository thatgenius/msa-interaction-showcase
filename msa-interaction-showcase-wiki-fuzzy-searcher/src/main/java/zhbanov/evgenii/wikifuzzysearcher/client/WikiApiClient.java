package zhbanov.evgenii.wikifuzzysearcher.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import zhbanov.evgenii.exception.ExceptionHelper;
import zhbanov.evgenii.wikifuzzysearcher.config.properties.WikiFuzzySearcherProperties;
import zhbanov.evgenii.wikifuzzysearcher.model.dto.WikiSearchSuggestionDto;

@Component
public class WikiApiClient {

    private final WebClient WEB_CLIENT;
    private static final int ONE_PAGE_IS_ALL_WE_NEED_FOR_NOW = 1;

    public WikiApiClient(WikiFuzzySearcherProperties properties) {
        ExchangeStrategies strategies = ExchangeStrategies.builder().codecs(WikiApiClient::registerMapper).build();
        WEB_CLIENT = WebClient.builder().exchangeStrategies(strategies).baseUrl(properties.getWikipedia().getUrl()).build();
    }

    /**
     * Returns: wikipedia page URI based on keyword provided.
     */
    public WikiSearchSuggestionDto doOpenSearch(String keyword) {
        return WEB_CLIENT
                .get()
                .uri(String.format(
                        "/w/api.php?" +
                                "action=opensearch&" +
                                "search=%s&" +
                                "limit=%s&" +
                                "format=xml",
                        keyword, ONE_PAGE_IS_ALL_WE_NEED_FOR_NOW)
                )
                .accept(MediaType.TEXT_XML)
                .retrieve()
                .bodyToMono(WikiSearchSuggestionDto.class)
                .doOnError(ExceptionHelper::failWikiApi)
                .block();
    }

    private static void registerMapper(ClientCodecConfigurer configurer) {
        ObjectMapper mapper = new XmlMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(mapper, MediaType.TEXT_XML));
    }
}