package zhbanov.evgenii.wikifuzzysearcher.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhbanov.evgenii.model.dto.SearchSuggestionDto;
import zhbanov.evgenii.wikifuzzysearcher.service.WikiFuzzySearcherService;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class WikiFuzzySearcherController {

    private final WikiFuzzySearcherService WIKI_FUZZY_SEARCHER_SERVICE;

    @GetMapping("/page/{keyword}")
    public ResponseEntity<SearchSuggestionDto> findPage(@PathVariable(name="keyword") String keyword) {
        SearchSuggestionDto searchSuggestionDto = WIKI_FUZZY_SEARCHER_SERVICE.findPage(keyword);
        return ResponseEntity.ok(searchSuggestionDto);
    }
}
