package merenda.com.demo.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import merenda.com.demo.modelo.Feedback;
import merenda.com.demo.repositorio.FeedbackRepositorio;
//import merenda.com.demo.servico.FeedbackServico;
import merenda.com.demo.service.FeedbackService;

@Controller
@RequestMapping("/feedback")
public class FeedbackControle {

	@Autowired
	FeedbackService feedbackService;
	
	@Autowired
	FeedbackRepositorio feedbackRepositorio;
	
	@GetMapping("/listar")
	public String listarFeedback(Model model) {
		model.addAttribute("feedbacks", feedbackRepositorio.findAll());		
		return "/auth/admin/admin-listar-feedbacks";	
	}
	
	@GetMapping("/redirectFeedback")
	public String redirecionarFeedback(Model model) {
		Feedback feedback = new Feedback();
		model.addAttribute("novoFeedback", feedback);
		return "/auth/aluno/redirectFeedback";
	}
	
	@GetMapping("/pagFeedback")
	public String feedback(Model model) {
		Feedback feedback = new Feedback();
		model.addAttribute("novoFeedback", feedback);
		return "/auth/aluno/feedback";
	}
	
	@PostMapping("/gravarFeedback")
	public String gravarFeedback(@ModelAttribute("novoFeedback") @Valid Feedback feedback,
			BindingResult erros, Model model, 
			RedirectAttributes attributes) {
		
		if(erros.hasErrors()) {
			return "/auth/aluno/feedback";
		}
		
		feedbackService.criarFeedback(feedback);
		attributes.addFlashAttribute("mensagem", "Feedback salvo com sucesso!");
		return "auth/aluno/redirectFeedback";
	}
	
}