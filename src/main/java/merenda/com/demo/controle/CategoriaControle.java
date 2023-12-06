package merenda.com.demo.controle;

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
import merenda.com.demo.repositorio.CategoriaRepositorio;
import merenda.com.demo.service.CategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaControle {
	
	@Autowired
	private CategoriaRepositorio categoriaRepository;
	
	@Autowired
	private CategoriaService categoriaService;

	
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
	
	@GetMapping("/editar/{id}")
	public String editarCategoria(@PathVariable("id") long id, RedirectAttributes attributes, Model model) {
		try {
			Categoria categoria = categoriaService.buscarCategoriaPorId(id);
			model.addAttribute("categoria", categoria);
			return "/auth/admin/admin-alterar-categoria";
		} catch (CategoriaNotFoundException e) {
			attributes.addFlashAttribute("mensagemErro", e.getMessage());
		}
		return "/auth/admin/admin-alterar-categoria"; //criar
	}

	@PostMapping("/editar/{id}")
	public String editarCategoria(@PathVariable("id") long id, @ModelAttribute("categoria") @Valid Categoria categoria,
			BindingResult erros) {

		if (erros.hasErrors()) {
			categoria.setId(id);
			return "/auth/admin/admin-alterar-categoria";
		}
		categoriaRepository.save(categoria);

		return "redirect:/categoria/listar";
	}
}
