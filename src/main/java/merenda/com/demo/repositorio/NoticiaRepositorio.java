package merenda.com.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import merenda.com.demo.modelo.Noticia;

public interface NoticiaRepositorio extends JpaRepository<Noticia, Long> {

}
