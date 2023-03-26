package pl.kkski.watermeauth.service;

import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kkski.watermeauth.model.User;
import pl.kkski.watermeauth.model.CustomUserDetails;
import pl.kkski.watermeauth.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUsername(username);
    return user.map(CustomUserDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
  }
}
