package zhbanov.evgenii.orchestrator.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zhbanov.evgenii.amclient.TokenClient;
import zhbanov.evgenii.amclient.config.properties.AmClientProperties;
import zhbanov.evgenii.exception.IntegrationClientException;
import zhbanov.evgenii.model.IntegrationSystem;
import zhbanov.evgenii.model.dto.SearchSuggestionDto;
import zhbanov.evgenii.model.dto.TokenDto;
import zhbanov.evgenii.orchestrator.client.KeywordSupplierClient;
import zhbanov.evgenii.orchestrator.client.WikiFuzzySearcherClient;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComplexDataService {

    private final TokenClient TOKEN_CLIENT = new TokenClient();
    private final AmClientProperties PROPERTIES;
    private final KeywordSupplierClient KEYWORD_SUPPLIER_CLIENT;
    private final WikiFuzzySearcherClient WIKI_FUZZY_SEARCHER_CLIENT;
    private static final Logger LOG = LoggerFactory.getLogger(ComplexDataService.class);

    /**
     * Returns: a random wikipedia page URI
     * and a random keyword it was found by.
     */
    public SearchSuggestionDto retrieveRandomWikipediaPageUri() {
        TokenDto tokenDto = TOKEN_CLIENT.retrieve(
                PROPERTIES.getAccountManager().getUrl(),
                PROPERTIES.getAmLogin(),
                PROPERTIES.getAmPassword()
        );
        LOG.info("Retrieved token from \"{}\" for login: \"{}\"", IntegrationSystem.ACCOUNT_MANAGER, PROPERTIES.getAmLogin());

        String keyword = retrieveKeyword(tokenDto);
        LOG.info("Retrieved keyword from \"{}\": \"{}\"", IntegrationSystem.KEYWORD_SUPPLIER, keyword);

        SearchSuggestionDto searchSuggestionDto = WIKI_FUZZY_SEARCHER_CLIENT.retrievePageUri(keyword);
        LOG.info("Retrieved wikipedia page URI from \"{}\" for keyword \"{}\": \"{}\"",
                IntegrationSystem.WIKI_FUZZY_SEARCHER,
                searchSuggestionDto.getRandomKeyword(),
                searchSuggestionDto.getSuggestedWikiPageUri());
        return searchSuggestionDto;
    }

    private String retrieveKeyword(TokenDto tokenDto) {
        return Optional.ofNullable(KEYWORD_SUPPLIER_CLIENT.retrieve(tokenDto))
                .map(keyWords -> keyWords.getValues()[0])
                .orElseThrow(() -> new IntegrationClientException(
                        IntegrationSystem.KEYWORD_SUPPLIER,
                        "Expected 1 random word, but received none.")
                );
    }
}