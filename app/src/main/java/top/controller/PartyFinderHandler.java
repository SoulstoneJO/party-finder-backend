package top.controller;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.common.exception.AppException;
import top.constant.CommonConstant.ResultCode;

/** PartyFinderHandler. */
@RestControllerAdvice
public class PartyFinderHandler {

  @ExceptionHandler(AppException.class)
  public ResponseEntity<ResultResponse> handleAppException(AppException ex) {
    return ResponseEntity.ok(
        ResultResponse.builder().resultCode(ex.getCode().value).message(ex.getMessage()).build());
  }

  /**
   * handleUnExceptException.
   *
   * @param ex Exception
   * @return ResponseEntity
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResultResponse> handleUnExceptException(Exception ex) {
    return ResponseEntity.ok(
        ResultResponse.builder()
            .resultCode(ResultCode.NOEXCEPT_ERROR.value)
            .message("UnExceptException")
            .build());
  }

  @Data
  @Builder
  private static class ResultResponse {
    private String resultCode;
    private String message;
  }
}
