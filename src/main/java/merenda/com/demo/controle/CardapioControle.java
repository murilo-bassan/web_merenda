package merenda.com.demo.controle;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import br.edu.ifms.demo.modelo.Estudante;
import jakarta.validation.Valid;
import merenda.com.demo.modelo.Agenda;
import merenda.com.demo.modelo.Cardapio;
import merenda.com.demo.modelo.Categoria;
import merenda.com.demo.modelo.Item;
import merenda.com.demo.modelo.Tipo_merenda;
import merenda.com.demo.repositorio.AgendaRepositorio;
import merenda.com.demo.repositorio.CardapioRepositorio;
import merenda.com.demo.repositorio.CategoriaRepositorio;
import merenda.com.demo.repositorio.ItemRepositorio;
import merenda.com.demo.repositorio.Tipo_merendaRepositorio;
import merenda.com.demo.utils.DataUtil;

@Controller
@RequestMapping("/cardapio")
public class CardapioControle {
	
	@Autowired
	private AgendaRepositorio agendaRepositorio;

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

	@GetMapping("/listar")  // Lista de cardapio
	public String listarCardapio(Model model) {
		List<Cardapio> cardapios = cardapioRepositorio.findAll();
		model.addAttribute("ListaCardapio",cardapios);
		return "/auth/admin/admin-listar-cardapio";	
	}
	
	
	@GetMapping("/novoTipo")
	public String adicionarTipo(Model model) {
		model.addAttribute("tipo_merenda", new Tipo_merenda());
		return "/auth/admin/admin-criar-tipo";
	}

	@GetMapping("/novaAgenda")
	public String adicionarAgenda(Model model) {
		model.addAttribute("agenda", new Agenda());
		List<Cardapio> cardapio = cardapioRepositorio.findAll();
		model.addAttribute("cardapio", cardapio);
		return "/auth/admin/admin-criar-agenda";
	}

	@GetMapping("/novaCategoria")
	public String adicionarCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		model.addAttribute("listaCategorias", categoriaRepository.findAll());
		return "/auth/admin/admin-criar-categoria";
	}
	
	
	@GetMapping("/listarCategoria")  // Lista de cardapio
	public String listarCategoria(Model model) {
		model.addAttribute("listaCategorias", categoriaRepository.findAll());
		return "/auth/admin/admin-listar-categoria";	
	}
	
	@GetMapping("/apagarCategoria/{id}")
	public String deleteCategoria(@PathVariable("id") long id, Model model) {
		Categoria categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		categoriaRepository.delete(categoria);
		return "redirect:/cardapio/listarCategoria";
	}
	
	
	
	
	
	@GetMapping("/novoCardapio")
	public String adicionarCardapio(Model model) {
		model.addAttribute("cardapio", new Cardapio());
		List<Cardapio> cardapio = cardapioRepositorio.findAll();
		model.addAttribute("cardapio", cardapio);
		
		List<Categoria> categorias = categoriaRepository.findAll();
		model.addAttribute("categorias", categorias);
		
		List<Agenda> agendas = agendaRepositorio.findAll();
		Agenda agenda = agendas.get(agendas.size() - 1);
		
		DataUtil dataUtil = new DataUtil();
		LocalDate d1 = dataUtil.convertToLocalDateViaInstant(agenda.getDataInicio());
		LocalDate d2 = dataUtil.convertToLocalDateViaInstant(agenda.getDataFim());
		
		int daysBetween = (int)ChronoUnit.DAYS.between(d1, d2);
		
		model.addAttribute("dias", daysBetween + 1);
		model.addAttribute("agenda", agenda);
		model.addAttribute("dI", agenda.dataIFormatada());
		return "/auth/admin/admin-criar-cardapio";
	}
	
	
	

	@GetMapping("/novoItem")
	public String adicionarNoticia(Model model) {
		model.addAttribute("item", new Item());
		List<Categoria> categoria = categoriaRepository.findAll();
		model.addAttribute("categoria", categoria);
		return "/auth/admin/admin-criar-item";
	}
	
	@GetMapping("/listarItem")  // Lista de cardapio
	public String listarItem(Model model) {
		model.addAttribute("listaItens", itemRepository.findAll());
		return "/auth/admin/admin-listar-itens";	
	}
	
	@GetMapping("/apagarItem/{id}")
	public String deleteItem(@PathVariable("id") long id, Model model) {
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		itemRepository.delete(item);
		return "redirect:/cardapio/listarItem";
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
	

	@PostMapping("/salvarAgenda")
	public String salvarAgenda(@Valid Agenda agenda, BindingResult result, 
				RedirectAttributes attributes, Model model) {
		if (result.hasErrors()) {
			List<Cardapio> cardapio = cardapioRepositorio.findAll();
			model.addAttribute("cardapios", cardapio);
			return "/auth/admin/admin-criar-agenda";
		}	
		agendaRepositorio.save(agenda);
		attributes.addFlashAttribute("mensagem", "Agenda salva com sucesso!");
		return "redirect:/cardapio/novoCardapio";
	}


	@PostMapping("/salvarCategoria")
	public String salvarCategoria(@Valid Categoria categoria, BindingResult result, 
				RedirectAttributes attributes, Model model) {
		if (result.hasErrors()) {
			//List<Tipo_merenda> tipo_merenda = tipo_merendaRepository.findAll();
			//model.addAttribute("tipo", tipo_merenda);
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