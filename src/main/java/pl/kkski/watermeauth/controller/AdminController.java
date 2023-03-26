package pl.kkski.watermeauth.controller;

import javax.management.relation.RoleNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kkski.watermeauth.model.user.UserCreation;
import pl.kkski.watermeauth.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
  private final AdminService adminService;

  public AdminController(
      AdminService adminService) {
    this.adminService = adminService;
  }

  @PostMapping("/caretakers")
  public void addCaretaker(@RequestBody UserCreation caretaker) throws RoleNotFoundException {
    adminService.addCaretaker(caretaker);
  }

  @PostMapping("/head-caretakers")
  public void addHeadCaretaker(@RequestBody UserCreation caretaker) throws RoleNotFoundException {
    adminService.addHeadCaretaker(caretaker);
  }

  @PostMapping("/admins")
  public void addAdmin(@RequestBody UserCreation admin) throws RoleNotFoundException {
    adminService.addAdmin(admin);
  }


}
