package pl.kkski.watermeauth.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.kkski.watermeauth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);
}