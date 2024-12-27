package zhbanov.evgenii.orchestrator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhbanov.evgenii.model.dto.SearchSuggestionDto;
import zhbanov.evgenii.orchestrator.service.ComplexDataService;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ComplexDataController {

    private final ComplexDataService complexDataService;

    @GetMapping(value = "/random-wiki-page/")
    public ResponseEntity<SearchSuggestionDto> retrieveRandomWikipediaPageUri() {
        return ResponseEntity.ok(complexDataService.retrieveRandomWikipediaPageUri());
    }
}
