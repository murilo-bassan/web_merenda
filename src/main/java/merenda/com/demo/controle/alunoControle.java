package merenda.com.demo.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import merenda.com.demo.modelo.Restricao;
//import merenda.com.demo.repositorio.RestricaoRepositorio;
import merenda.com.demo.servico.RestricaoServico;

@Controller
public class alunoControle {
	
	
	@Autowired
	RestricaoServico restricaoServico;

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
	
	@PostMapping("/gravar")
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
