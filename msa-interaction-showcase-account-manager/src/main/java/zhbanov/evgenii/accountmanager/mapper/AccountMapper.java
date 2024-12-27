package zhbanov.evgenii.accountmanager.mapper;

import org.springframework.stereotype.Component;
import zhbanov.evgenii.model.dto.AccountDto;
import zhbanov.evgenii.accountmanager.model.entity.AccountEntity;

@Component
public class AccountMapper {

    public AccountDto mapEntityToDto(AccountEntity accountEntity) {
        return new AccountDto(accountEntity.getName());
    }
}
