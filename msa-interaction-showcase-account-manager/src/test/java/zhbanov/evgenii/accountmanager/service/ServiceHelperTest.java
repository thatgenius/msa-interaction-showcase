package zhbanov.evgenii.accountmanager.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static zhbanov.evgenii.accountmanager.service.ServiceHelper.generateToken;

public class ServiceHelperTest {

    @Test
    public void testTokenGeneration() {
        assertNotEquals(generateToken(), generateToken());
        assertEquals(32, generateToken().length());
    }
}