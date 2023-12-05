package merenda.com.demo.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import merenda.com.demo.modelo.Agenda;
import merenda.com.demo.modelo.Cardapio;
import merenda.com.demo.repositorio.AgendaRepositorio;
import merenda.com.demo.repositorio.CardapioRepositorio;

@Controller
@RequestMapping("/agenda")
public class AgendaControle {
	
	@Autowired
	CardapioRepositorio cardapioRepositorio;
	
	@Autowired
	AgendaRepositorio agendaRepositorio;

	@GetMapping("/nova")
	public String adicionarAgenda(Model model) {
		model.addAttribute("agenda", new Agenda());
		List<Cardapio> cardapio = cardapioRepositorio.findAll();
		model.addAttribute("cardapio", cardapio);
		return "/auth/admin/admin-criar-agenda";
	}
	
	@PostMapping("/salvar")
	public String salvarAgenda(@Valid Agenda agenda, BindingResult result, 
				RedirectAttributes attributes, Model model) {
		if (result.hasErrors()) {
			List<Cardapio> cardapio = cardapioRepositorio.findAll();
			model.addAttribute("cardapios", cardapio);
			return "/auth/admin/admin-criar-agenda";
		}	
		agendaRepositorio.save(agenda);
		attributes.addFlashAttribute("mensagem", "Agenda salva com sucesso!");
		return "redirect:/cardapio/novo";
	}
	
}
