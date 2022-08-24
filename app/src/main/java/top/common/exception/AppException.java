package top.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.constant.CommonConstant.ResultCode;

/** AppException. */
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class AppException extends RuntimeException {
  private final ResultCode code;
  private final String message;
}
