package io.moresushant48.servicesImpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import io.moresushant48.model.Email;
import io.moresushant48.services.EmailService;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendEmail(String emailTo, String subject, String message) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		
		try {
			
			mimeMessageHelper.setFrom(Email.getMyEmail());
			mimeMessageHelper.setTo(emailTo);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(message);
			
			javaMailSender.send(mimeMessage);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
