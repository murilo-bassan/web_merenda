package merenda.com.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import merenda.com.demo.excecao.CategoriaNotFoundException;
import merenda.com.demo.modelo.Categoria;
import merenda.com.demo.repositorio.CategoriaRepositorio;


@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepositorio categoriaRepositorio;
	
	public Categoria buscarAlbumPorId(long id) throws CategoriaNotFoundException {
		Optional<Categoria> caixa = categoriaRepositorio.findById(id);
		if (caixa.isPresent()) {
			return caixa.get();
		} else {
			throw new CategoriaNotFoundException("Album" + "com id" + id + "nao existe");
		}
	}
}
