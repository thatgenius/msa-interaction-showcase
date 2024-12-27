package zhbanov.evgenii.accountmanager.service;

import java.security.SecureRandom;
import java.util.Base64;

public class ServiceHelper {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final Base64.Encoder BASE_64_ENCODER = Base64.getUrlEncoder();

    /**
     * Массив из 24 случайных чисел типа byte
     * кодируется по стандарту Base64.
     * На выходе строка из 32 символов.
     */
    public static String generateToken() {
        byte[] randomBytes = new byte[24];
        SECURE_RANDOM.nextBytes(randomBytes);
        return BASE_64_ENCODER.encodeToString(randomBytes);
    }
}