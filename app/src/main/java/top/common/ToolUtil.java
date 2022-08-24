package top.common;

import java.security.MessageDigest;
import lombok.SneakyThrows;

/** ToolUtil. */
public class ToolUtil {
  /**
   * encode password.
   *
   * @param password String
   * @return String
   */
  @SneakyThrows
  public static String encode(String password) {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(password.getBytes());
    byte[] digest = md.digest();
    StringBuilder hexString = new StringBuilder();

    for (byte b : digest) {
      hexString.append(Integer.toHexString(0xFF & b));
    }
    return hexString.toString();
  }
}
