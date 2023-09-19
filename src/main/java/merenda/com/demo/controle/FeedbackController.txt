package merenda.com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import merenda.com.demo.repository.FeedbackRepository;

@Controller
@RequestMapping("/feedback")
public class FeedbackController{
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@GetMapping("/listar")
	public String listarFeedback(Model model) {
		model.addAttribute("feedbacks", feedbackRepository.findAll());		
		return "/auth/admin/admin-listar-feedbacks";	
	}
}