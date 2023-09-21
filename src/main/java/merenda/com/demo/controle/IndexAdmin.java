package merenda.com.demo.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class IndexAdmin {

	@GetMapping("/index2")
	public String principal() {
		return "/auth/admin/index-admin";
	}
}
