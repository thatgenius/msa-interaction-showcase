package zhbanov.evgenii.keywordsupplier.service;

import zhbanov.evgenii.model.dto.AccountDto;

import java.util.Objects;

public class ServiceHelper {

    public static boolean isAccountDtoValid(AccountDto accountDto) {
        return !Objects.isNull(accountDto) && !Objects.isNull(accountDto.getName()) &&
                !accountDto.getName().trim().isEmpty();
    }
}