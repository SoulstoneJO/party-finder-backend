package top.service;

import generated.model.ConsumerProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.common.JwtManager;
import top.repository.entity.generated.ConsumerExample;
import top.repository.mapper.generated.ConsumerMapper;

/** ConsumerProfileService. */
@Service
public class ConsumerProfileService {
  @Autowired JwtManager jwtManager;
  @Autowired ConsumerMapper consumerMapper;

  /**
   * queryProfile.
   *
   * @param token String
   * @return ConsumerProfileResponse
   */
  public ConsumerProfileResponse queryProfile(String token) {
    final var mail = jwtManager.validateToken(token);

    final var example = new ConsumerExample();
    example.createCriteria().andMailEqualTo(mail);

    final var response = new ConsumerProfileResponse();

    consumerMapper.selectByExample(example).stream().findFirst().ifPresent(consumer -> response
        .firstName(consumer.getFirstName())
        .lastName(consumer.getLastName())
        .mail(consumer.getMail()));

    return response;
  }
}
