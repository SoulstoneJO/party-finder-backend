package top.service;

import generated.model.SignUpRequest;
import generated.model.SignUpResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.common.JwtManager;
import top.common.ToolUtil;
import top.common.ValidateCodeManager;
import top.common.exception.AppException;
import top.constant.CommonConstant.ResultCode;
import top.repository.entity.generated.Consumer;
import top.repository.mapper.generated.ConsumerMapper;

/** SignUpService. */
@Service
public class SignUpService {
  @Autowired ConsumerMapper consumerMapper;
  @Autowired JwtManager jwtManager;
  @Autowired ValidateCodeManager validateCodeManager;

  /**
   * doSignUp.
   *
   * @param signUpRequest SignUpRequest
   * @return SignUpResponse
   */
  public SignUpResponse doSignUp(@Valid SignUpRequest signUpRequest) {
    // get code from redis
    final var code =
        validateCodeManager
            .getCode(signUpRequest.getMail())
            .orElseThrow(() -> new AppException(ResultCode.REDIS_CODE_NOT_EXIST.value));

    // redis code =/= request verification code
    if (!code.equals(signUpRequest.getVerificationCode())) {
      throw new AppException(ResultCode.VERIFICATION_CODE_NOT_EQUAL.value);
    }

    // insert
    final var consumer = new Consumer();
    consumer.setFirstName(signUpRequest.getFirstName());
    consumer.setLastName(signUpRequest.getLastName());
    consumer.setMail(signUpRequest.getMail());
    consumer.setPassword(ToolUtil.encode(signUpRequest.getPassword()));
    consumerMapper.insert(consumer);

    return new SignUpResponse()
        .resultCode(ResultCode.SUCCESS.value)
        .token(jwtManager.generateToken(signUpRequest.getMail()));
  }
}
