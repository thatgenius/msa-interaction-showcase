package zhbanov.evgenii.accountmanager.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account", schema = "account_manager_schema")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    /**
     * Technical aspect: unique constraint here in Java in fact
     * only is to tell the software development engineer these field values are unique.
     * The actual uniqueness checks happens in the database.
     * Read more here:
     * <a href=https://stackoverflow.com/questions/30460596/jpa-column-unique-true-what-is-really-point-of-having-unique-attribute>stackoverflow link</a>
     */
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "token", unique = true)
    private String token;
}