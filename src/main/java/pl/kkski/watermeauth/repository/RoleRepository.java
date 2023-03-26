package pl.kkski.watermeauth.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.kkski.watermeauth.model.role.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(String name);
}
