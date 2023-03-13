package LoginSystem.com;

import static org.assertj.core.api.Assertions.assertThat;

import com.gamestation.ecommerce.model.Role;
import com.gamestation.ecommerce.model.User;
import com.gamestation.ecommerce.repository.RoleRepository;
import com.gamestation.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;


    private UserRepository userRepo;


    private RoleRepository roleRepo;

    // test methods go below
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setFirstName("Raheeb");
        user.setLastName("Abdulsalam");
        user.setEmail("raheeb@gmail.com");
        user.setPassword("123456");
        User savedUser = userRepo.save(user);
        User existUser = entityManager.find(User.class, savedUser.getId());
        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }

    @Test
    public void testAddRoleToNewUser() {
        Role roleAdmin = roleRepo.findByName("Admin");
        User user = new User();
        user.setFirstName("Raheeb");
        user.setLastName("Abdusalam");
        user.setEmail("raheeb2023@gmail.com");
        user.setPassword("123456");
        user.addRole(roleAdmin);
        User savedUser = userRepo.save(user);
        assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }



}
