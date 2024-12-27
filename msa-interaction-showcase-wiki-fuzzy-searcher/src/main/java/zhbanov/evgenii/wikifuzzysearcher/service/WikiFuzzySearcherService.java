package zhbanov.evgenii.wikifuzzysearcher.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zhbanov.evgenii.model.IntegrationSystem;
import zhbanov.evgenii.model.dto.SearchSuggestionDto;
import zhbanov.evgenii.wikifuzzysearcher.client.WikiApiClient;
import zhbanov.evgenii.wikifuzzysearcher.mapper.SearchSuggestionMapper;
import zhbanov.evgenii.wikifuzzysearcher.model.dto.WikiSearchSuggestionDto;

@Service
@RequiredArgsConstructor
public class WikiFuzzySearcherService {

    private final WikiApiClient WIKI_API_CLIENT;
    private final SearchSuggestionMapper searchSuggestionMapper;
    private static final Logger LOG = LoggerFactory.getLogger(WikiFuzzySearcherService.class);

    public SearchSuggestionDto findPage(String keyword) {
        WikiSearchSuggestionDto wikiSearchSuggestionDto = WIKI_API_CLIENT.doOpenSearch(keyword);
        SearchSuggestionDto searchSuggestionDto = searchSuggestionMapper.mapWikiDtoToDto(wikiSearchSuggestionDto, keyword);
        LOG.info("Retrieved wikipedia page URI from \"{}\" for keyword \"{}\": \"{}\"",
                IntegrationSystem.WIKIPEDIA_API, keyword, searchSuggestionDto.getSuggestedWikiPageUri());
        return searchSuggestionDto;
    }
}