package top.service;

import generated.model.SignUpRequest;
import generated.model.SignUpResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.common.JwtManager;
import top.common.ToolUtil;
import top.repository.entity.generated.Consumer;
import top.repository.mapper.generated.ConsumerMapper;

/** SignUpService. */
@Service
public class SignUpService {
  @Autowired ConsumerMapper consumerMapper;
  @Autowired JwtManager jwtManager;

  /**
   * doSignUp.
   *
   * @param signUpRequest SignUpRequest
   * @return SignUpResponse
   */
  public SignUpResponse doSignUp(@Valid SignUpRequest signUpRequest) {
    // insert
    final var consumer = new Consumer();
    consumer.setFirstName(signUpRequest.getFirstName());
    consumer.setLastName(signUpRequest.getLastName());
    consumer.setMail(signUpRequest.getMail());
    consumer.setPassword(ToolUtil.encode(signUpRequest.getPassword()));
    consumerMapper.insert(consumer);

    final var token =
        jwtManager.generateToken(signUpRequest.getMail());

    return new SignUpResponse().token(token);
  }
}
