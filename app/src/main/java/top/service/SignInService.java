package top.service;

import generated.model.SignInRequest;
import generated.model.SignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.common.JwtManager;
import top.common.ToolUtil;
import top.common.exception.AppException;
import top.constant.CommonConstant.ResultCode;
import top.repository.entity.generated.ConsumerExample;
import top.repository.mapper.generated.ConsumerMapper;

/** SignInService. */
@Service
public class SignInService {
  @Autowired ConsumerMapper consumerMapper;
  @Autowired JwtManager jwtManager;

  /**
   * doSignIn.
   *
   * @param signInRequest SignInRequest
   * @return SignInResponse
   */
  public SignInResponse doSignIn(SignInRequest signInRequest) {

    final var example = new ConsumerExample();
    final var criteria = example.createCriteria();
    criteria
        .andMailEqualTo(signInRequest.getMail())
        .andPasswordEqualTo(ToolUtil.encode(signInRequest.getPassword()));

    final var consumerExisted = consumerMapper.countByExample(example);

    final var signInResponse = new SignInResponse();

    if (consumerExisted > 0) {
      signInResponse.setToken(jwtManager.generateToken(signInRequest.getMail()));
    } else {
      throw new AppException(ResultCode.AUTH_ERROR.value);
    }

    return signInResponse.resultCode(ResultCode.SUCCESS.value);
  }
}
