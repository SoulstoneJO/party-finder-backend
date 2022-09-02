package top.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import top.constant.CommonConstant.ResultCode;

/** AppException. */
@Getter
@AllArgsConstructor
public class AppException extends RuntimeException {
  private final String code;
}
