package top.common;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/** MailManager. */
@Component
public class MailManager {
  @Autowired private JavaMailSender mailSender;
  @Autowired private TemplateEngine templateEngine;

  /** sendValidateMail. */
  @SneakyThrows
  public void sendValidateMail(String firstName, String lastName, String mailAddress, String code) {
    Context context = new Context();
    context.setVariable("firstName", firstName);
    context.setVariable("lastName", lastName);
    context.setVariable("code", code);

    String process = templateEngine.process("mail/validate.html", context); // TODO localization
    javax.mail.internet.MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
    helper.setSubject("Validate Mail[Party Finder]");
    helper.setText(process, true);
    helper.setTo(mailAddress);
    mailSender.send(mimeMessage);
  }
}
