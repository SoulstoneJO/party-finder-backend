package top.service;

import generated.model.SendMailValidationRequest;
import generated.model.SendMailValidationResponse;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.common.MailManager;
import top.common.ValidateCodeManager;
import top.common.exception.AppException;
import top.constant.CommonConstant.ResultCode;
import top.repository.entity.generated.ConsumerExample;
import top.repository.mapper.generated.ConsumerMapper;

/** SendMailValidationService. */
@Service
public class SendMailValidationService {
  @Autowired ConsumerMapper consumerMapper;
  @Autowired ValidateCodeManager validateCodeManager;
  @Autowired MailManager mailManager;

  /**
   * doSendMailValidation.
   *
   * @param request SendMailValidationRequest
   * @return SendMailValidationResponse
   */
  public SendMailValidationResponse doSendMailValidation(SendMailValidationRequest request) {
    final var example = new ConsumerExample();
    example.createCriteria().andMailEqualTo(request.getMail());
    if (consumerMapper.selectByExample(example).stream().findFirst().isPresent()) {
      throw new AppException(ResultCode.USER_EXIST.value);
    }

    StringBuilder code = new StringBuilder();
    for (int i = 0; i < 7; i++) {
      code.append(new Random().nextInt(10));
    }

    validateCodeManager.setCode(request.getMail(), String.valueOf(code));

    mailManager.sendValidateMail(
        request.getFirstName(), request.getLastName(), request.getMail(), String.valueOf(code));

    return new SendMailValidationResponse().resultCode(ResultCode.SUCCESS.value);
  }
}
