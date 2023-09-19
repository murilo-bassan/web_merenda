package merenda.com.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import merenda.com.demo.modelo.Foto;

public interface FotoRepositorio extends JpaRepository<Foto, Long> {

}
