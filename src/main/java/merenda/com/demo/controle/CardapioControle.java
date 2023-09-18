package merenda.com.demo.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cardapio")
public class CardapioControle {

	@GetMapping("/redirectCardapio")
	public String redirecionarCardapio() {
		
		return "/auth/aluno/redirectCardapio";
	}
	
	@GetMapping("/pagCardapio")
	public String cardapio() {
		
		return "/auth/aluno/cardapio";
	}
	
}
