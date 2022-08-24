package top.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.constant.JwtConstant;
import top.repository.entity.generated.ConsumerExample;
import top.repository.mapper.generated.ConsumerMapper;

/** JWTUtil. */
@Component
@RequiredArgsConstructor
public class JwtManager {
  private final JwtConstant jwtConstant;
  @Autowired ConsumerMapper consumerMapper;

  /**
   * generateToken.
   *
   * @param subject String
   * @return String[token]
   */
  public String generateToken(String subject) {
    return jwtConstant.getTokenPrefix()
        + JWT.create()
            .withSubject(subject)
            .withExpiresAt(new Date(System.currentTimeMillis() + jwtConstant.getExpireTime()))
            .sign(Algorithm.HMAC256(jwtConstant.getSecret()));
  }

  /**
   * validateToken.
   *
   * @param token String
   * @return String
   */
  @SneakyThrows
  public String validateToken(String token) {
    final var jwtVerifier = JWT.require(Algorithm.HMAC256(jwtConstant.getSecret())).build();
    final var noPrefixToken = token.replace(jwtConstant.getTokenPrefix(), "");
    final var decodedToken = jwtVerifier.verify(noPrefixToken);

    final var example = new ConsumerExample();
    final var criteria = example.createCriteria();
    criteria.andMailEqualTo(decodedToken.getSubject());
    consumerMapper.countByExample(example);

    if (consumerMapper.countByExample(example) > 0) {
      return decodedToken.getSubject();
    } else {
      // TODO
      throw new Exception();
    }
  }
}
