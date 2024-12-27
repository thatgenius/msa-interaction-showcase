package zhbanov.evgenii.keywordsupplier.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zhbanov.evgenii.keywordsupplier.client.AccountManagerClient;
import zhbanov.evgenii.exception.IntegrationClientException;
import zhbanov.evgenii.model.IntegrationSystem;
import zhbanov.evgenii.model.dto.AccountDto;

import static zhbanov.evgenii.keywordsupplier.service.ServiceHelper.isAccountDtoValid;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final AccountManagerClient accountManagerClient;
    private static final Logger LOG = LoggerFactory.getLogger(AuthorizationService.class);

    /**
     * Performs authorization by receiving user data from Account-Manager by provided token
     * and then validates the data.
     * If not successful IntegrationClientException is thrown.
     */
    public void authorize(String token) {
        AccountDto accountDto = accountManagerClient.retrieveDataByToken(token);
        if (!isAccountDtoValid(accountDto)) {
            throw new IntegrationClientException(
                    IntegrationSystem.ACCOUNT_MANAGER,
                    "Authorization by provided token failed: " +
                            "received account data from Account-Manager is invalid."
            );
        }
        LOG.info("Authorization by token \"{}\" is successful.", maskToken(token));
    }

    private static String maskToken(String token) {
        return  "..." + token.substring(token.length() - 4);
    }
}