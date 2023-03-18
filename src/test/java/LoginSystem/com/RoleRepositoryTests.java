package LoginSystem.com;


import com.gamestation.ecommerce.model.Role;
import com.gamestation.ecommerce.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
    RoleRepository RoleRepo;

    @Test
    public void testCreateRoles() {
        Role user = new Role("USER");
        Role admin = new Role("ADMIN");

        RoleRepo.saveAll(List.of(user, admin));

        List<Role> listRoles = RoleRepo.findAll();
        assertThat(listRoles.size()).isEqualTo(2);
    }
}
