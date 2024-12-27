package zhbanov.evgenii.accountmanager.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import zhbanov.evgenii.accountmanager.exception.AccountManagerException;
import zhbanov.evgenii.accountmanager.mapper.AccountMapper;
import zhbanov.evgenii.accountmanager.model.entity.AccountEntity;
import zhbanov.evgenii.accountmanager.repository.AccountRepository;
import zhbanov.evgenii.model.dto.AccountDto;
import zhbanov.evgenii.model.dto.TokenDto;
import java.util.Objects;
import static zhbanov.evgenii.accountmanager.service.ServiceHelper.generateToken;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository ACCOUNT_REPOSITORY;
    private final TransactionTemplate TRANSACTION_TEMPLATE;
    private final AccountMapper ACCOUNT_MAPPER;
    private static final Logger LOG = LoggerFactory.getLogger(AccountService.class);

    public TokenDto retrieveOrGenerateToken(String login, String password) {
        AccountEntity accountEntity = authenticate(login, password);
        String token = accountEntity.getToken();
        if (token == null) {
            token = generateToken();
            accountEntity.setToken(token);
            LOG.debug(
                    "Updating  \"{}\" with login \"{}\" in PostgreSQL by id: \"{}\"",
                    AccountEntity.class.getName(), login, accountEntity.getId()
            );
            Integer numberOfRowsAffected = TRANSACTION_TEMPLATE.execute(status -> ACCOUNT_REPOSITORY.updateToken(
                    accountEntity.getId(),
                    accountEntity.getToken()
            ));
            if (Objects.isNull(numberOfRowsAffected) || numberOfRowsAffected.equals(0)) {
                throw new AccountManagerException("Couldn't find account with id: " + accountEntity.getId());
            }
            LOG.info("Updated account to store generated token for login: \"{}\"", login);
        }
        return new TokenDto(token);
    }

    public AccountDto retrieveDataByToken(String token) {
        AccountEntity accountEntity = findByToken(token);
        AccountDto accountDto = ACCOUNT_MAPPER.mapEntityToDto(accountEntity);
        LOG.info("Retrieved account data for third party by token: \"{}\"", maskToken(token));
        return accountDto;
    }

    private AccountEntity authenticate(String login, String password) {
        LOG.debug("Retrieving \"{}\" from PostgreSQL by login: \"{}\"", AccountEntity.class.getName(), login);
        AccountEntity accountEntity = TRANSACTION_TEMPLATE.execute(status -> ACCOUNT_REPOSITORY.findByLogin(login));
        LOG.info("Found account by login: \"{}\"", login);
        if (Objects.isNull(accountEntity) || !Objects.equals(accountEntity.getPassword(), password)) {
            LOG.warn("Wrong credentials provided with login: \"{}\"", login);
            throw new AccountManagerException("Wrong login or password.");
        }
        LOG.info("Authentication by login \"{}\" is successful.", login);
        return accountEntity;
    }

    private AccountEntity findByToken(String token) {
        LOG.debug(
                "Retrieving \"{}\" from PostgreSQL by token: \"{}\"",
                AccountEntity.class.getName(), maskToken(token)
        );
        AccountEntity accountEntity = TRANSACTION_TEMPLATE.execute(status -> ACCOUNT_REPOSITORY.findByToken(token));
        if (Objects.isNull(accountEntity)) {
            throw new AccountManagerException("Couldn't find account by token: " + maskToken(token));
        } else {
            LOG.info("Found account by token: \"{}\"", maskToken(token));
            return accountEntity;
        }
    }

    public static String maskToken(String token) {
        return  "..." + token.substring(token.length() - 4);
    }
}