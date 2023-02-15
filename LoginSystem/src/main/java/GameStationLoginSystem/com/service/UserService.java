package GameStationLoginSystem.com.service;

import GameStationLoginSystem.com.model.Role;
import GameStationLoginSystem.com.model.User;
import GameStationLoginSystem.com.repository.RoleRepository;
import GameStationLoginSystem.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

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
        Role roleCustomer=roleRepo.findByName("Customer");
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
}
