package merenda.com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import merenda.com.demo.repository.RestricaoRepository;

@Controller
@RequestMapping("/restricao")
public class RestricaoController {

	@Autowired
	private RestricaoRepository restricaoRepository;
	
	@GetMapping("/listar")
	public String listarRestricoes(Model model) {
		model.addAttribute("restricoes", restricaoRepository.findAll());		
		return "/auth/admin/admin-listar-restricoes";	
	}	
}
