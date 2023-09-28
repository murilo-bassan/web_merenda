package merenda.com.demo.controle;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import jakarta.validation.Valid;
import merenda.com.demo.dto.NoticiaCreate;
import merenda.com.demo.excecao.FotoNotFoundExcpetion;
import merenda.com.demo.modelo.Foto;
import merenda.com.demo.modelo.Noticia;
import merenda.com.demo.repositorio.FotoRepositorio;
import merenda.com.demo.repositorio.NoticiaRepositorio;
import merenda.com.demo.service.FotoService;
import merenda.com.demo.utils.FileUtils;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/noticia")
public class NoticiaControle {

	@Autowired
	private NoticiaRepositorio noticiaRepository;
	
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private FotoRepositorio fotoRepository;
	
	public static String uriRoot = "http://10.3.36.144:8081";

	
	
	
	@GetMapping("/listar")
	public String listarNoticia(Model model) {
		model.addAttribute("noticias", noticiaRepository.findAll());		
		return "/auth/admin/admin-listar-noticias";	
	}
	
	@GetMapping("/nova")
	public String adicionarNoticia(Model model) {
		model.addAttribute("item", new NoticiaCreate());
		return "/auth/admin/publica-criar-noticia";
	}
	
	@PostMapping("/salvar")
	public String salvarNoticia(@ModelAttribute("item") @Valid NoticiaCreate noticiaCreate, BindingResult result, 
				RedirectAttributes attributes, Model model) throws FotoNotFoundExcpetion {
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError e: errors) {
				String fieldErrors = ((FieldError) e).getField();
				System.out.println("---------- " + fieldErrors); 
			}
			return "/auth/admin/publica-criar-noticia";
		}	
		
		Noticia noticia = new Noticia();
		
		noticia.setTitulo(noticiaCreate.getTitulo());
		noticia.setSubtitulo(noticiaCreate.getSubtitulo());
		noticia.setCorpo(noticiaCreate.getCorpo());
		noticia.setAutor(noticiaCreate.getAutor());
		
		Noticia noticiaGravada=noticiaRepository.save(noticia);
		FileUtils fileUtils = new FileUtils();
		String uriUpload = uriRoot + "/upload";
		Map<String, String> map = null;
		for (MultipartFile file : noticiaCreate.getFotos()) {
			if (!file.isEmpty()) {
				if (!fileUtils.isImagem(file.getContentType())) {
					System.out.println(file.getContentType());
					throw new FotoNotFoundExcpetion("Não é uma foto");
				}
				try {
					map = fotoService.salvarFotoServidor(file, uriUpload);
					Foto foto = new Foto();
					foto.setUrl(map.get("url"));
					foto.setNome(map.get("name"));
					foto.setNoticia(noticiaGravada);
					fotoService.gravarFoto(foto);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		attributes.addFlashAttribute("mensagem", "Noticia adicionada com sucesso!");
		return "redirect:/noticia/listar";
	}
	

	@GetMapping("/admin/apagar/{id}")
	public String deletenoticia(@PathVariable("id") long id, Model model) {
		Noticia noticia = noticiaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
		noticiaRepository.delete(noticia);
	    return "redirect:/noticia/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editarNoticia(@PathVariable("id") long id, Model model) {
		Optional<Noticia> noticiaVelha = noticiaRepository.findById(id);
		if(!noticiaVelha.isPresent()) {
			throw new IllegalArgumentException("Noticia inválida: " + id);
		}

		
		Noticia noticia= noticiaVelha.get();
		model.addAttribute("noticia", noticia);
		return "/auth/admin/admin-alter-noticia";
	}
	
	@PostMapping("/editar/{id}")
	public String editarnoticia(@PathVariable("id") long id,
			@Valid Noticia noticia, BindingResult result, Model model) {
		if(result.hasErrors()) {
			noticia.setId(id);
			return "/auth/admin/admin-alter-noticia";
		}
		noticiaRepository.save(noticia);
		return "redirect:/noticia/listar";
	}
	
	@GetMapping("/listarFotos/{idNoticia}")
	public String listarFotos(@PathVariable("idNoticia") long idNoticia, Model model) {
		Noticia noticia = noticiaRepository.findById(idNoticia)
		.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + idNoticia));
		model.addAttribute("noticia", noticia);
		return "auth/admin/admin-listar-alter";
	}
		
	
	@GetMapping("/admin/apagarFoto/{idNoticia}/{idFoto}")
	public String deleteFotoNoticia(@PathVariable("idNoticia") long idNoticia, @PathVariable("idFoto") long idFoto,
		Model model, RedirectAttributes attributes) {
		Foto foto = fotoRepository.findById(idFoto)
		.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + idFoto));
		fotoRepository.delete(foto);
	    return "auth/admin/admin-listar-alter";
	}
	
    @PostMapping("/{noticiaId}/adicionar-fotos")
    public String deleteFotoNoticia(@PathVariable("idNoticia") long idNoticia, Model model, RedirectAttributes attributes) {
    	Noticia noticia = noticiaRepository.findById(idNoticia)
    	.orElseThrow(() -> new IllegalArgumentException("Id inválido:" + idNoticia));
    	
    	
    		return "/auth/admin/admin-alter-noticia";


    }
    }



