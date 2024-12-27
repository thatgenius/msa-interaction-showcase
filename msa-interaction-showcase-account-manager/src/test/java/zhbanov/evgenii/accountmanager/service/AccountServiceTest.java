package zhbanov.evgenii.accountmanager.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static zhbanov.evgenii.accountmanager.service.AccountService.maskToken;

public class AccountServiceTest {

    @Test
    public void testTokenMasking() {
        assertEquals(maskToken("19n45$78sg2345678l1234567891z57v"), "...z57v");
        assertNotEquals(maskToken("19n45$78sg2345678l1234567891z57v"), maskToken("1z257v$7s2345678l12345678919n45$"));
    }
}