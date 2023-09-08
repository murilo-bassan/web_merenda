package merenda.com.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import merenda.com.demo.modelo.Feedback;


public interface FeedbackRepositorio extends JpaRepository<Feedback, Long> {

}
