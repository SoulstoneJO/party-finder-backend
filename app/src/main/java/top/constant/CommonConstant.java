package top.constant;

import lombok.AllArgsConstructor;

/** CommonConstant. */
public class CommonConstant {

  /** ResultCode. */
  @AllArgsConstructor
  public enum ResultCode {
    SUCCESS("000"),
    PARAMETER_ERROR("001"),
    AUTH_ERROR("002"),
    REDIS_CODE_NOT_EXIST("003"),
    VERIFICATION_CODE_NOT_EQUAL("004"),
    USER_EXIST("100"),
    NOEXCEPT_ERROR("999");

    public final String value;
  }
}
