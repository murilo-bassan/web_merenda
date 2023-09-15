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
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifms.merenda.controller.utils.FileUtils;
import br.ifms.merenda.dto.RestricaoCreate;
import br.ifms.merenda.excecao.FotoNotFoundExcpetion;
import br.ifms.merenda.service.RestricaoService;
import jakarta.validation.Valid;
import merenda.com.demo.modelo.Feedback;
import merenda.com.demo.modelo.Restricao;
import merenda.com.demo.servico.FeedbackServico;
//import merenda.com.demo.repositorio.RestricaoRepositorio;
//import merenda.com.demo.servico.RestricaoServico;

@Controller
@RequestMapping("/aluno")
public class Controle {
	
	
	@Autowired
	RestricaoService restricaoService;
	
	@Autowired
	FeedbackServico feedbackServico;
	
	public static String uriRoot = "http://10.3.36.144:8081";

	@GetMapping("/")
	public String index() {
		return "/auth/aluno/aluno-index";
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
	
	

	@GetMapping("/redirectFeedback")
	public String redirecionarFeedback(Model model) {
		Feedback feedback = new Feedback();
		model.addAttribute("novoFeedback", feedback);
		return "/auth/aluno/redirectFeedback";
	}
	
	@GetMapping("/pagFeedback")
	public String feedback(Model model) {
		Feedback feedback = new Feedback();
		model.addAttribute("novoFeedback", feedback);
		return "/auth/aluno/feedback";
	}
	
	@PostMapping("/gravarFeedback")
	public String gravarFeedback(@ModelAttribute("novoFeedback") @Valid Feedback feedback,
			BindingResult erros, Model model, 
			RedirectAttributes attributes) {
		
		if(erros.hasErrors()) {
			return "/auth/aluno/feedback";
		}
		
		feedbackServico.criarFeedback(feedback);
		attributes.addFlashAttribute("mensagem", "Feedback salva com sucesso!");
		return "redirect:/redirectFeedback";
	}
	
	@GetMapping("/redirectCardapio")
	public String redirecionarCardapio() {
		
		return "/auth/aluno/redirectCardapio";
	}
	
	@GetMapping("/pagCardapio")
	public String cardapio() {
		
		return "/auth/aluno/cardapio";
	}
	
	@PostMapping("/restricao/salvar")
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
				if (!fileUtils.isImagem(file.getContentType())) {
					System.out.println(file.getContentType());
					throw new FotoNotFoundExcpetion("Não é uma foto");
				}
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
		attributes.addFlashAttribute("mensagem", "Restricao adicionada com sucesso!");
		return "redirect:/aluno/pagRestricao";
	}
	
	//@PostMapping("/gravar")
	//public String gravarEstudante(@ModelAttribute("novoEstudante") @Valid Estudante estudante,
	//		BindingResult erros, Model model, 
	//		RedirectAttributes attributes) {
	//	
	//	if(erros.hasErrors()) {
	//		return "/novo-estudante";
	//	}
	//
	//	Estudante estudante2 = estudanteRepositorio.findByLogin(estudante.getLogin());
	//	if(estudante2 != null){
	//		model.addAttribute("loginExiste", "Login já está cadastrado");
	//		return "/novo-estudante";
	//	}
	//
	//	estudanteServico.criarEstudante(estudante);
	//	attributes.addFlashAttribute("mensagem", "Estudante salvo com sucesso!");
	//	return "redirect:/novo";
	//}
}
