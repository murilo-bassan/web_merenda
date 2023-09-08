package merenda.com.demo.servico;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import merenda.com.demo.modelo.Feedback;
import merenda.com.demo.repositorio.FeedbackRepositorio;

@Service
public class FeedbackServico {

		@Autowired
		private FeedbackRepositorio feedbackRepositorio;
		
		public Feedback criarFeedback(Feedback feedback) {
			return feedbackRepositorio.save(feedback);
		}
		
		//import - java.util
		//public List<Restricao> buscarTodosEstudantes() {
		//	return restricaoRepositorio.findAll();
		//}
}
