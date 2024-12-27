package zhbanov.evgenii.keywordsupplier.service;

import zhbanov.evgenii.model.dto.AccountDto;
import static zhbanov.evgenii.keywordsupplier.service.ServiceHelper.isAccountDtoValid;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ServiceHelperTest {

    @Test
    public void testIsAccountDtoValid() {
        assertEquals(true, isAccountDtoValid(new AccountDto("Ivan")));
        assertEquals(false, isAccountDtoValid(new AccountDto(" ")));
        assertEquals(false, isAccountDtoValid(new AccountDto("")));
        assertEquals(false, isAccountDtoValid(new AccountDto(null)));
        assertEquals(false, isAccountDtoValid(null));
    }

}