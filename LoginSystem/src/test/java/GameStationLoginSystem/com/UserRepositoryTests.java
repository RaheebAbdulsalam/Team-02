package com.RegisterationExample;

import static org.assertj.core.api.Assertions.assertThat;

import com.RegisterationExample.model.Role;
import com.RegisterationExample.model.User;
import com.RegisterationExample.repository.RoleRepository;
import com.RegisterationExample.repository.UserRepository;
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
        user.setEmail("raheeb@gmail.com");
        user.setPassword("raheeb2023");
        user.setFirstName("Raheeb");
        user.setLastName("Abdulsalam");

        User savedUser = userRepo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }

    @Test
    public void testFindUserByEmail(){
        String email="user@gmail.com";
        User user=userRepo.findByEmail(email);

        assertThat(user).isNotNull();
    }

    @Test
    public void testAddRoleToNewUser() {
        Role roleAdmin = roleRepo.findByName("Admin");

        User user = new User();
        user.setEmail("raheeb2023@gmail.com");
        user.setPassword("123456");
        user.setFirstName("Raheeb");
        user.setLastName("Abdusalam");
        user.addRole(roleAdmin);

        User savedUser = userRepo.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }



}
