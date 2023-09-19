package merenda.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import merenda.com.demo.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
