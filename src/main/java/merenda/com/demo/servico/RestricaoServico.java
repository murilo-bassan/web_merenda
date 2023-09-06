package merenda.com.demo.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import merenda.com.demo.modelo.Restricao;
import merenda.com.demo.repositorio.RestricaoRepositorio;

@Service
public class RestricaoServico {

		@Autowired
		private RestricaoRepositorio restricaoRepositorio;
		
		public Restricao criarRestricao(Restricao restricao) {
			return restricaoRepositorio.save(restricao);
		}
		
		//import - java.util
		//public List<Restricao> buscarTodosEstudantes() {
		//	return restricaoRepositorio.findAll();
		//}
}
