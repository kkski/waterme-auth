package pl.kkski.watermeauth.model;

public class RoleConstants {
  public static final String ADMIN = "ADMIN";
  public static final String CARETAKER = "CARETAKER";
  public static final String HEAD_CARETAKER = "HEAD_CARETAKER";

  private RoleConstants() {
    throw new IllegalStateException("Utility class");
  }
}
