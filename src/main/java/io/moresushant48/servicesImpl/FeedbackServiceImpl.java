package io.moresushant48.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.moresushant48.Repository.FeedbackRepository;
import io.moresushant48.model.Feedback;
import io.moresushant48.services.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Override
	public void saveFeedback(Feedback feedback) {
		
		feedbackRepository.save(feedback);
	}
}
