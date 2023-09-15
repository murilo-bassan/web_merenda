package br.ifms.merenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifms.merenda.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
