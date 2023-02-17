package LoginSystem.com;

import static org.assertj.core.api.Assertions.assertThat;

import LoginSystem.com.model.User;
import LoginSystem.com.model.Role;
import LoginSystem.com.repository.RoleRepository;
import LoginSystem.com.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    // test methods go below
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("Raheeb");
        user.setEmail("raheeb@gmail.com");
        user.setPassword("raheeb2023");
        User savedUser = userRepo.save(user);
        User existUser = entityManager.find(User.class, savedUser.getId());
        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }

    @Test
    public void testAddRoleToNewUser() {
        Role roleAdmin = roleRepo.findByName("Admin");
        User user = new User();
        user.setUsername("Raheeb");
        user.setEmail("raheeb2023@gmail.com");
        user.setPassword("123456");
        user.setEnabled(true);
        user.addRole(roleAdmin);
        User savedUser = userRepo.save(user);
        assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }



}
