package zhbanov.evgenii.exception;

import zhbanov.evgenii.model.IntegrationSystem;

public class IntegrationClientException extends RuntimeException {

    private static final String MESSAGE = "Error occurred while integrating with %s : %s";

    public IntegrationClientException(IntegrationSystem system, Throwable cause) {
        super(String.format(MESSAGE, system, cause.getMessage()));
    }

    public IntegrationClientException(IntegrationSystem system, String message) {
        super(String.format(MESSAGE, system, message));
    }
}