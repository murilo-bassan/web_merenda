package merenda.com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import merenda.com.demo.modelo.Noticia;
import merenda.com.demo.repositorio.NoticiaRepositorio;


@Service
public class NoticiaService {
   
    @Autowired
	private NoticiaRepositorio noticiaRepositorio;

    public Page<Noticia> buscarTodosServidoresPaginado(Pageable pageable) {
		Page<Noticia> pageTuts;
		pageTuts =  noticiaRepositorio.findAll(pageable);
		return pageTuts;
	}


}
