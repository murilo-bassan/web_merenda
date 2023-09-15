package br.ifms.merenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ifms.merenda.model.Noticia;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

}
