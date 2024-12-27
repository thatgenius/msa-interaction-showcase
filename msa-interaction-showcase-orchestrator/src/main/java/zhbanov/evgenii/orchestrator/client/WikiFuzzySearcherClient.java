package zhbanov.evgenii.orchestrator.client;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import zhbanov.evgenii.exception.ExceptionHelper;
import zhbanov.evgenii.model.dto.SearchSuggestionDto;
import zhbanov.evgenii.orchestrator.config.properties.OrchestratorProperties;

@Component
public class WikiFuzzySearcherClient {

    private final WebClient WEB_CLIENT;

    public WikiFuzzySearcherClient(OrchestratorProperties properties) {
        ExchangeStrategies strategies = ExchangeStrategies.builder().codecs(WikiFuzzySearcherClient::registerMapper).build();
        WEB_CLIENT = WebClient.builder().exchangeStrategies(strategies).baseUrl(properties.getWikiFuzzySearcher().getUrl()).build();
    }

    public SearchSuggestionDto retrievePageUri(String keyword) {
        return WEB_CLIENT
                .get()
                .uri("/page/" + keyword)
                .accept(MediaType.APPLICATION_XML)
                .retrieve()
                .bodyToMono(SearchSuggestionDto.class)
                .doOnError(ExceptionHelper::failWikiFuzzySearcher)
                .block();
    }

    private static void registerMapper(ClientCodecConfigurer configurer) {
        configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(new XmlMapper(), MediaType.APPLICATION_XML));
    }
}