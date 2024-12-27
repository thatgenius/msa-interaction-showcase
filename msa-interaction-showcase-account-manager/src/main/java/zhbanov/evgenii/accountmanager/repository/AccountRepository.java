package zhbanov.evgenii.accountmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zhbanov.evgenii.accountmanager.model.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer>, JpaSpecificationExecutor<AccountEntity> {

    AccountEntity findByLogin(String login);
    AccountEntity findByToken(String token);

    @Modifying
    @Query("update AccountEntity a set a.token = :token where a.id = :id")
    Integer updateToken(@Param("id") int id, @Param("token") String token);
}