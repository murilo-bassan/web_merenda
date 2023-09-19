package merenda.com.demo.controle;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import merenda.com.demo.dto.RestricaoCreate;
import merenda.com.demo.excecao.FotoNotFoundExcpetion;
import merenda.com.demo.modelo.Restricao;
import merenda.com.demo.repositorio.RestricaoRepositorio;
import merenda.com.demo.service.RestricaoService;
import merenda.com.demo.utils.FileUtils;

@Controller
@RequestMapping("/restricao")
public class RestricaoControle {
	
	@Autowired
	RestricaoService restricaoService;
	
	@Autowired
	RestricaoRepositorio restricaoRepositorio;
	
	public static String uriRoot = "http://10.3.36.144:8081";
	
	@GetMapping("/listar")
	public String listarRestricoes(Model model) {
		model.addAttribute("restricoes", restricaoRepositorio.findAll());		
		return "/auth/admin/admin-listar-restricoes";	
	}	

	@GetMapping("/redirectRestricoes")
	public String redirecionarRestricoes(Model model) {
		Restricao restricao = new Restricao();
		model.addAttribute("novaRestricao", restricao);
		return "/auth/aluno/redirectRestricoes";
	}
	
	@GetMapping("/pagRestricoes")
	public String restricoes(Model model) {
		Restricao restricao = new Restricao();
		model.addAttribute("novaRestricao", restricao);
		return "/auth/aluno/restricoes";
	}
	
	
	@PostMapping("/salvar")
	public String salvarNoticia(@ModelAttribute("novaRestricao") @Valid RestricaoCreate restricaoCreate, BindingResult result, 
				RedirectAttributes attributes, Model model) throws FotoNotFoundExcpetion {
		if (result.hasErrors()) {
		
			return "/auth/aluno/restricoes";
		}	
		
		FileUtils fileUtils = new FileUtils();
		String uriUpload = uriRoot + "/upload";
		Map<String, String> map = null;
		for (MultipartFile file : restricaoCreate.getPdf()) {
			if (!file.isEmpty()) {
				/*if (!fileUtils.isImagem(file.getContentType())) {
					System.out.println(file.getContentType());
					throw new FotoNotFoundExcpetion("Não é uma foto");
				}*/
				try {
					map = restricaoService.salvarPdfServidor(file, uriUpload);
					Restricao restricao = new Restricao();
					
					restricao.setDescricaoRestricao(restricaoCreate.getDescricaoRestricao());
					restricao.setNomeAluno(restricaoCreate.getNomeAluno());
					restricao.setUrl(map.get("url"));
					
					restricaoService.gravarRestricao(restricao);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		attributes.addFlashAttribute("mensagemRestricao", "Restricao adicionada com sucesso!");
		return "auth/aluno/redirectRestricoes";
	}
	
}
