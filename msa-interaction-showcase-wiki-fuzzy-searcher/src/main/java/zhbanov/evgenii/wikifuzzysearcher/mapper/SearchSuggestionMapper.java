package zhbanov.evgenii.wikifuzzysearcher.mapper;

import org.springframework.stereotype.Component;
import zhbanov.evgenii.model.dto.SearchSuggestionDto;
import zhbanov.evgenii.wikifuzzysearcher.model.dto.WikiSearchSuggestionDto;
import java.util.Optional;

@Component
public class SearchSuggestionMapper {

    public SearchSuggestionDto mapWikiDtoToDto(WikiSearchSuggestionDto wikiSearchSuggestionDto, String keyword) {
        String wikiPageUrl = Optional.ofNullable(wikiSearchSuggestionDto)
                .map(WikiSearchSuggestionDto::getSection)
                .map(WikiSearchSuggestionDto.Section::getItem)
                .map(WikiSearchSuggestionDto.Item::getUrl)
                .orElse("Nothing found. Try again");
        return new SearchSuggestionDto(keyword, wikiPageUrl);
    }
}
