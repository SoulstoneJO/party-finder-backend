package top.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/** JwtConstant. */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConstant {
  private String header;
  private String tokenPrefix;
  private String secret;
  private long expireTime;
}
