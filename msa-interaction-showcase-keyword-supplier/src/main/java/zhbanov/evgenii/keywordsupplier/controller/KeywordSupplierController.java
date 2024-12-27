package zhbanov.evgenii.keywordsupplier.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zhbanov.evgenii.keywordsupplier.client.RandomWordApiClient;
import zhbanov.evgenii.keywordsupplier.service.AuthorizationService;
import zhbanov.evgenii.keywordsupplier.service.KeywordSupplierService;
import zhbanov.evgenii.model.dto.RandomWordsDto;
import static zhbanov.evgenii.model.Constants.TOKEN_HEADER_KEY;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class KeywordSupplierController {

    private final AuthorizationService authorizationService;
    private final KeywordSupplierService keywordSupplierService;

    @GetMapping("/keywords/{keywordNumber}")
    public ResponseEntity<RandomWordsDto> retrieve(@PathVariable(name="keywordNumber") int keywordNumber,
                                              @RequestHeader(TOKEN_HEADER_KEY) String token) {
        authorizationService.authorize(token);
        RandomWordsDto randomWordsDto = keywordSupplierService.retrieve(keywordNumber);
        return ResponseEntity.ok(randomWordsDto);
    }
}
