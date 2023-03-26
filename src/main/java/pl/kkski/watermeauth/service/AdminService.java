package pl.kkski.watermeauth.service;

import javax.management.relation.RoleNotFoundException;
import org.springframework.stereotype.Service;
import pl.kkski.watermeauth.model.user.UserCreation;

@Service
public interface AdminService {
  void addCaretaker(UserCreation caretakerToAdd) throws RoleNotFoundException;
  void addHeadCaretaker(UserCreation headCaretakerToAdd) throws RoleNotFoundException;
  void addAdmin(UserCreation adminToAdd) throws RoleNotFoundException;

}
