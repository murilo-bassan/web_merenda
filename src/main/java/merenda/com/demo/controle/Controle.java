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
import merenda.com.demo.modelo.Restricao;
import merenda.com.demo.servico.FeedbackServico;
//import merenda.com.demo.repositorio.RestricaoRepositorio;
import merenda.com.demo.servico.RestricaoServico;

@Controller
public class Controle {
	
	
	@Autowired
	RestricaoServico restricaoServico;
	
	@Autowired
	FeedbackServico feedbackServico;

	@GetMapping("/")
	public String index() {
		return "/aluno/aluno-index";
	}
	
	@GetMapping("/redirectRestricoes")
	public String redirecionarRestricoes(Model model) {
		Restricao restricao = new Restricao();
		model.addAttribute("novaRestricao", restricao);
		return "/aluno/redirectRestricoes";
	}
	
	@GetMapping("/pagRestricoes")
	public String restricoes(Model model) {
		Restricao restricao = new Restricao();
		model.addAttribute("novaRestricao", restricao);
		return "/aluno/restricoes";
	}
	
	@PostMapping("/gravarRestricao")
	public String gravarRestricao(@ModelAttribute("novaRestricao") @Valid Restricao restricao,
			BindingResult erros, Model model, 
			RedirectAttributes attributes) {
		
		if(erros.hasErrors()) {
			return "/aluno/restricoes";
		}
		
		restricaoServico.criarRestricao(restricao);
		attributes.addFlashAttribute("mensagem", "Restricao salva com sucesso!");
		return "redirect:/redirectRestricoes";
	}

	@GetMapping("/redirectFeedback")
	public String redirecionarFeedback(Model model) {
		Feedback feedback = new Feedback();
		model.addAttribute("novoFeedback", feedback);
		return "/aluno/redirectFeedback";
	}
	
	@GetMapping("/pagFeedback")
	public String feedback(Model model) {
		Feedback feedback = new Feedback();
		model.addAttribute("novoFeedback", feedback);
		return "/aluno/feedback";
	}
	
	@PostMapping("/gravarFeedback")
	public String gravarFeedback(@ModelAttribute("novoFeedback") @Valid Feedback feedback,
			BindingResult erros, Model model, 
			RedirectAttributes attributes) {
		
		if(erros.hasErrors()) {
			return "/aluno/feedback";
		}
		
		feedbackServico.criarFeedback(feedback);
		attributes.addFlashAttribute("mensagem", "Feedback salva com sucesso!");
		return "redirect:/redirectFeedback";
	}
	
	@GetMapping("/redirectCardapio")
	public String redirecionarCardapio() {
		
		return "/aluno/redirectCardapio";
	}
	
	@GetMapping("/pagCardapio")
	public String cardapio() {
		
		return "/aluno/cardapio";
	}
	
	//@PostMapping("/gravar")
	//public String gravarEstudante(@ModelAttribute("novoEstudante") @Valid Estudante estudante,
	//		BindingResult erros, Model model, 
	//		RedirectAttributes attributes) {
	//	
	//	if(erros.hasErrors()) {
	//		return "/novo-estudante";
	//	}
	//
	//	Estudante estudante2 = estudanteRepositorio.findByLogin(estudante.getLogin());
	//	if(estudante2 != null){
	//		model.addAttribute("loginExiste", "Login já está cadastrado");
	//		return "/novo-estudante";
	//	}
	//
	//	estudanteServico.criarEstudante(estudante);
	//	attributes.addFlashAttribute("mensagem", "Estudante salvo com sucesso!");
	//	return "redirect:/novo";
	//}
}
