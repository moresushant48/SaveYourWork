package io.moresushant48.servicesImpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import io.moresushant48.Repository.FeedbackRepository;
import io.moresushant48.model.Email;
import io.moresushant48.model.Feedback;
import io.moresushant48.services.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	JavaMailSender javaMailSender;

	@Override
	public void saveFeedback(Feedback feedback) {
		
		feedbackRepository.save(feedback);
	}
	
	@Override
	public void sendEmail(Email email) {
		
		 MimeMessage message = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message);

	        try {
	            helper.setFrom(email.getEmail());
	            helper.setTo(Email.getMyEmail());
	            helper.setSubject("Emailed by : " + email.getName());
	            helper.setText(email.getMessage());
	            
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	        javaMailSender.send(message);
	}
}
