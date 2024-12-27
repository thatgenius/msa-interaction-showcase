package zhbanov.evgenii.keywordsupplier.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zhbanov.evgenii.keywordsupplier.client.RandomWordApiClient;
import zhbanov.evgenii.model.IntegrationSystem;
import zhbanov.evgenii.model.dto.RandomWordsDto;

@Service
@RequiredArgsConstructor
public class KeywordSupplierService {

    private final RandomWordApiClient randomWordApiClient;
    private static final Logger LOG = LoggerFactory.getLogger(KeywordSupplierService.class);

    /**
     * Performs authorization by receiving user data from Account-Manager by provided token
     * and then validates the data.
     * If not successful IntegrationClientException is thrown.
     */
    public RandomWordsDto retrieve(int keywordNumber) {
        RandomWordsDto randomWordsDto = randomWordApiClient.retrieve(keywordNumber);
        LOG.info("Retrieved random words from \"{}\": \"{}\"", IntegrationSystem.RANDOM_WORD_API, randomWordsDto);
        return randomWordsDto;
    }
}