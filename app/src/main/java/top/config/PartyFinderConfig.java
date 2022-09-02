package top.config;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

/** PartyFinderConfig. */
@Configuration
public class PartyFinderConfig {

  /**
   * getTemplateEngine.
   *
   * @return TemplateEngine
   */
  @Bean
  public TemplateEngine getTemplateEngine() {
    return new SpringTemplateEngine();
  }

  /**
   * getJavaMailSender.
   *
   * @return JavaMailSender
   */
  @Bean
  public JavaMailSender getJavaMailSender() {
    // TODO application.yaml
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com");
    mailSender.setPort(587);

    // TODO mail address
    mailSender.setUsername();
    // TODO mail password
    mailSender.setPassword();

    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.debug", "true");

    return mailSender;
  }
}
