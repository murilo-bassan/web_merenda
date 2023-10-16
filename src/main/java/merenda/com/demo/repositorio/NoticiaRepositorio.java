package merenda.com.demo.repositorio;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import merenda.com.demo.modelo.Noticia;

public interface NoticiaRepositorio extends JpaRepository<Noticia, Long> {
    List<Noticia> findById(Long id, Pageable pageable);

}
