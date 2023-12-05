package merenda.com.demo.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import merenda.com.demo.modelo.Categoria;
import merenda.com.demo.repositorio.CategoriaRepositorio;

@Controller
@RequestMapping("/categoria")
public class CategoriaControle {
	
	@Autowired
	private CategoriaRepositorio categoriaRepository;

	
	@GetMapping("/nova")
	public String adicionarCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		model.addAttribute("listaCategorias", categoriaRepository.findAll());
		return "/auth/admin/admin-criar-categoria";
	}
	
	
	@GetMapping("/listar")  // Lista de cardapio
	public String listarCategoria(Model model) {
		model.addAttribute("listaCategorias", categoriaRepository.findAll());
		return "/auth/admin/admin-listar-categoria";	
	}
	
	@GetMapping("/apagar/{id}")
	public String deleteCategoria(@PathVariable("id") long id, Model model) {
		Categoria categoria = categoriaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		categoriaRepository.delete(categoria);
		return "redirect:/categoria/listar";
	}
	
	@PostMapping("/salvar")
	public String salvarCategoria(@Valid Categoria categoria, BindingResult result, 
				RedirectAttributes attributes, Model model) {
		if (result.hasErrors()) {
			//List<Tipo_merenda> tipo_merenda = tipo_merendaRepository.findAll();
			//model.addAttribute("tipo", tipo_merenda);
			return "/auth/admin/admin-criar-categoria";
		}	
		categoriaRepository.save(categoria);
		attributes.addFlashAttribute("mensagem", "Categoria salva com sucesso!");
		return "redirect:/categoria/nova";
	}
}
