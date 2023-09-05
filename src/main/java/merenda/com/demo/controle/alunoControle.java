package merenda.com.demo.controle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import merenda.com.demo.modelo.Restricao;

@Controller
public class alunoControle {

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
	
	@GetMapping("/restricoes")
	public String restricoes() {
		
		return "/aluno/restricoes";
	}
	
	//@GetMapping("/novo")
	//public String novoEstudante(Model model) {
	//	Estudante estudante = new Estudante();
	//	model.addAttribute("novoEstudante", estudante);
	//	return "/novo-estudante";
	//}
	//tradutor
	//sei la	
}
