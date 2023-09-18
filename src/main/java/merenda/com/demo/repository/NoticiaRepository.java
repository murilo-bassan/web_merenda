package merenda.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import merenda.com.demo.model.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

}
