package pl.kkski.watermeauth.service.impl;

import java.util.Set;
import javax.management.relation.RoleNotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pl.kkski.watermeauth.model.role.Role;
import pl.kkski.watermeauth.model.user.User;
import pl.kkski.watermeauth.model.user.UserCreation;
import pl.kkski.watermeauth.repository.RoleRepository;
import pl.kkski.watermeauth.repository.UserRepository;
import pl.kkski.watermeauth.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public AdminServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  @Override
  public void addCaretaker(UserCreation caretakerToAdd) throws RoleNotFoundException {
    Role role = roleRepository.findByName("CARETAKER").orElseThrow(RoleNotFoundException::new);
    userRepository.save(User.builder()
        .username(caretakerToAdd.getUsername())
        .password(caretakerToAdd.getPassword())
        .roles(Set.of(role))
        .build());
  }

  @Override
  public void addHeadCaretaker(UserCreation headCaretakerToAdd) throws RoleNotFoundException {
    Role role = roleRepository.findByName("HEAD_CARETAKER").orElseThrow(RoleNotFoundException::new);
    userRepository.save(User.builder()
        .username(headCaretakerToAdd.getUsername())
        .password(hashPassword(headCaretakerToAdd.getPassword()))
        .roles(Set.of(role))
        .build());
  }

  @Override
  public void addAdmin(UserCreation adminToAdd) throws RoleNotFoundException {
    Role role = roleRepository.findByName("ADMIN").orElseThrow(RoleNotFoundException::new);
    userRepository.save(User.builder()
        .username(adminToAdd.getUsername())
        .password(hashPassword(adminToAdd.getPassword()))
        .roles(Set.of(role))
        .build());
  }

  private static String hashPassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

}
