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

//import br.edu.ifms.demo.modelo.Estudante;
import jakarta.validation.Valid;
import merenda.com.demo.modelo.Categoria;
import merenda.com.demo.modelo.Item;
import merenda.com.demo.modelo.Tipo_merenda;
import merenda.com.demo.repositorio.CardapioRepositorio;
import merenda.com.demo.repositorio.CategoriaRepositorio;
import merenda.com.demo.repositorio.ItemRepositorio;
import merenda.com.demo.repositorio.Tipo_merendaRepositorio;

@Controller
@RequestMapping("/cardapio")
public class CardapioControle {
	
	@Autowired
	private Tipo_merendaRepositorio tipo_merendaRepository;
	
	@Autowired
	private CategoriaRepositorio categoriaRepository;
	
	@Autowired
	private ItemRepositorio itemRepository;
	
	@Autowired
	private CardapioRepositorio cardapioRepositorio;
	
	@GetMapping("/redirectCardapio")
	public String redirecionarCardapio() {

	return "/auth/aluno/redirectCardapio";
	}

	@GetMapping("/pagCardapio")
	public String cardapio(Model model) {
		Tipo_merenda tipo_merenda = new Tipo_merenda();
		model.addAttribute("tipo_merenda", tipo_merenda);
		
		model.addAttribute("listaCardapios", cardapioRepositorio.findAll());	
		
	return "/auth/aluno/cardapio";
	}

	@GetMapping("/listar")
	public String listarNoticia(Model model) {
		return "/auth/admin/admin-listar-cardapio";	
	}
	
	@GetMapping("/novoTipo")
	public String adicionarTipo(Model model) {
		model.addAttribute("tipo_merenda", new Tipo_merenda());
		return "/auth/admin/admin-criar-tipo";
	}

	

	@GetMapping("/novaCategoria")
	public String adicionarCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		List<Tipo_merenda> tipo_merenda = tipo_merendaRepository.findAll();
		model.addAttribute("tipo", tipo_merenda);
		return "/auth/admin/admin-criar-categoria";
	}

	

	@GetMapping("/novoItem")
	public String adicionarNoticia(Model model) {
		model.addAttribute("item", new Item());
		List<Categoria> categoria = categoriaRepository.findAll();
		model.addAttribute("categoria", categoria);
		return "/auth/admin/admin-criar-item";
	}
	
	@PostMapping("/salvarTipo")
	public String salvarTipo(@Valid Tipo_merenda tipo_merenda, BindingResult result, 
				RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "/auth/admin/admin-criar-tipo";
		}	
		tipo_merendaRepository.save(tipo_merenda);
		attributes.addFlashAttribute("mensagem", "Tipo salvo com sucesso!");
		return "redirect:/cardapio/novoTipo";
	}
	
	@PostMapping("/salvarCategoria")
	public String salvarCategoria(@Valid Categoria categoria, BindingResult result, 
				RedirectAttributes attributes, Model model) {
		if (result.hasErrors()) {
			List<Tipo_merenda> tipo_merenda = tipo_merendaRepository.findAll();
			model.addAttribute("tipo", tipo_merenda);
			return "/auth/admin/admin-criar-categoria";
		}	
		categoriaRepository.save(categoria);
		attributes.addFlashAttribute("mensagem", "Categoria salva com sucesso!");
		return "redirect:/cardapio/novaCategoria";
	}
	
	@PostMapping("/salvarItem")
	public String salvarItem(@Valid Item item, BindingResult result, 
				RedirectAttributes attributes, Model model) {
		if (result.hasErrors()) {
			List<Categoria> categoria = categoriaRepository.findAll();
			model.addAttribute("categoria", categoria);
			return "auth/admin/admin-criar-item";
		}	
		itemRepository.save(item);
		attributes.addFlashAttribute("mensagem", "Item salvo com sucesso!");
		return "redirect:/cardapio/novoItem";
	}
	
}