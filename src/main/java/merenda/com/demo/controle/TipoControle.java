package merenda.com.demo.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import merenda.com.demo.modelo.Tipo_merenda;
import merenda.com.demo.repositorio.Tipo_merendaRepositorio;

@Controller
@RequestMapping("/tipo")
public class TipoControle {
	
	@Autowired
	Tipo_merendaRepositorio tipo_merendaRepository;

	
	@GetMapping("/novo")
	public String adicionarTipo(Model model) {
		model.addAttribute("tipo_merenda", new Tipo_merenda());
		return "/auth/admin/admin-criar-tipo";
	}
	
	@PostMapping("/salvar")
	public String salvarTipo(@Valid Tipo_merenda tipo_merenda, BindingResult result, 
				RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "/auth/admin/admin-criar-tipo";
		}	
		tipo_merendaRepository.save(tipo_merenda);
		attributes.addFlashAttribute("mensagem", "Tipo salvo com sucesso!");
		return "redirect:/cardapio/novoTipo";
	}
}
