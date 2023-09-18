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

import br.ifms.merenda.service.FeedbackService;
import jakarta.validation.Valid;
import merenda.com.demo.modelo.Feedback;
//import merenda.com.demo.servico.FeedbackServico;

@Controller
@RequestMapping("/feedback")
public class FeedbackControle {

	@Autowired
	FeedbackService feedbackService;
	
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
		attributes.addFlashAttribute("mensagem", "Feedback salva com sucesso!");
		return "redirect:/redirectFeedback";
	}
	
}
