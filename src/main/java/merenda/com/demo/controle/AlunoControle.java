package merenda.com.demo.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort;
//import java.util.Map;
 
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;

import merenda.com.demo.modelo.Noticia;
import merenda.com.demo.repositorio.NoticiaRepositorio;

//import br.ifms.merenda.controller.utils.FileUtils;
//import br.ifms.merenda.dto.RestricaoCreate;
//import br.ifms.merenda.excecao.FotoNotFoundExcpetion;
//import br.ifms.merenda.service.RestricaoService;
//import jakarta.validation.Valid;
//import merenda.com.demo.modelo.Feedback;
//import merenda.com.demo.modelo.Restricao;
//import merenda.com.demo.servico.FeedbackServico;
//import merenda.com.demo.repositorio.RestricaoRepositorio;
//import merenda.com.demo.servico.RestricaoServico;

@Controller
public class AlunoControle {
	
	@Autowired
	private NoticiaRepositorio noticiaRepository;
	

	@GetMapping("/")
    public String noticias(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "2") int size) {	
		
			Order order = new Order(Sort.Direction.DESC, "id");
 
			Pageable pageable = PageRequest.of(page - 1, size) ;

			List<Noticia> noticias = noticiaRepository.findAll();
			
			int currentPage = page;

	        model.addAttribute("currentPage", currentPage);	

		model.addAttribute("noticias", noticiaRepository.findAll(pageable)); 
	
		return "/auth/aluno/aluno-index";
    }
	
	
	
	/*
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
	//} */
}
