package top.constant;

import lombok.AllArgsConstructor;

/** CommonConstant. */
public class CommonConstant {

  /** ResultCode. */
  @AllArgsConstructor
  public enum ResultCode {
    SUCCESS("000"),
    PARAMETER_ERROR("001"),
    Auth_ERROR("002"),
    NOEXCEPT_ERROR("999");

    public final String value;
  }
}
