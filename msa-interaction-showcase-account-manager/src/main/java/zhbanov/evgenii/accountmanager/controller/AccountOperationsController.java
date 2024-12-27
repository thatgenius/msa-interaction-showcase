package zhbanov.evgenii.accountmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zhbanov.evgenii.accountmanager.service.AccountService;
import zhbanov.evgenii.model.dto.AccountCredentialsDto;
import zhbanov.evgenii.model.dto.AccountDto;
import zhbanov.evgenii.model.dto.TokenDto;
import static zhbanov.evgenii.model.Constants.TOKEN_HEADER_KEY;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AccountOperationsController {

    private final AccountService ACCOUNT_SERVICE;

    @PostMapping("/token")
    public ResponseEntity<TokenDto> retrieveOrGenerateToken(@RequestBody AccountCredentialsDto accCredentialsDto) {
        TokenDto tokenDto = ACCOUNT_SERVICE.retrieveOrGenerateToken(
                accCredentialsDto.getLogin(),
                accCredentialsDto.getPassword()
        );
        return ResponseEntity.ok(tokenDto);
    }

    @GetMapping("/token/account-data")
    public ResponseEntity<AccountDto> retrieveDataByToken(@RequestHeader(TOKEN_HEADER_KEY) String token) {
        AccountDto accountDto = ACCOUNT_SERVICE.retrieveDataByToken(token);
        return ResponseEntity.ok(accountDto);
    }
}
