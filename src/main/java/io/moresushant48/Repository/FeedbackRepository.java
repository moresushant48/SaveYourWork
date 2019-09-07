package io.moresushant48.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.moresushant48.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>{

}
