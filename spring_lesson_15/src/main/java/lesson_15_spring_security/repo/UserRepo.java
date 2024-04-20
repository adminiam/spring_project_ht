package lesson_15_spring_security.repo;
import lesson_15_spring_security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepo extends JpaRepository<User, Long>{
    User getUserByName(String name);
}
