package zhbanov.evgenii.exception;

import zhbanov.evgenii.model.IntegrationSystem;

public interface ExceptionHelper {

    static void failWikiFuzzySearcher(Throwable t) {
        throw new IntegrationClientException(IntegrationSystem.WIKI_FUZZY_SEARCHER, t);
    }

    static void failWikiApi(Throwable t) {
        throw new IntegrationClientException(IntegrationSystem.WIKIPEDIA_API, t);
    }

    static void failAccountManager(Throwable t) {
        throw new IntegrationClientException(IntegrationSystem.ACCOUNT_MANAGER, t);
    }

    static void failRandomWordApi(Throwable t) {
        throw new IntegrationClientException(IntegrationSystem.RANDOM_WORD_API, t);
    }

    static void failKeywordSupplier(Throwable t) {
        throw new IntegrationClientException(IntegrationSystem.KEYWORD_SUPPLIER, t);
    }
}