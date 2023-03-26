package pl.kkski.watermeauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TestController {

  @GetMapping("/caretaker")
  public String caretaker() {
    return "Hello Caretaker!";
  }

  @GetMapping("/headcaretaker")
  public String headCaretaker() {
    return "Hello Head Caretaker!";
  }

  @GetMapping("/admin")
  public String admin() {
    return "Hello Admin!";
  }
}