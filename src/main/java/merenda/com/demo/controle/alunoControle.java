package merenda.com.demo.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class alunoControle {

	@GetMapping("/")
	public String index() {
		return "/aluno/aluno-index";
	}
	//tradutor
}
