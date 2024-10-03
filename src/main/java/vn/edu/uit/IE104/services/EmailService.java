package vn.edu.uit.IE104.services;


import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import vn.edu.uit.IE104.entity.EmailDetails;


import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Service
public class EmailService {
   @Autowired
   private JavaMailSender mailSender;
   @Autowired
   private ResourceLoader resourceLoader;
   @Value("${spring.mail.username}") private String sender;
   public String sendVerifyEmail(EmailDetails details)
   {
       try {
           MimeMessage mimeMessage = mailSender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
           helper.setFrom(sender);
           helper.setTo(details.getRecipient());
           helper.setText(details.getMsgBody(), true);
           helper.setSubject(details.getSubject());


           mailSender.send(mimeMessage);
           return "Mail Sent Successfully...";
       }
       catch (Exception e) {
           return "Error while Sending Mail";
       }
   }


   public String loadEmailTemplate() throws IOException {
       Resource resource = resourceLoader.getResource("classpath:templates/EmailVerify.html");
       return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
   }
}
