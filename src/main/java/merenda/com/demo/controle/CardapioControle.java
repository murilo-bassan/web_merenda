package merenda.com.demo.controle;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
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
	private CategoriaRepositorio categoriaRepository;
	
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
	
	@GetMapping("/novo")
	public String adicionarCardapio(Model model) {
		model.addAttribute("cardapio", new Cardapio());
		List<Cardapio> cardapio = cardapioRepositorio.findAll();
		model.addAttribute("cardapio", cardapio);
		
		List<Categoria> categorias = categoriaRepository.findAll();
		model.addAttribute("categorias", categorias);
		
		List<Agenda> agendas = agendaRepositorio.findAll();
		Agenda agenda = agendas.get(agendas.size() - 1);
		
		DataUtil dataUtil = new DataUtil();
		LocalDate d1 = agenda.getDataInicio();
		LocalDate d2 = agenda.getDataFim();
		
		int daysBetween = (int)ChronoUnit.DAYS.between(d1, d2);
		
		model.addAttribute("dias", daysBetween + 1);
		model.addAttribute("agenda", agenda);
		
		//------------------------------------------------
		
		List<LocalDate> listaDeDatas = new ArrayList<>();
        LocalDate dataAtual = agenda.getDataInicio();
        LocalDate dataFim = agenda.getDataFim();
        
        while (!dataAtual.isAfter(dataFim)) {
            listaDeDatas.add(dataAtual);
            dataAtual = dataAtual.plusDays(1); // Incrementa a data atual em um dia
        }

        model.addAttribute("datas", listaDeDatas); // Envie a lista de datas para a página Thymeleaf
		
		//model.addAttribute("dInicio", agenda.getDataInicio());
		//model.addAttribute("dI", agenda.dataIFormatada());
		
		return "/auth/admin/admin-criar-cardapio";
	}
	
}


