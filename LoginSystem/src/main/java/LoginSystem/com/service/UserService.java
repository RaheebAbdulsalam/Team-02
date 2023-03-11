package LoginSystem.com.service;

import LoginSystem.com.model.Role;
import LoginSystem.com.model.User;
import LoginSystem.com.repository.RoleRepository;
import LoginSystem.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;

    public void saveWithDefaultRole(User user){

        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedPassword= encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        //Save a new user as a customer
        Role roleCustomer=roleRepo.findByName("USER");
        user.addRole(roleCustomer);
        userRepo.save(user);
    }

    public List<User> listAll(){
        return userRepo.findAll();
    }

    public User get(Long id) {
        return userRepo.findById(id).get();
    }

    public List<Role> getRoles() {
        return roleRepo.findAll();
    }

    public void save(User user) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedPassword= encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
    }


    public boolean emailExists(String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepo.findByEmail(email));
        return userOptional.isPresent();
    }


}
