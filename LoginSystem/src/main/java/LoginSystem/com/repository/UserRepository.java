package LoginSystem.com.repository;

import LoginSystem.com.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
	@Query("SELECT u FROM User u WHERE u.username = :username")
	User getUserByUsername(@Param("username") String username);
}
