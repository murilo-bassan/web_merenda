package merenda.com.demo.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import merenda.com.demo.excecao.CategoriaNotFoundException;
import merenda.com.demo.modelo.Categoria;
import merenda.com.demo.modelo.Item;
import merenda.com.demo.repositorio.CategoriaRepositorio;
import merenda.com.demo.repositorio.ItemRepositorio;
import merenda.com.demo.service.ItemService;


@Controller
@RequestMapping("item")
public class ItemControle {
	
	@Autowired
	CategoriaRepositorio categoriaRepository;
	
	@Autowired
	ItemRepositorio itemRepository;
	
	@Autowired
	ItemService itemService;
	

	@GetMapping("/novo")
	public String adicionarNoticia(Model model) {
		model.addAttribute("item", new Item());
		List<Categoria> categoria = categoriaRepository.findAll();
		model.addAttribute("categoria", categoria);
		return "/auth/admin/admin-criar-item";
	}
	
	@GetMapping("/listar")  // Lista de cardapio
	public String listarItem(Model model) {
		model.addAttribute("listaItens", itemRepository.findAll());
		return "/auth/admin/admin-listar-itens";	
	}
	
	@GetMapping("/apagar/{id}")
	public String deleteItem(@PathVariable("id") long id, Model model) {
		Item item = itemRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		itemRepository.delete(item);
		return "redirect:/item/listar";
	}
	
	@PostMapping("/salvar")
	public String salvarItem(@Valid Item item, BindingResult result, 
				RedirectAttributes attributes, Model model) {
		if (result.hasErrors()) {
			List<Categoria> categoria = categoriaRepository.findAll();
			model.addAttribute("categoria", categoria);
			return "auth/admin/admin-criar-item";
		}	
		itemRepository.save(item);
		attributes.addFlashAttribute("mensagem", "Item salvo com sucesso!");
		return "redirect:/item/novo";
	}
	
	@GetMapping("/editar/{id}")
	public String editarItem(@PathVariable("id") long id, RedirectAttributes attributes, Model model) {
		try {
			Item item = itemService.buscarItemPorId(id);
			model.addAttribute("item", item);
			List<Categoria> categorias = categoriaRepository.findAll();
			model.addAttribute("categorias", categorias);
			return "/auth/admin/admin-alterar-item";
		} catch (CategoriaNotFoundException e) {
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
		return "/auth/admin/admin-alterar-item";
	}
	
	@PostMapping("/editar/{id}")
	public String editarItem(@PathVariable("id") long id, @ModelAttribute("novaMusica") @Valid Item item,
			BindingResult erros, Model model) {

		if (erros.hasErrors()) {
			item.setId(id);
			List<Categoria> categorias = categoriaRepository.findAll();
			model.addAttribute("categiorias", categorias);
			return "/auth/admin/admin-alterar-item";
		}
		itemRepository.save(item);

		return "redirect:/item/listar";
	}
	
	
	
	
	
}
