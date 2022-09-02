package top.common;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/** ValidateCodeManager. */
@Component
public class ValidateCodeManager {
  @Autowired RedisTemplate<String, String> redisTemplate;

  public void setCode(String mail, String code) {
    redisTemplate.opsForValue().set(mail, code, 10, TimeUnit.MINUTES);
  }

  public Optional<String> getCode(String code) {
    return Optional.ofNullable(redisTemplate.opsForValue().get(code));
  }

  public Boolean hasCode(String mail) {
    return redisTemplate.hasKey(mail);
  }

  public Boolean deleteCode(String mail) {
    return redisTemplate.delete(mail);
  }
}
