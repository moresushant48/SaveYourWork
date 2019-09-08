package io.moresushant48.services;

import io.moresushant48.model.Email;
import io.moresushant48.model.Feedback;

public interface FeedbackService {

	public void saveFeedback(Feedback feedback);

	public void sendEmail(Email email);
	
}
